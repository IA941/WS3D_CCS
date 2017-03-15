package br.com.cominotti.ws3d_ccs.application;

import java.util.concurrent.CompletableFuture;

public interface UseCaseRunner {

    <TInput, TOutput> CompletableFuture<TOutput> run(RunnableUseCase<TInput, TOutput> runnableFeature, TInput featureInput);
}
