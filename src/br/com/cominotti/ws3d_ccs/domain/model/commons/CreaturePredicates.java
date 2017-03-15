package br.com.cominotti.ws3d_ccs.domain.model.commons;

import ws3dproxy.model.Thing;
import ws3dproxy.util.Constants;

import java.util.function.Predicate;

public class CreaturePredicates {

    public static Predicate<Thing> isNotCreature() {
        return thing ->
                thing.getCategory() != Constants.categoryCREATURE;
    }
}
