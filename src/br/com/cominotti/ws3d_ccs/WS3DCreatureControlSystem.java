package br.com.cominotti.ws3d_ccs;

import br.com.cominotti.ws3d_ccs.application.ApplicationState;
import br.com.cominotti.ws3d_ccs.application.AsyncUseCaseRunner;
import br.com.cominotti.ws3d_ccs.application.UseCaseRegistry;
import br.com.cominotti.ws3d_ccs.application.UseCaseRunner;
import br.com.cominotti.ws3d_ccs.application.commons.CreatureRepository;
import br.com.cominotti.ws3d_ccs.application.commons.EmptyReturn;
import br.com.cominotti.ws3d_ccs.application.commons.ThingRepository;
import br.com.cominotti.ws3d_ccs.application.creatures.use_cases.eating.*;
import br.com.cominotti.ws3d_ccs.application.creatures.use_cases.gaming.*;
import br.com.cominotti.ws3d_ccs.application.creatures.use_cases.hiding.*;
import br.com.cominotti.ws3d_ccs.application.creatures.use_cases.moving.*;
import br.com.cominotti.ws3d_ccs.application.creatures.use_cases.storing.*;
import br.com.cominotti.ws3d_ccs.application.world.use_cases.creation.CreateWorldUseCase;
import br.com.cominotti.ws3d_ccs.application.world.use_cases.creation.CreateWorldUseCaseInput;
import br.com.cominotti.ws3d_ccs.application.world.use_cases.creation.CreateWorldUseCaseOutput;
import br.com.cominotti.ws3d_ccs.domain.model.commons.FoodPredicates;
import br.com.cominotti.ws3d_ccs.domain.model.commons.TerminalLeafletPrinter;
import br.com.cominotti.ws3d_ccs.infrastructure.storage.creatures.InMemoryCreatureRepository;
import br.com.cominotti.ws3d_ccs.infrastructure.storage.things.InMemoryThingRepository;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class WS3DCreatureControlSystem extends Application {

    private final static Logger LOGGER = Logger.getLogger(WS3DCreatureControlSystem.class.getName());

    private final static ApplicationState APPLICATION_STATE;


    static {
        APPLICATION_STATE = new ApplicationState() {

            private final UseCaseRegistry useCaseRegistry = new UseCaseRegistry() {

                private final UseCaseRunner useCaseRunner = new AsyncUseCaseRunner();

                private final CreatureRepository creatureRepository = new InMemoryCreatureRepository();

                private final ThingRepository thingRepository = new InMemoryThingRepository();


                @Override
                public CompletableFuture<CreateWorldUseCaseOutput> handle(final CreateWorldUseCaseInput input) {
                    return useCaseRunner.run(new CreateWorldUseCase(creatureRepository, thingRepository), input);
                }

                @Override
                public CompletableFuture<EmptyReturn> handle(final MoveForwardUseCaseInput input) {
                    return useCaseRunner.run(new MoveForwardUseCase(creatureRepository), input);
                }

                @Override
                public CompletableFuture<EmptyReturn> handle(final MoveBackwardUseCaseInput input) {
                    return useCaseRunner.run(new MoveBackwardUseCase(creatureRepository), input);
                }

                @Override
                public CompletableFuture<EmptyReturn> handle(final RotateLeftUseCaseInput input) {
                    return useCaseRunner.run(new RotateLeftUseCase(creatureRepository), input);
                }

                @Override
                public CompletableFuture<EmptyReturn> handle(final RotateRightUseCaseInput input) {
                    return useCaseRunner.run(new RotateRightUseCase(creatureRepository), input);
                }

                @Override
                public CompletableFuture<EmptyReturn> handle(final StopUseCaseInput input) {
                    return useCaseRunner.run(new StopUseCase(creatureRepository), input);
                }

                @Override
                public CompletableFuture<EatAllFoodsInVisionUseCaseOutput> handle(final EatAllFoodsInVisionUseCaseInput input) {
                    return useCaseRunner.run(new EatAllFoodsInVisionUseCase(creatureRepository, thingRepository), input);
                }

                @Override
                public CompletableFuture<EmptyReturn> handle(final EatSingleFoodUseCaseInput input) {
                    return useCaseRunner.run(new EatSingleFoodUseCase(creatureRepository, thingRepository), input);
                }

                @Override
                public CompletableFuture<EmptyReturn> handle(final HideAllThingsInVisionUseCaseInput input) {
                    return useCaseRunner.run(new HideAllThingsInVisionUseCase(creatureRepository), input);
                }

                @Override
                public CompletableFuture<EmptyReturn> handle(final HideSingleThingUseCaseInput input) {
                    return useCaseRunner.run(new HideSingleThingUseCase(creatureRepository), input);
                }

                @Override
                public CompletableFuture<EmptyReturn> handle(final UnhideAllThingsInVisionUseCaseInput input) {
                    return useCaseRunner.run(new UnhideAllThingsInVisionUseCase(creatureRepository), input);
                }

                @Override
                public CompletableFuture<EmptyReturn> handle(final UnhideSingleThingUseCaseInput input) {
                    return useCaseRunner.run(new UnhideSingleThingUseCase(creatureRepository), input);
                }

                @Override
                public CompletableFuture<PutAllThingsInVisionInSackUseCaseOutput> handle(final PutAllThingsInVisionInSackUseCaseInput input) {
                    return useCaseRunner.run(new PutAllThingsInVisionInSackUseCase(creatureRepository), input);
                }

                @Override
                public CompletableFuture<EmptyReturn> handle(final PutSingleThingInSackUseCaseInput input) {
                    return useCaseRunner.run(new PutSingleThingInSackUseCase(creatureRepository), input);
                }

                @Override
                public CompletableFuture<EmptyReturn> handle(final GenerateLeafletUseCaseInput input) {
                    return useCaseRunner.run(new GenerateLeafletUseCase(creatureRepository, new TerminalLeafletPrinter()), input);
                }

                @Override
                public CompletableFuture<EmptyReturn> handle(final PrintLeafletsDetailsUseCaseInput input) {
                    return useCaseRunner.run(new PrintLeafletsDetailsUseCase(creatureRepository, new TerminalLeafletPrinter()), input);
                }

                @Override
                public CompletableFuture<EmptyReturn> handle(final DeliverLeafletUseCaseInput input) {
                    return useCaseRunner.run(new DeliverLeafletUseCase(creatureRepository), input);
                }
            };

            private String creatureName;

            private ObservableList<String> observableThings;


            @Override
            public String getCreatureName() {
                return creatureName;
            }

            @Override
            public ObservableList<String> getObservableThings() {
                return new ReadOnlyListWrapper<>(observableThings);
            }

            @Override
            public CompletableFuture<CreateWorldUseCaseOutput> handle(final CreateWorldUseCaseInput input) {
                return useCaseRegistry.handle(input).thenApply(
                        createWorldUseCaseOutput -> {
                            creatureName = createWorldUseCaseOutput.getCreatureName();
                            observableThings = FXCollections.observableList(
                                    createWorldUseCaseOutput.getObjectsNames()
                            );
                            return createWorldUseCaseOutput;
                        }
                );
            }

            @Override
            public CompletableFuture<EmptyReturn> handle(final MoveForwardUseCaseInput input) {
                return useCaseRegistry.handle(input);
            }

            @Override
            public CompletableFuture<EmptyReturn> handle(final MoveBackwardUseCaseInput input) {
                return useCaseRegistry.handle(input);
            }

            @Override
            public CompletableFuture<EmptyReturn> handle(final RotateLeftUseCaseInput input) {
                return useCaseRegistry.handle(input);
            }

            @Override
            public CompletableFuture<EmptyReturn> handle(final RotateRightUseCaseInput input) {
                return useCaseRegistry.handle(input);
            }

            @Override
            public CompletableFuture<EmptyReturn> handle(final StopUseCaseInput input) {
                return useCaseRegistry.handle(input);
            }

            @Override
            public CompletableFuture<EatAllFoodsInVisionUseCaseOutput> handle(final EatAllFoodsInVisionUseCaseInput input) {
                return useCaseRegistry.handle(input).thenApply(
                        eatAllFoodsInVisionUseCaseOutput -> {
                            Platform.runLater(
                                    () -> observableThings.removeAll(
                                            eatAllFoodsInVisionUseCaseOutput.getEatenFoods()
                                    )
                            );
                            return eatAllFoodsInVisionUseCaseOutput;
                        }
                );
            }

            @Override
            public CompletableFuture<EmptyReturn> handle(final EatSingleFoodUseCaseInput input) {
                return useCaseRegistry.handle(input).thenApply(
                        emptyReturn -> {
                            Platform.runLater(
                                    () -> observableThings.remove(input.getFoodName())
                            );
                            return emptyReturn;
                        }
                );
            }

            @Override
            public CompletableFuture<EmptyReturn> handle(final HideAllThingsInVisionUseCaseInput input) {
                return useCaseRegistry.handle(input);
            }

            @Override
            public CompletableFuture<EmptyReturn> handle(final HideSingleThingUseCaseInput input) {
                return useCaseRegistry.handle(input);
            }

            @Override
            public CompletableFuture<EmptyReturn> handle(final UnhideAllThingsInVisionUseCaseInput input) {
                return useCaseRegistry.handle(input);
            }

            @Override
            public CompletableFuture<EmptyReturn> handle(final UnhideSingleThingUseCaseInput input) {
                return useCaseRegistry.handle(input);
            }

            @Override
            public CompletableFuture<PutAllThingsInVisionInSackUseCaseOutput> handle(final PutAllThingsInVisionInSackUseCaseInput input) {
                return useCaseRegistry.handle(input).thenApply(
                        putAllThingsInVisionInSackUseCaseOutput -> {
                            Platform.runLater(
                                    () -> observableThings.removeAll(
                                            putAllThingsInVisionInSackUseCaseOutput.getStoredThings().stream().filter(
                                                    FoodPredicates.isNotFoodName()
                                            ).collect(
                                                    Collectors.toList()
                                            )
                                    )
                            );
                            return putAllThingsInVisionInSackUseCaseOutput;
                        }
                );
            }

            @Override
            public CompletableFuture<EmptyReturn> handle(final PutSingleThingInSackUseCaseInput input) {
                return useCaseRegistry.handle(input).thenApply(
                        emptyReturn -> {
                            Platform.runLater(
                                    () -> observableThings.remove(
                                            input.getThingName()
                                    )
                            );
                            return emptyReturn;
                        }
                ).exceptionally(
                        throwable -> null
                );
            }

            @Override
            public CompletableFuture<EmptyReturn> handle(GenerateLeafletUseCaseInput input) {
                return useCaseRegistry.handle(input);
            }

            @Override
            public CompletableFuture<EmptyReturn> handle(PrintLeafletsDetailsUseCaseInput input) {
                return useCaseRegistry.handle(input);
            }

            @Override
            public CompletableFuture<EmptyReturn> handle(DeliverLeafletUseCaseInput input) {
                return useCaseRegistry.handle(input);
            }
        };
    }


    public static void main(String[] args) {
        try {
            APPLICATION_STATE.handle(
                    new CreateWorldUseCaseInput()
            ).thenAccept(
                    createWorldUseCaseOutput -> launch(args)
            ).join();
        } catch (Exception ex) {
            LOGGER.severe(ex.getMessage());
        }
    }

    public static ApplicationState getApplicationState() {
        return APPLICATION_STATE;
    }


    @Override
    public void start(Stage primaryStage) {
        try {
            GridPane page = FXMLLoader.load(getClass().getResource("gui/main_panel.fxml"));
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.setTitle("WS3D Creature Control System");
            primaryStage.show();
        } catch (Exception ex) {
            LOGGER.severe(ex.getMessage());
            throw new RuntimeException();
        }
    }
}
