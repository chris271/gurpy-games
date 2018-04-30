package com.gurpy.games.obj.action.control;

import com.gurpy.games.gui.GurpusUI;
import com.gurpy.games.obj.action.Action;
import com.gurpy.games.obj.control.spawner.BBoxSpawner;
import com.gurpy.games.obj.control.spawner.Spawner;
import com.gurpy.games.obj.entities.Entity;
import com.gurpy.games.obj.entities.ui.UIElement;
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
