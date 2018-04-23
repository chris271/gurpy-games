package com.gurpy.games.pojos.action;

import com.gurpy.games.gui.GurpusUI;
import com.gurpy.games.pojos.entities.BBoxElement;
import com.gurpy.games.pojos.entities.LineElement;
import com.gurpy.games.pojos.entities.UIEntity;

public class DestroyAction extends UIAction{

    private final GurpusUI contentPane;

    public DestroyAction(UIEntity owner, GurpusUI contentPane) {
        super(owner);
        this.contentPane = contentPane;
    }

    @Override
    public boolean perform() {
        if (getOwner() instanceof BBoxElement || getOwner() instanceof LineElement) {
            contentPane.getGuiElements().remove((UIEntity)getOwner());
            return true;
        } else {
            return false;
        }
    }

    public GurpusUI getContentPane() {
        return contentPane;
    }
}
