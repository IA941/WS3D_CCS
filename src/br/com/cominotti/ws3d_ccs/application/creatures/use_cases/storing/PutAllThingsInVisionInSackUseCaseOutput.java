package br.com.cominotti.ws3d_ccs.application.creatures.use_cases.storing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PutAllThingsInVisionInSackUseCaseOutput {
    private static final PutAllThingsInVisionInSackUseCaseOutput EMPTY_SINGLETON =
            new PutAllThingsInVisionInSackUseCaseOutput(Collections.emptyList());

    private final List<String> storedThings;


    public PutAllThingsInVisionInSackUseCaseOutput(final List<String> storedThings) {
        this.storedThings = new ArrayList<>(storedThings);
    }


    public static PutAllThingsInVisionInSackUseCaseOutput emptyOutput() {
        return EMPTY_SINGLETON;
    }


    public List<String> getStoredThings() {
        return new ArrayList<>(storedThings);
    }
}
