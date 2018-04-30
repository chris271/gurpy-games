package com.gurpy.games.obj.action;

import com.gurpy.games.obj.entities.Entity;

public interface Action {
    public Entity getOwner();
    public void setOwner(Entity owner);
    public boolean perform();
}
