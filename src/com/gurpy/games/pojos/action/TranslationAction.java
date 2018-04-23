package com.gurpy.games.pojos.action;

import com.gurpy.games.pojos.entities.BBoxElement;
import com.gurpy.games.pojos.entities.Laser;
import com.gurpy.games.pojos.entities.LineElement;
import com.gurpy.games.pojos.entities.UIEntity;

import java.awt.geom.Point2D;

public class TranslationAction extends UIAction {

    private Point2D.Double newPos;

    public TranslationAction(UIEntity owner, Point2D.Double newPos) {
        super(owner);
        this.newPos = newPos;
    }

    @Override
    public boolean perform() {
        if (getOwner() instanceof BBoxElement) {
            BBoxElement boxElem = (BBoxElement)getOwner();
            boxElem.setPosition(newPos);
            return true;
        } else if (getOwner() instanceof Laser) {
            Laser laser = (Laser) getOwner();
            laser.setPosition(newPos);
            return true;
        } else {
            return false;
        }
    }
}
