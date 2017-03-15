package br.com.cominotti.ws3d_ccs.application.world.use_cases.creation;

import java.util.ArrayList;
import java.util.List;

public class CreateWorldUseCaseOutput {

    private final String creatureName;

    private final List<String> objectsNames;


    public CreateWorldUseCaseOutput(final String creatureName, final List<String> objectsNames) {
        this.creatureName = creatureName;
        this.objectsNames = new ArrayList<>(objectsNames);
    }


    public String getCreatureName() {
        return creatureName;
    }

    public List<String> getObjectsNames() {
        return new ArrayList<>(objectsNames);
    }
}
