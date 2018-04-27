package com.gurpy.games.pojos.action.control;

import com.gurpy.games.gui.GurpusUI;
import com.gurpy.games.pojos.action.Action;
import com.gurpy.games.pojos.control.BBoxSpawner;
import com.gurpy.games.pojos.control.Spawner;
import com.gurpy.games.pojos.entities.BBoxElement;
import com.gurpy.games.pojos.entities.Entity;
import com.gurpy.games.pojos.entities.UIElement;
import com.sun.corba.se.impl.io.TypeMismatchException;

public class StepSpawnerAction implements Action {

    private Spawner spawner;
    private GurpusUI contentPane;
    private double chance;

    public StepSpawnerAction(Spawner owner, GurpusUI contentPane, double chance) {
        this.spawner = owner;
        this.contentPane = contentPane;
        this.chance = chance;
    }

    @Override
    public Entity getOwner() {
        return spawner;
    }

    @Override
    public void setOwner(Entity owner) {
        if (owner instanceof BBoxSpawner) {
            this.spawner = (BBoxSpawner)getOwner();
        } else {
            throw new TypeMismatchException("Entity not a type of Spawner.");
        }
    }

    @Override
    public boolean perform() {
        if (getOwner() instanceof BBoxSpawner) {
            BBoxSpawner spawner = (BBoxSpawner)getOwner();
            if (spawner.randomSpawn(chance)) {
                UIElement elementToSpawn = spawner.nextElement();
                elementToSpawn.setPosition(spawner.randomLocation());
                return new SpawnAction(elementToSpawn, contentPane).perform();
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
