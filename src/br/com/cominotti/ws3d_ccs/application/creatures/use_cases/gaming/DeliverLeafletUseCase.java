package br.com.cominotti.ws3d_ccs.application.creatures.use_cases.gaming;

import br.com.cominotti.ws3d_ccs.application.RunnableUseCase;
import br.com.cominotti.ws3d_ccs.application.commons.CreatureRepository;
import br.com.cominotti.ws3d_ccs.application.commons.EmptyReturn;
import ws3dproxy.model.Creature;
import ws3dproxy.model.Leaflet;

import java.util.ArrayList;
import java.util.logging.Logger;

public class DeliverLeafletUseCase implements RunnableUseCase<DeliverLeafletUseCaseInput, EmptyReturn> {

    private static final Logger LOGGER = Logger.getLogger(DeliverLeafletUseCase.class.getName());

    private final CreatureRepository creatureRepository;


    public DeliverLeafletUseCase(final CreatureRepository creatureRepository) {
        this.creatureRepository = creatureRepository;
    }


    @Override
    public EmptyReturn run(DeliverLeafletUseCaseInput input) {
        try {
            final Creature creature = creatureRepository.findCreatureByName(input.getCreatureName());
            for (Leaflet leaflet : new ArrayList<>(creature.getLeaflets())) {
                creature.deliverLeaflet(String.valueOf(leaflet.getID()));
                creature.updateBag();
                creature.updateState();
                LOGGER.info("Creature score: " + creature.s.score);
                LOGGER.info("Delivered leaflet " + leaflet.getID() + "...");
                LOGGER.info("Current fuel level: " + creature.getFuel());
            }
            return EmptyReturn.get();
        } catch(Exception ex) {
            LOGGER.severe(ex.getMessage());
            throw new RuntimeException();
        } finally {
            LOGGER.info("Finished " + DeliverLeafletUseCase.class.getName());
        }
    }
}
