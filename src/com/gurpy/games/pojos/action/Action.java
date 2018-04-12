package com.gurpy.games.pojos.action;

import com.gurpy.games.pojos.entities.Entity;

public interface Action {
    public Entity getOwner();
    public void setOwner(Entity owner);
    public boolean perform();
}
