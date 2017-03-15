package br.com.cominotti.ws3d_ccs.application.creatures.use_cases.storing;

import br.com.cominotti.ws3d_ccs.application.RunnableUseCase;
import br.com.cominotti.ws3d_ccs.application.commons.CreatureRepository;
import br.com.cominotti.ws3d_ccs.application.commons.EmptyReturn;
import ws3dproxy.CommandExecException;
import ws3dproxy.model.Creature;

import java.util.logging.Logger;

public class PutSingleThingInSackUseCase implements RunnableUseCase<PutSingleThingInSackUseCaseInput, EmptyReturn> {

    private static final Logger LOGGER = Logger.getLogger(PutSingleThingInSackUseCaseInput.class.getName());

    private final CreatureRepository creatureRepository;


    public PutSingleThingInSackUseCase(final CreatureRepository creatureRepository) {
        this.creatureRepository = creatureRepository;
    }


    @Override
    public EmptyReturn run(PutSingleThingInSackUseCaseInput input) {
        try {
            final Creature creature = creatureRepository.findCreatureByName(input.getCreatureName());
            creature.putInSack(input.getThingName());
            creature.updateBag();
            creature.updateState();
            LOGGER.info("Put thing " + input.getThingName() + " in the sack...");
            LOGGER.info("Current fuel level: " + creature.getFuel());
            return EmptyReturn.get();
        } catch (CommandExecException ex) {
            LOGGER.severe(ex.getMessage());
            throw new RuntimeException();
        } finally {
            LOGGER.info("Finished " + PutSingleThingInSackUseCase.class.getName());
        }
    }
}
