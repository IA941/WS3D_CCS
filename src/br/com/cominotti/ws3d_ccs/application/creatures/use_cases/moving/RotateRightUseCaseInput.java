package br.com.cominotti.ws3d_ccs.application.creatures.use_cases.moving;

public class RotateRightUseCaseInput {

    private final String creatureName;


    public RotateRightUseCaseInput(final String creatureName) {
        this.creatureName = creatureName;
    }


    public String getCreatureName() {
        return creatureName;
    }
}
