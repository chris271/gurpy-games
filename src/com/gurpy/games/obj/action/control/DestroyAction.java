package com.gurpy.games.obj.action.control;

import com.gurpy.games.gui.GurpusUI;
import com.gurpy.games.obj.action.UIAction;
import com.gurpy.games.obj.entities.ui.UIEntity;

public class DestroyAction extends UIAction {

    private final GurpusUI contentPane;

    public DestroyAction(UIEntity owner, GurpusUI contentPane) {
        super(owner);
        this.contentPane = contentPane;
    }

    @Override
    public boolean perform() {
        return contentPane.getGuiElements().remove(getOwner());
    }
}
