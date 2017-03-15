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

import java.util.concurrent.CompletableFuture;

public interface UseCaseRegistry {

    CompletableFuture<CreateWorldUseCaseOutput> handle(CreateWorldUseCaseInput input);

    CompletableFuture<EmptyReturn> handle(MoveForwardUseCaseInput input);

    CompletableFuture<EmptyReturn> handle(MoveBackwardUseCaseInput input);

    CompletableFuture<EmptyReturn> handle(RotateLeftUseCaseInput input);

    CompletableFuture<EmptyReturn> handle(RotateRightUseCaseInput input);

    CompletableFuture<EmptyReturn> handle(StopUseCaseInput input);

    CompletableFuture<EatAllFoodsInVisionUseCaseOutput> handle(EatAllFoodsInVisionUseCaseInput input);

    CompletableFuture<EmptyReturn> handle(EatSingleFoodUseCaseInput input);

    CompletableFuture<EmptyReturn> handle(HideAllThingsInVisionUseCaseInput input);

    CompletableFuture<EmptyReturn> handle(HideSingleThingUseCaseInput input);

    CompletableFuture<EmptyReturn> handle(UnhideAllThingsInVisionUseCaseInput input);

    CompletableFuture<EmptyReturn> handle(UnhideSingleThingUseCaseInput input);

    CompletableFuture<PutAllThingsInVisionInSackUseCaseOutput> handle(PutAllThingsInVisionInSackUseCaseInput input);

    CompletableFuture<EmptyReturn> handle(PutSingleThingInSackUseCaseInput input);

    CompletableFuture<EmptyReturn> handle(GenerateLeafletUseCaseInput input);

    CompletableFuture<EmptyReturn> handle(PrintLeafletsDetailsUseCaseInput input);

    CompletableFuture<EmptyReturn> handle(DeliverLeafletUseCaseInput input);
}
