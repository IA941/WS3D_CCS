package br.com.cominotti.ws3d_ccs.application;

import br.com.cominotti.ws3d_ccs.application.commons.EmptyReturn;
import br.com.cominotti.ws3d_ccs.application.creatures.use_cases.eating.EatAllFoodsInVisionUseCaseInput;
import br.com.cominotti.ws3d_ccs.application.creatures.use_cases.eating.EatAllFoodsInVisionUseCaseOutput;
import br.com.cominotti.ws3d_ccs.application.creatures.use_cases.eating.EatSingleFoodUseCaseInput;
import br.com.cominotti.ws3d_ccs.application.creatures.use_cases.gaming.DeliverLeafletUseCaseInput;
import br.com.cominotti.ws3d_ccs.application.creatures.use_cases.gaming.GenerateLeafletUseCaseInput;
import br.com.cominotti.ws3d_ccs.application.creatures.use_cases.gaming.PrintLeafletsDetailsUseCaseInput;
import br.com.cominotti.ws3d_ccs.application.creatures.use_cases.hiding.HideAllThingsInVisionUseCaseInput;
import br.com.cominotti.ws3d_ccs.application.creatures.use_cases.hiding.HideSingleThingUseCaseInput;
import br.com.cominotti.ws3d_ccs.application.creatures.use_cases.hiding.UnhideAllThingsInVisionUseCaseInput;
import br.com.cominotti.ws3d_ccs.application.creatures.use_cases.hiding.UnhideSingleThingUseCaseInput;
import br.com.cominotti.ws3d_ccs.application.creatures.use_cases.moving.*;
import br.com.cominotti.ws3d_ccs.application.creatures.use_cases.storing.PutAllThingsInVisionInSackUseCaseInput;
import br.com.cominotti.ws3d_ccs.application.creatures.use_cases.storing.PutAllThingsInVisionInSackUseCaseOutput;
import br.com.cominotti.ws3d_ccs.application.creatures.use_cases.storing.PutSingleThingInSackUseCaseInput;
import br.com.cominotti.ws3d_ccs.application.world.use_cases.creation.CreateWorldUseCaseInput;
import br.com.cominotti.ws3d_ccs.application.world.use_cases.creation.CreateWorldUseCaseOutput;
import javafx.collections.ObservableList;

import java.util.concurrent.CompletableFuture;

public interface ApplicationState extends UseCaseRegistry {

    String getCreatureName();

    ObservableList<String> getObservableThings();

    @Override
    CompletableFuture<CreateWorldUseCaseOutput> handle(CreateWorldUseCaseInput input);

    @Override
    CompletableFuture<EmptyReturn> handle(MoveForwardUseCaseInput input);

    @Override
    CompletableFuture<EmptyReturn> handle(MoveBackwardUseCaseInput input);

    @Override
    CompletableFuture<EmptyReturn> handle(RotateLeftUseCaseInput input);

    @Override
    CompletableFuture<EmptyReturn> handle(RotateRightUseCaseInput input);

    @Override
    CompletableFuture<EmptyReturn> handle(StopUseCaseInput input);

    @Override
    CompletableFuture<EatAllFoodsInVisionUseCaseOutput> handle(EatAllFoodsInVisionUseCaseInput input);

    @Override
    CompletableFuture<EmptyReturn> handle(EatSingleFoodUseCaseInput input);

    @Override
    CompletableFuture<EmptyReturn> handle(HideAllThingsInVisionUseCaseInput input);

    @Override
    CompletableFuture<EmptyReturn> handle(HideSingleThingUseCaseInput input);

    @Override
    CompletableFuture<EmptyReturn> handle(UnhideAllThingsInVisionUseCaseInput input);

    @Override
    CompletableFuture<EmptyReturn> handle(UnhideSingleThingUseCaseInput input);

    @Override
    CompletableFuture<PutAllThingsInVisionInSackUseCaseOutput> handle(PutAllThingsInVisionInSackUseCaseInput input);

    @Override
    CompletableFuture<EmptyReturn> handle(PutSingleThingInSackUseCaseInput input);

    @Override
    CompletableFuture<EmptyReturn> handle(GenerateLeafletUseCaseInput input);

    @Override
    CompletableFuture<EmptyReturn> handle(PrintLeafletsDetailsUseCaseInput input);

    @Override
    CompletableFuture<EmptyReturn> handle(DeliverLeafletUseCaseInput input);
}
