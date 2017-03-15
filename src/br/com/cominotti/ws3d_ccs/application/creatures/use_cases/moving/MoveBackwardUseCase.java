package br.com.cominotti.ws3d_ccs.application.creatures.use_cases.moving;

import br.com.cominotti.ws3d_ccs.application.RunnableUseCase;
import br.com.cominotti.ws3d_ccs.application.commons.CreatureRepository;
import br.com.cominotti.ws3d_ccs.application.commons.EmptyReturn;
import ws3dproxy.CommandExecException;
import ws3dproxy.model.Creature;

import java.util.logging.Logger;

public class MoveBackwardUseCase implements RunnableUseCase<MoveBackwardUseCaseInput, EmptyReturn> {

    private static final Logger LOGGER = Logger.getLogger(MoveBackwardUseCase.class.getName());

    private final CreatureRepository creatureRepository;


    public MoveBackwardUseCase(final CreatureRepository creatureRepository) {
        this.creatureRepository = creatureRepository;
    }


    @Override
    public EmptyReturn run(final MoveBackwardUseCaseInput input) {
        try {
            final Creature creature =  creatureRepository.findCreatureByName(input.getCreatureName());
            creature.start();
            creature.move(-1.0, -1.0, -1.0);
            creature.updateState();
            LOGGER.info("Current fuel level: " + creature.getFuel());
            return EmptyReturn.get();
        } catch (CommandExecException ex) {
            LOGGER.severe(ex.getMessage());
            throw new RuntimeException();
        } finally {
            LOGGER.info("Finished " + MoveBackwardUseCase.class.getName());
        }
    }
}
