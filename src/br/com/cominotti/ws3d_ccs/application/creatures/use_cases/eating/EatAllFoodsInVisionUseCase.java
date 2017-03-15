package br.com.cominotti.ws3d_ccs.application.creatures.use_cases.eating;

import br.com.cominotti.ws3d_ccs.application.RunnableUseCase;
import br.com.cominotti.ws3d_ccs.application.commons.CreatureRepository;
import br.com.cominotti.ws3d_ccs.application.commons.ThingRepository;
import br.com.cominotti.ws3d_ccs.domain.model.commons.FoodPredicates;
import ws3dproxy.CommandExecException;
import ws3dproxy.model.Creature;
import ws3dproxy.model.Thing;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class EatAllFoodsInVisionUseCase implements RunnableUseCase<EatAllFoodsInVisionUseCaseInput, EatAllFoodsInVisionUseCaseOutput> {

    private static final Logger LOGGER = Logger.getLogger(EatAllFoodsInVisionUseCase.class.getName());

    private final CreatureRepository creatureRepository;

    private final ThingRepository thingRepository;


    public EatAllFoodsInVisionUseCase(final CreatureRepository creatureRepository, final ThingRepository thingRepository) {
        this.creatureRepository = creatureRepository;
        this.thingRepository = thingRepository;
    }


    @Override
    public EatAllFoodsInVisionUseCaseOutput run(final EatAllFoodsInVisionUseCaseInput input) {
        try {
            final Creature creature = creatureRepository.findCreatureByName(input.getCreatureName());
            if (creature.getThingsInVision() == null && creature.getThingsInVision().isEmpty()) {
                return EatAllFoodsInVisionUseCaseOutput.emptyOutput();
            }
            List<Thing> foodsInVision = new ArrayList<>(creature.getThingsInVision())
                    .stream().filter(
                            FoodPredicates.isFoodThing()
                    ).collect(
                            Collectors.toList()
                    );
            List<String> eatenFoods = new ArrayList<>();
            for (Thing thing : foodsInVision) {
                creature.eatIt(thing.getName());
                creature.updateState();
                eatenFoods.add(thing.getName());
                thingRepository.remove(thing.getName());
                LOGGER.info("Ate food " + thing.getName() + "...");
            }
            LOGGER.info("Current fuel level: " + creature.getFuel());
            return new EatAllFoodsInVisionUseCaseOutput(eatenFoods);
        } catch (CommandExecException e) {
            LOGGER.severe(e.getMessage());
            throw new RuntimeException();
        } finally {
            LOGGER.info("Finished " + EatAllFoodsInVisionUseCase.class.getName());
        }
    }
}