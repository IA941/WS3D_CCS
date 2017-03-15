package br.com.cominotti.ws3d_ccs.application;

public interface RunnableUseCase<TInput, TOutput> {

    TOutput run(TInput input);
}
