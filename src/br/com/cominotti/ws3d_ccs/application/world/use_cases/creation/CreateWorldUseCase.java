package br.com.cominotti.ws3d_ccs.application.world.use_cases.creation;

import br.com.cominotti.ws3d_ccs.application.RunnableUseCase;
import br.com.cominotti.ws3d_ccs.application.commons.CreatureRepository;
import br.com.cominotti.ws3d_ccs.application.commons.ThingRepository;
import br.com.cominotti.ws3d_ccs.domain.model.commons.CreaturePredicates;
import ws3dproxy.CommandExecException;
import ws3dproxy.WS3DProxy;
import ws3dproxy.model.Creature;
import ws3dproxy.model.Thing;
import ws3dproxy.model.World;

import java.util.concurrent.Executors;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public final class CreateWorldUseCase implements RunnableUseCase<CreateWorldUseCaseInput, CreateWorldUseCaseOutput> {

    private static final Logger LOGGER = Logger.getLogger(CreateWorldUseCase.class.getName());

    private final CreatureRepository creatureRepository;

    private final ThingRepository thingRepository;


    public CreateWorldUseCase(final CreatureRepository creatureRepository, final ThingRepository thingRepository) {
        this.creatureRepository = creatureRepository;
        this.thingRepository = thingRepository;
    }


    @Override
    public CreateWorldUseCaseOutput run(final CreateWorldUseCaseInput input) {
        try {
            Executors.newSingleThreadExecutor().submit(
                    () -> worldserver3d.Main.main(null)
            );
            Thread.sleep(6000);
            final WS3DProxy ws3DProxy = new WS3DProxy(input.getHost(), input.getPort());
            ws3DProxy.getWorld().reset();
            ws3DProxy.getWorld().setDeliverySpot(600, 100);
            World.createBrick(0, 570, 80, 580, 120);
            World.createBrick(0, 620, 80, 630, 120);
            World.createBrick(0, 570, 70, 630, 80);
            World.createFood(0, 100, 200);
            World.createFood(0, 150, 200);
            World.createFood(1, 200, 200);
            World.createFood(1, 250, 200);
            World.createFood(0, 300, 200);
            World.createFood(0, 350, 200);
            World.createFood(1, 400, 200);
            World.createFood(1, 450, 200);
            World.createFood(0, 500, 200);
            World.createFood(0, 550, 200);
            World.createFood(1, 600, 200);
            World.createJewel(0, 100, 300);
            World.createJewel(1, 200, 300);
            World.createJewel(2, 300, 300);
            World.createJewel(3, 400, 300);
            World.createJewel(4, 500, 300);
            World.createJewel(5, 600, 300);
            World.createJewel(0, 100, 400);
            World.createJewel(1, 200, 400);
            World.createJewel(2, 300, 400);
            World.createJewel(3, 400, 400);
            World.createJewel(4, 500, 400);
            World.createJewel(5, 600, 400);
            World.createJewel(0, 100, 500);
            World.createJewel(1, 200, 500);
            World.createJewel(2, 300, 500);
            World.createJewel(3, 400, 500);
            World.createJewel(4, 500, 500);
            World.createJewel(5, 600, 500);
            World.createCage(700, 500);
            World.getWorldEntities().stream().filter(
                    CreaturePredicates.isNotCreature()
            ).forEach(
                    thingRepository::save
            );
            final Creature creature = ws3DProxy.createCreature(100, 100, 100);
            creature.updateState();
            LOGGER.info("Current fuel level: " + creature.getFuel());
            creatureRepository.saveCreature(creature);
            return new CreateWorldUseCaseOutput(
                    creature.getName(),
                    thingRepository.getThings(
                            CreaturePredicates.isNotCreature()
                    ).stream().map(
                            Thing::getName
                    ).collect(
                            Collectors.toList()
                    )
            );
        } catch (CommandExecException | InterruptedException ex) {
            LOGGER.severe(ex.getMessage());
            throw new RuntimeException();
        } finally {
            LOGGER.info("Finished " + CreateWorldUseCase.class.getName());
        }
    }
}
