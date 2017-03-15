package br.com.cominotti.ws3d_ccs.application.creatures.use_cases.hiding;

public class HideAllThingsInVisionUseCaseInput {

    private final String creatureName;


    public HideAllThingsInVisionUseCaseInput(final String creatureName) {
        this.creatureName = creatureName;
    }


    public String getCreatureName() {
        return creatureName;
    }
}
