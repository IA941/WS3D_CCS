package br.com.cominotti.ws3d_ccs.application;

import java.util.concurrent.CompletableFuture;

public class AsyncUseCaseRunner implements UseCaseRunner {

    @Override
    public <TInput, TOutput> CompletableFuture<TOutput> run(final RunnableUseCase<TInput, TOutput> runnableUseCase, final TInput useCaseInput) {
        return CompletableFuture.supplyAsync(
                () -> runnableUseCase.run(useCaseInput)
        );
    }
}
