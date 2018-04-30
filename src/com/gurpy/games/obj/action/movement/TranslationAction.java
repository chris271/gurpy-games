package com.gurpy.games.obj.action.movement;

import com.gurpy.games.obj.action.UIAction;
import com.gurpy.games.obj.entities.bbox.BBoxElement;
import com.gurpy.games.obj.entities.line.LineElement;
import com.gurpy.games.obj.entities.line.weapon.Laser;
import com.gurpy.games.obj.entities.menu.Menu;
import com.gurpy.games.obj.entities.text.TextElement;
import com.gurpy.games.obj.entities.ui.UIEntity;

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
        } else if (getOwner() instanceof LineElement) {
            Laser laser = (Laser)getOwner();
            laser.setPosition(newPos);
            return true;
        } else if (getOwner() instanceof TextElement) {
            TextElement textElement = (TextElement)getOwner();
            textElement.setPosition(newPos);
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
