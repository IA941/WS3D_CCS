package br.com.cominotti.ws3d_ccs.application.creatures.use_cases.gaming;

public class PrintLeafletsDetailsUseCaseInput {

    private final String creatureName;


    public PrintLeafletsDetailsUseCaseInput(final String creatureName) {
        this.creatureName = creatureName;
    }


    public String getCreatureName() {
        return creatureName;
    }
}
