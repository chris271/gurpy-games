package com.gurpy.games.obj.action;

import com.gurpy.games.obj.entities.ui.UIEntity;

public abstract class UIAction implements Action {

    private UIEntity owner;

    public UIAction(UIEntity owner) {
        this.owner = owner;
    }

    @Override
    public UIEntity getOwner() {
        return owner;
    }

    @Override
    public void setOwner(UIEntity owner) {
        this.owner = owner;
    }

    @Override
    public boolean perform() {
        //Generic UI operation.
        return false;
    }

}
