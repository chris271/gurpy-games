package com.gurpy.games.obj.action;

import com.gurpy.games.obj.entities.ui.UIEntity;

public interface Action {
    public UIEntity getOwner();
    public void setOwner(UIEntity owner);
    public boolean perform();
}
