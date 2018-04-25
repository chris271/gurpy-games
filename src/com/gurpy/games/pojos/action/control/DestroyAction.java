package com.gurpy.games.pojos.action.control;

import com.gurpy.games.gui.GurpusUI;
import com.gurpy.games.pojos.action.UIAction;
import com.gurpy.games.pojos.entities.UIElement;

public class DestroyAction extends UIAction {

    private final GurpusUI contentPane;

    public DestroyAction(UIElement owner, GurpusUI contentPane) {
        super(owner);
        this.contentPane = contentPane;
    }

    @Override
    public boolean perform() {
        if (getOwner() instanceof UIElement) {
            contentPane.getGuiElements().remove((UIElement)getOwner());
            return true;
        } else {
            return false;
        }
    }

    public GurpusUI getContentPane() {
        return contentPane;
    }
}
