package br.com.cominotti.ws3d_ccs.application.creatures.use_cases.storing;

public class PutAllThingsInVisionInSackUseCaseInput {

    private final String creatureName;


    public PutAllThingsInVisionInSackUseCaseInput(final String creatureName) {
        this.creatureName = creatureName;
    }


    public String getCreatureName() {
        return creatureName;
    }
}
