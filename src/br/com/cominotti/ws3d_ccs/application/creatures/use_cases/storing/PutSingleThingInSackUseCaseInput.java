package br.com.cominotti.ws3d_ccs.application.creatures.use_cases.storing;

public class PutSingleThingInSackUseCaseInput {

    private final String creatureName;

    private final String thingName;


    public PutSingleThingInSackUseCaseInput(final String creatureName, final String thingName) {
        this.creatureName = creatureName;
        this.thingName = thingName;
    }


    public String getCreatureName() {
        return creatureName;
    }

    public String getThingName() {
        return thingName;
    }
}
