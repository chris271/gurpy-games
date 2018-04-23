package com.gurpy.games.pojos.action;

import com.gurpy.games.pojos.entities.BBoxPlayer;
import com.gurpy.games.pojos.entities.Laser;
import com.gurpy.games.pojos.entities.UIEntity;

import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class CollisionAction extends UIAction{

    private UIEntity other;

    public CollisionAction(UIEntity owner, UIEntity other) {
        super(owner);
        this.other = other;
    }

    @Override
    public boolean perform() {
        if (getOwner() instanceof BBoxPlayer && other instanceof Laser) {
            BBoxPlayer player = (BBoxPlayer)getOwner();
            Laser laser = (Laser)other;
            for (Line2D line2D : laser.getLines()) {
                if (player.getBBox().intersectsLine(line2D)) {
                    player.addCollision(laser);
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
