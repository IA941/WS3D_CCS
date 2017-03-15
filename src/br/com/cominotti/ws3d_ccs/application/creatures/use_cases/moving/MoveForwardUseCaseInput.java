package br.com.cominotti.ws3d_ccs.application.creatures.use_cases.moving;

public class MoveForwardUseCaseInput {

    private final String creatureName;


    public MoveForwardUseCaseInput(final String creatureName) {
        this.creatureName = creatureName;
    }


    public String getCreatureName() {
        return creatureName;
    }
}
