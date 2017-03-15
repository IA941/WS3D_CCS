package br.com.cominotti.ws3d_ccs.application.creatures.use_cases.gaming;

import br.com.cominotti.ws3d_ccs.application.RunnableUseCase;
import br.com.cominotti.ws3d_ccs.application.commons.CreatureRepository;
import br.com.cominotti.ws3d_ccs.application.commons.EmptyReturn;
import br.com.cominotti.ws3d_ccs.application.creatures.use_cases.eating.EatSingleFoodUseCase;
import br.com.cominotti.ws3d_ccs.domain.model.commons.LeafletPrinter;
import ws3dproxy.CommandExecException;
import ws3dproxy.model.Creature;

import java.util.logging.Logger;

public class GenerateLeafletUseCase implements RunnableUseCase<GenerateLeafletUseCaseInput, EmptyReturn> {

    private static final Logger LOGGER = Logger.getLogger(EatSingleFoodUseCase.class.getName());

    private final CreatureRepository creatureRepository;

    private final LeafletPrinter leafletPrinter;


    public GenerateLeafletUseCase(final CreatureRepository creatureRepository, final LeafletPrinter leafletPrinter) {
        this.creatureRepository = creatureRepository;
        this.leafletPrinter = leafletPrinter;
    }


    @Override
    public EmptyReturn run(GenerateLeafletUseCaseInput input) {
        try {
            final Creature creature = creatureRepository.findCreatureByName(input.getCreatureName());
            creature.genLeaflet();
            creature.updateState();
            creature.getLeaflets().forEach(
                    leafletPrinter::printLeaflet
            );
            LOGGER.info("Current fuel level: " + creature.getFuel());
            return EmptyReturn.get();
        } catch (CommandExecException e) {
            LOGGER.severe(e.getMessage());
            throw new RuntimeException();
        } finally {
            LOGGER.info("Finished " + GenerateLeafletUseCase.class.getName());
        }
    }
}
