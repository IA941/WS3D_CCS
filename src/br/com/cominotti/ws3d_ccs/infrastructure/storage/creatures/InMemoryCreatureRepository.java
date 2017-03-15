package br.com.cominotti.ws3d_ccs.infrastructure.storage.creatures;

import br.com.cominotti.ws3d_ccs.application.commons.CreatureRepository;
import ws3dproxy.model.Creature;

import java.util.HashMap;

public class InMemoryCreatureRepository implements CreatureRepository {

    private HashMap<String, Creature> storage = new HashMap<>();


    @Override
    public Creature findCreatureByName(String creatureName) {
        return storage.get(creatureName);
    }

    @Override
    public void saveCreature(Creature creature) {
        storage.put(creature.getName(), creature);
    }
}
