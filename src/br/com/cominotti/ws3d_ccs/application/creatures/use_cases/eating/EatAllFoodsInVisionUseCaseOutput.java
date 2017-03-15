package br.com.cominotti.ws3d_ccs.application.creatures.use_cases.eating;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EatAllFoodsInVisionUseCaseOutput {

    private static final EatAllFoodsInVisionUseCaseOutput EMPTY_SINGLETON =
            new EatAllFoodsInVisionUseCaseOutput(Collections.emptyList());

    private final List<String> eatenFoods;


    public EatAllFoodsInVisionUseCaseOutput(final List<String> eatenFoods) {
        this.eatenFoods = new ArrayList<>(eatenFoods);
    }


    public static EatAllFoodsInVisionUseCaseOutput emptyOutput() {
        return EMPTY_SINGLETON;
    }


    public List<String> getEatenFoods() {
        return new ArrayList<>(eatenFoods);
    }
}
