package br.com.cominotti.ws3d_ccs.application.creatures.use_cases.hiding;

import br.com.cominotti.ws3d_ccs.application.RunnableUseCase;
import br.com.cominotti.ws3d_ccs.application.commons.CreatureRepository;
import br.com.cominotti.ws3d_ccs.application.commons.EmptyReturn;
import ws3dproxy.CommandExecException;
import ws3dproxy.model.Creature;

import java.util.logging.Logger;

public class UnhideSingleThingUseCase implements RunnableUseCase<UnhideSingleThingUseCaseInput, EmptyReturn> {

    private static final Logger LOGGER = Logger.getLogger(UnhideSingleThingUseCase.class.getName());

    private final CreatureRepository creatureRepository;


    public UnhideSingleThingUseCase(final CreatureRepository creatureRepository) {
        this.creatureRepository = creatureRepository;
    }


    @Override
    public EmptyReturn run(final UnhideSingleThingUseCaseInput input) {
        try {
            final Creature creature = creatureRepository.findCreatureByName(input.getCreatureName());
            creature.unhideIt(input.getThingName());
            LOGGER.info("Unhid thing " + input.getThingName() + " ...");
            creature.updateState();
            LOGGER.info("Current fuel level: " + creature.getFuel());
            return EmptyReturn.get();
        } catch (CommandExecException ex) {
            LOGGER.severe(ex.getMessage());
            throw new RuntimeException();
        } finally {
            LOGGER.info("Finished " + UnhideSingleThingUseCase.class.getName());
        }
    }
}
