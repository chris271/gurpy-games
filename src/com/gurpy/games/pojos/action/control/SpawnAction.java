package com.gurpy.games.pojos.action.control;

import com.gurpy.games.gui.GurpusUI;
import com.gurpy.games.pojos.action.UIAction;
import com.gurpy.games.pojos.entities.BBoxElement;
import com.gurpy.games.pojos.entities.LineElement;
import com.gurpy.games.pojos.entities.UIElement;

public class SpawnAction extends UIAction {

    private final GurpusUI contentPane;

    public SpawnAction(UIElement owner, GurpusUI contentPane) {
        super(owner);
        this.contentPane = contentPane;
    }

    @Override
    public boolean perform() {
        return contentPane.getGuiElements().add((UIElement) getOwner());
    }

    public GurpusUI getContentPane() {
        return contentPane;
    }
}
