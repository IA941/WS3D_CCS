package br.com.cominotti.ws3d_ccs.infrastructure.storage.things;

import br.com.cominotti.ws3d_ccs.application.commons.ThingRepository;
import ws3dproxy.model.Thing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class InMemoryThingRepository implements ThingRepository {

    private Map<String, Thing> storage = new HashMap<>();


    @Override
    public List<Thing> getAllThings() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public List<Thing> getThings(Predicate<? super Thing> predicate) {
        return storage.values().stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public void save(Thing thing) {
        storage.put(thing.getName(), thing);
    }

    @Override
    public void remove(String thingName) {
        storage.remove(thingName);
    }
}
