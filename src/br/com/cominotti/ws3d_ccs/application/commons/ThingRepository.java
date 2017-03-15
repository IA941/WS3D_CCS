package br.com.cominotti.ws3d_ccs.application.commons;

import ws3dproxy.model.Thing;

import java.util.List;
import java.util.function.Predicate;

public interface ThingRepository {

    List<Thing> getAllThings();

    List<Thing> getThings(Predicate<? super Thing> predicate);

    void save(Thing thing);

    void remove(String thingName);
}
