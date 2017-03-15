package br.com.cominotti.ws3d_ccs.application.creatures.use_cases.eating;

public class EatAllFoodsInVisionUseCaseInput {

    private final String creatureName;


    public EatAllFoodsInVisionUseCaseInput(final String creatureName) {
        this.creatureName = creatureName;
    }


    public String getCreatureName() {
        return creatureName;
    }
}
