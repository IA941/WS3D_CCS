package br.com.cominotti.ws3d_ccs.application.creatures.use_cases.eating;

import br.com.cominotti.ws3d_ccs.application.RunnableUseCase;
import br.com.cominotti.ws3d_ccs.application.commons.CreatureRepository;
import br.com.cominotti.ws3d_ccs.application.commons.EmptyReturn;
import br.com.cominotti.ws3d_ccs.application.commons.ThingRepository;
import ws3dproxy.CommandExecException;
import ws3dproxy.model.Creature;

import java.util.logging.Logger;

public final class EatSingleFoodUseCase implements RunnableUseCase<EatSingleFoodUseCaseInput, EmptyReturn> {

    private static final Logger LOGGER = Logger.getLogger(EatSingleFoodUseCase.class.getName());

    private final CreatureRepository creatureRepository;

    private final ThingRepository thingRepository;


    public EatSingleFoodUseCase(final CreatureRepository creatureRepository, final ThingRepository thingRepository) {
        this.creatureRepository = creatureRepository;
        this.thingRepository = thingRepository;
    }


    @Override
    public EmptyReturn run(final EatSingleFoodUseCaseInput input) {
        try {
            final Creature creature = creatureRepository.findCreatureByName(input.getCreatureName());
            creature.eatIt(input.getFoodName());
            creature.updateState();
            thingRepository.remove(input.getFoodName());
            LOGGER.info("Ate food " + input.getFoodName() + "...");
            LOGGER.info("Current fuel level: " + creature.getFuel());
            return EmptyReturn.get();
        } catch (CommandExecException e) {
            LOGGER.severe(e.getMessage());
            throw new RuntimeException();
        } finally {
            LOGGER.info("Finished " + EatSingleFoodUseCase.class.getName());
        }
    }
}
