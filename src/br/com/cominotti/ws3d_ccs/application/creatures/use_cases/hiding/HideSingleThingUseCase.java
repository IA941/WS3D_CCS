package br.com.cominotti.ws3d_ccs.application.creatures.use_cases.hiding;

import br.com.cominotti.ws3d_ccs.application.RunnableUseCase;
import br.com.cominotti.ws3d_ccs.application.commons.CreatureRepository;
import br.com.cominotti.ws3d_ccs.application.commons.EmptyReturn;
import ws3dproxy.CommandExecException;
import ws3dproxy.model.Creature;

import java.util.logging.Logger;

public class HideSingleThingUseCase implements RunnableUseCase<HideSingleThingUseCaseInput, EmptyReturn> {

    private static final Logger LOGGER = Logger.getLogger(HideSingleThingUseCase.class.getName());

    private final CreatureRepository creatureRepository;


    public HideSingleThingUseCase(final CreatureRepository creatureRepository) {
        this.creatureRepository = creatureRepository;
    }


    @Override
    public EmptyReturn run(final HideSingleThingUseCaseInput input) {
        try {
            final Creature creature = creatureRepository.findCreatureByName(input.getCreatureName());
            creature.hideIt(input.getThingName());
            LOGGER.info("Hid thing " + input.getThingName() + " ...");
            creature.updateState();
            LOGGER.info("Current fuel level: " + creature.getFuel());
            return EmptyReturn.get();
        } catch (CommandExecException ex) {
            LOGGER.severe(ex.getMessage());
            throw new RuntimeException();
        } finally {
            LOGGER.info("Finished " + HideSingleThingUseCase.class.getName());
        }
    }
}
