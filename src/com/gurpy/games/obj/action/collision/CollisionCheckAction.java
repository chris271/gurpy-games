package com.gurpy.games.obj.action.collision;

import com.gurpy.games.obj.action.UIAction;
import com.gurpy.games.obj.entities.bbox.BBoxElement;
import com.gurpy.games.obj.entities.bbox.playable.BBoxEnemy;
import com.gurpy.games.obj.entities.line.LineElement;
import com.gurpy.games.obj.entities.line.weapon.Laser;
import com.gurpy.games.obj.entities.ui.Playable;
import com.gurpy.games.obj.entities.ui.UIEntity;

import java.awt.geom.Line2D;

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
                    if (bBoxElement.getBBox().intersectsLine(line2D)) {
                        doCollision();
                        return true;
                    }
                }
            } else if (other instanceof BBoxElement) {
                BBoxElement bBoxElement = (BBoxElement) getOwner();
                BBoxElement bBoxElement1 = (BBoxElement) other;
                if (bBoxElement.getBBox().intersects(bBoxElement1.getBBox())) {
                    doCollision();
                    return true;
                }
            }
        } else if (getOwner() instanceof LineElement) {
            return true;
        }
        return false;
    }

    public UIEntity getOther() {
        return other;
    }

    public void setOther(UIEntity other) {
        this.other = other;
    }

    private void doCollision() {
        if (getOwner() instanceof Playable) {
            Playable player = (Playable)getOwner();
            if (other instanceof Laser) {
                Playable laserOwner = ((Laser)other).getOwner();
                if (!(player.equals(laserOwner))) {
                    if (player.getHealth() > 0) {
                        player.setHealth(player.getHealth() - .1);
                    } else {
                        player.setDestroy(true);
                        laserOwner.setKillCount(laserOwner.getKillCount() + 1);
                        laserOwner.setScore(laserOwner.getScore() + player.getScore());
                    }
                }

            } else if (other instanceof BBoxEnemy) {
                if (player.isControllable()) {
                    player.setHealth(player.getHealth() - .1);
                }
            }
        }
    }

}
