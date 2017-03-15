package br.com.cominotti.ws3d_ccs.application.creatures.use_cases.hiding;

import br.com.cominotti.ws3d_ccs.application.RunnableUseCase;
import br.com.cominotti.ws3d_ccs.application.commons.CreatureRepository;
import br.com.cominotti.ws3d_ccs.application.commons.EmptyReturn;
import ws3dproxy.CommandExecException;
import ws3dproxy.model.Creature;
import ws3dproxy.model.Thing;

import java.util.ArrayList;
import java.util.logging.Logger;

public final class HideAllThingsInVisionUseCase implements RunnableUseCase<HideAllThingsInVisionUseCaseInput, EmptyReturn> {

    private static final Logger LOGGER = Logger.getLogger(HideAllThingsInVisionUseCase.class.getName());

    private final CreatureRepository creatureRepository;


    public HideAllThingsInVisionUseCase(final CreatureRepository creatureRepository) {
        this.creatureRepository = creatureRepository;
    }


    @Override
    public EmptyReturn run(final HideAllThingsInVisionUseCaseInput input) {
        try {
            final Creature creature = creatureRepository.findCreatureByName(input.getCreatureName());
            if (creature.getThingsInVision() == null && creature.getThingsInVision().isEmpty()) {
                return EmptyReturn.get();
            }
            for (Thing thing : new ArrayList<>(creature.getThingsInVision())) {
                creature.hideIt(thing.getName());
                creature.updateState();
                LOGGER.info("Hid thing " + thing.getName() + " ...");
            }
            LOGGER.info("Current fuel level: " + creature.getFuel());
            return EmptyReturn.get();
        } catch (CommandExecException ex) {
            LOGGER.severe(ex.getMessage());
            throw new RuntimeException();
        } finally {
            LOGGER.info("Finished " + HideAllThingsInVisionUseCase.class.getName());
        }
    }
}
