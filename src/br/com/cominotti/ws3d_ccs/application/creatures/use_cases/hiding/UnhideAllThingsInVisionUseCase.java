package br.com.cominotti.ws3d_ccs.application.creatures.use_cases.hiding;

import br.com.cominotti.ws3d_ccs.application.RunnableUseCase;
import br.com.cominotti.ws3d_ccs.application.commons.CreatureRepository;
import br.com.cominotti.ws3d_ccs.application.commons.EmptyReturn;
import ws3dproxy.CommandExecException;
import ws3dproxy.model.Creature;
import ws3dproxy.model.Thing;

import java.util.ArrayList;
import java.util.logging.Logger;

public class UnhideAllThingsInVisionUseCase implements RunnableUseCase<UnhideAllThingsInVisionUseCaseInput, EmptyReturn> {

    private static final Logger LOGGER = Logger.getLogger(UnhideAllThingsInVisionUseCase.class.getName());

    private final CreatureRepository creatureRepository;


    public UnhideAllThingsInVisionUseCase(final CreatureRepository creatureRepository) {
        this.creatureRepository = creatureRepository;
    }


    @Override
    public EmptyReturn run(UnhideAllThingsInVisionUseCaseInput input) {
        try {
            final Creature creature = creatureRepository.findCreatureByName(input.getCreatureName());
            if (creature.getThingsInVision() == null && creature.getThingsInVision().isEmpty()) {
                return EmptyReturn.get();
            }
            for (Thing thing : new ArrayList<>(creature.getThingsInVision())) {
                creature.unhideIt(thing.getName());
                creature.updateState();
                LOGGER.info("Unhid thing " + thing.getName() + " ...");
            }
            LOGGER.info("Current fuel level: " + creature.getFuel());
            return EmptyReturn.get();
        } catch (CommandExecException ex) {
            LOGGER.severe(ex.getMessage());
            throw new RuntimeException();
        } finally {
            LOGGER.info("Finished " + UnhideAllThingsInVisionUseCase.class.getName());
        }
    }
}
