package br.com.cominotti.ws3d_ccs.application.creatures.use_cases.storing;

import br.com.cominotti.ws3d_ccs.application.RunnableUseCase;
import br.com.cominotti.ws3d_ccs.application.commons.CreatureRepository;
import ws3dproxy.CommandExecException;
import ws3dproxy.model.Creature;
import ws3dproxy.model.Thing;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PutAllThingsInVisionInSackUseCase implements RunnableUseCase<PutAllThingsInVisionInSackUseCaseInput, PutAllThingsInVisionInSackUseCaseOutput> {

    private static final Logger LOGGER = Logger.getLogger(PutAllThingsInVisionInSackUseCase.class.getName());

    private final CreatureRepository creatureRepository;


    public PutAllThingsInVisionInSackUseCase(final CreatureRepository creatureRepository) {
        this.creatureRepository = creatureRepository;
    }


    @Override
    public PutAllThingsInVisionInSackUseCaseOutput run(PutAllThingsInVisionInSackUseCaseInput input) {
        try {
            final Creature creature = creatureRepository.findCreatureByName(input.getCreatureName());
            if (creature.getThingsInVision() == null && creature.getThingsInVision().isEmpty()) {
                return PutAllThingsInVisionInSackUseCaseOutput.emptyOutput();
            }
            List<String> storedThings = new ArrayList<>();
            for (Thing thing : new ArrayList<>(creature.getThingsInVision())) {
                creature.putInSack(thing.getName());
                creature.updateBag();
                creature.updateState();
                storedThings.add(thing.getName());
                LOGGER.info("Put thing " + thing.getName() + " in the sack...");
            }
            LOGGER.info("Current fuel level: " + creature.getFuel());
            return new PutAllThingsInVisionInSackUseCaseOutput(storedThings);
        } catch (CommandExecException ex) {
            LOGGER.severe(ex.getMessage());
            throw new RuntimeException();
        } finally {
            LOGGER.info("Finished " + PutAllThingsInVisionInSackUseCase.class.getName());
        }
    }
}
