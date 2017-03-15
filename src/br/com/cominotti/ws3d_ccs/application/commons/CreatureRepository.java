package br.com.cominotti.ws3d_ccs.application.commons;

import ws3dproxy.model.Creature;

public interface CreatureRepository {

    Creature findCreatureByName(String creatureName);

    void saveCreature(Creature creature);
}
