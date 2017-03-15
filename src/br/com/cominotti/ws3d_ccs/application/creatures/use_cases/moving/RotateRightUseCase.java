package br.com.cominotti.ws3d_ccs.application.creatures.use_cases.moving;

import br.com.cominotti.ws3d_ccs.application.RunnableUseCase;
import br.com.cominotti.ws3d_ccs.application.commons.CreatureRepository;
import br.com.cominotti.ws3d_ccs.application.commons.EmptyReturn;
import ws3dproxy.CommandExecException;
import ws3dproxy.model.Creature;

import java.util.logging.Logger;

public class RotateRightUseCase implements RunnableUseCase<RotateRightUseCaseInput, EmptyReturn> {

    private static final Logger LOGGER = Logger.getLogger(RotateLeftUseCase.class.getName());

    private final CreatureRepository creatureRepository;


    public RotateRightUseCase(final CreatureRepository creatureRepository) {
        this.creatureRepository = creatureRepository;
    }


    @Override
    public EmptyReturn run(RotateRightUseCaseInput input) {
        try {
            final Creature creature = creatureRepository.findCreatureByName(input.getCreatureName());
            creature.start();
            creature.rotate(1.0);
            creature.updateState();
            LOGGER.info("Current fuel level: " + creature.getFuel());
            return EmptyReturn.get();
        } catch (CommandExecException ex) {
            LOGGER.severe(ex.getMessage());
            throw new RuntimeException();
        } finally {
            LOGGER.info("Finished " + RotateRightUseCase.class.getName());
        }
    }
}
