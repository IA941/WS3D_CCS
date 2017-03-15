package br.com.cominotti.ws3d_ccs.domain.model.commons;

import ws3dproxy.model.Thing;
import ws3dproxy.util.Constants;

import java.util.function.Predicate;

public class FoodPredicates {

    public static Predicate<Thing> isFoodThing() {
        return thing ->
                thing.getCategory() == Constants.categoryFOOD ||
                thing.getCategory() == Constants.categoryNPFOOD ||
                thing.getCategory() == Constants.categoryPFOOD;
    }

    public static Predicate<String> isFoodName() {
        return thing ->
                thing.contains("Food");
    }

    public static Predicate<String> isNotFoodName() {
        return thing ->
                !thing.contains("Food");
    }
}
