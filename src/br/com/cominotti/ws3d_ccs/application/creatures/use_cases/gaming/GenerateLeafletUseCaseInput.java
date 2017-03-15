package br.com.cominotti.ws3d_ccs.application.creatures.use_cases.gaming;

public class GenerateLeafletUseCaseInput {

    private final String creatureName;


    public GenerateLeafletUseCaseInput(final String creatureName) {
        this.creatureName = creatureName;
    }


    public String getCreatureName() {
        return creatureName;
    }
}
