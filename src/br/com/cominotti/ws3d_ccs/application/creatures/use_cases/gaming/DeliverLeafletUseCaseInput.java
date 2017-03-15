package br.com.cominotti.ws3d_ccs.application.creatures.use_cases.gaming;

public class DeliverLeafletUseCaseInput {

    private final String creatureName;


    public DeliverLeafletUseCaseInput(final String creatureName) {
        this.creatureName = creatureName;
    }


    public String getCreatureName() {
        return creatureName;
    }
}
