package com.gurpy.games.pojos.action;

import com.gurpy.games.pojos.entities.Entity;
import com.gurpy.games.pojos.entities.UIEntity;

public abstract class UIAction implements Action {

    private Entity owner;

    UIAction(UIEntity owner) {
        this.owner = owner;
    }

    @Override
    public Entity getOwner() {
        return owner;
    }

    @Override
    public void setOwner(Entity owner) {
        this.owner = owner;
    }

    @Override
    public boolean perform() {
        //Generic UI operation.
        return false;
    }

}
