package br.com.cominotti.ws3d_ccs.application.creatures.use_cases.gaming;

import br.com.cominotti.ws3d_ccs.application.RunnableUseCase;
import br.com.cominotti.ws3d_ccs.application.commons.CreatureRepository;
import br.com.cominotti.ws3d_ccs.application.commons.EmptyReturn;
import br.com.cominotti.ws3d_ccs.domain.model.commons.LeafletPrinter;
import ws3dproxy.model.Creature;

import java.util.logging.Logger;

public class PrintLeafletsDetailsUseCase implements RunnableUseCase<PrintLeafletsDetailsUseCaseInput, EmptyReturn> {

    private static final Logger LOGGER = Logger.getLogger(PrintLeafletsDetailsUseCase.class.getName());

    private final CreatureRepository creatureRepository;

    private final LeafletPrinter leafletPrinter;


    public PrintLeafletsDetailsUseCase(final CreatureRepository creatureRepository, final LeafletPrinter leafletPrinter) {
        this.creatureRepository = creatureRepository;
        this.leafletPrinter = leafletPrinter;
    }


    @Override
    public EmptyReturn run(PrintLeafletsDetailsUseCaseInput input) {
        final Creature creature = creatureRepository.findCreatureByName(input.getCreatureName());
        creature.getLeaflets().forEach(
                leafletPrinter::printLeaflet
        );
        LOGGER.info("Creature score: " + creature.s.score);
        LOGGER.info("Finished " + PrintLeafletsDetailsUseCase.class.getName());
        return EmptyReturn.get();
    }
}
