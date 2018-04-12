package com.gurpy.games.pojos.action;

import com.gurpy.games.pojos.entities.BBoxElement;
import com.gurpy.games.pojos.entities.UIEntity;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class DrawAction extends UIAction {

    private Graphics2D g2d;

    public DrawAction(UIEntity owner, Graphics2D g2d) {
        super(owner);
        this.g2d = g2d;
    }

    @Override
    public boolean perform() {
        if (getOwner() instanceof BBoxElement) {
            BBoxElement boxElem = (BBoxElement)getOwner();
            //Draw border as a filled rectangle.
            g2d.setColor(boxElem.getBorderColor());
            g2d.fill(new Rectangle2D.Double(
                    boxElem.getX(),
                    boxElem.getY(),
                    boxElem.getWidth(),
                    boxElem.getHeight()));
            //Draw background as a filled rectangle inside of the border rectangle.
            g2d.setColor(boxElem.getBgColor());
            g2d.fill(new Rectangle2D.Double(
                    boxElem.getX() + boxElem.getBorderThickness(),
                    boxElem.getY() + boxElem.getBorderThickness(),
                    boxElem.getWidth() - 2 * boxElem.getBorderThickness(),
                    boxElem.getHeight() - 2 * boxElem.getBorderThickness()));
            return true;
        } else {
            return false;
        }
    }
}
