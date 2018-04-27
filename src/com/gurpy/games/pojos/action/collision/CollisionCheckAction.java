package com.gurpy.games.pojos.action.collision;

import com.gurpy.games.pojos.action.UIAction;
import com.gurpy.games.pojos.entities.*;

import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class CollisionCheckAction extends UIAction {

    private UIEntity other;

    public CollisionCheckAction(UIEntity owner, UIEntity other) {
        super(owner);
        this.other = other;
    }

    @Override
    public boolean perform() {
        if (getOwner() instanceof BBoxElement) {
            if (other instanceof LineElement) {
                BBoxElement bBoxElement = (BBoxElement) getOwner();
                LineElement lineElement = (LineElement) other;
                for (Line2D line2D : lineElement.getLines()) {
                    if (bBoxElement.getBBox().intersectsLine(line2D))
                        bBoxElement.addCollision(lineElement);
                }
            } else if (other instanceof BBoxElement) {
                BBoxElement bBoxElement = (BBoxElement) getOwner();
                BBoxElement bBoxElement1 = (BBoxElement) other;
                if (bBoxElement.getBBox().intersects(bBoxElement1.getBBox()))
                    bBoxElement.addCollision(bBoxElement1);
            }
        }
        return false;
    }

    public UIEntity getOther() {
        return other;
    }

    public void setOther(UIEntity other) {
        this.other = other;
    }

}
