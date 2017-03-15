package br.com.cominotti.ws3d_ccs.application.creatures.use_cases.hiding;

public class UnhideAllThingsInVisionUseCaseInput {

    private final String creatureName;


    public UnhideAllThingsInVisionUseCaseInput(final String creatureName) {
        this.creatureName = creatureName;
    }


    public String getCreatureName() {
        return creatureName;
    }
}
