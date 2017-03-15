package br.com.cominotti.ws3d_ccs.application.creatures.use_cases.moving;

public class MoveBackwardUseCaseInput {

    private final String creatureName;


    public MoveBackwardUseCaseInput(final String creatureName) {
        this.creatureName = creatureName;
    }

    public String getCreatureName() {
        return creatureName;
    }
}
