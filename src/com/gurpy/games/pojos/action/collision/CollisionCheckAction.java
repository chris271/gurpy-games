package com.gurpy.games.pojos.action.collision;

import com.gurpy.games.pojos.action.UIAction;
import com.gurpy.games.pojos.entities.BBoxElement;
import com.gurpy.games.pojos.entities.BBoxPlayer;
import com.gurpy.games.pojos.entities.Laser;
import com.gurpy.games.pojos.entities.UIEntity;

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
        if (getOwner() instanceof BBoxElement && other instanceof Laser) {
            BBoxElement bBoxElement = (BBoxElement)getOwner();
            Laser laser = (Laser)other;
            for (Line2D line2D : laser.getLines()) {
                if (bBoxElement.getBBox().intersectsLine(line2D)) {
                    bBoxElement.addCollision(laser);
                }
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
