package com.gurpy.games.pojos.action.movement;

import com.gurpy.games.pojos.action.UIAction;
import com.gurpy.games.pojos.entities.*;

import java.awt.geom.Point2D;

public class TranslationAction extends UIAction {

    private Point2D.Double newPos;

    public TranslationAction(UIElement owner, Point2D.Double newPos) {
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
            Laser laser = (Laser)getOwner();
            laser.setPosition(newPos);
            return true;
        } else if (getOwner() instanceof Menu) {
            Menu menu = (Menu)getOwner();
            menu.setPosition(newPos);
            return true;
        } else {
            return false;
        }
    }

    public Point2D.Double getPosition() {
        return newPos;
    }

    public void setPosition(Point2D.Double newPos) {
        this.newPos = newPos;
    }
}
