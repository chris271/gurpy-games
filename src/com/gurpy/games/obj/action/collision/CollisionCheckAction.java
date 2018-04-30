package com.gurpy.games.obj.action.collision;

import com.gurpy.games.obj.action.UIAction;
import com.gurpy.games.obj.entities.bbox.BBoxElement;
import com.gurpy.games.obj.entities.bbox.playable.BBoxEnemy;
import com.gurpy.games.obj.entities.line.LineElement;
import com.gurpy.games.obj.entities.line.weapon.Laser;
import com.gurpy.games.obj.entities.ui.Enemy;
import com.gurpy.games.obj.entities.ui.Playable;
import com.gurpy.games.obj.entities.ui.Projectile;
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
            if (other instanceof Projectile) {
                Playable projOwner = ((Projectile)other).getOwner();
                if (!(player.equals(projOwner))) {
                    if (player.getHealth() > 0) {
                        player.setHealth(player.getHealth() - .1);
                    } else {
                        player.setDestroy(true);
                        projOwner.setKillCount(projOwner.getKillCount() + 1);
                        projOwner.setScore(projOwner.getScore() + player.getScore());
                    }
                }

            } else if (other instanceof Enemy) {
                Enemy enemy = (Enemy)other;
                if (!(player instanceof Enemy)) {
                    if (player.getHealth() > 0) {
                        player.setHealth(player.getHealth() - .1);
                    } else {
                        player.setDestroy(true);
                        enemy.setKillCount(enemy.getKillCount() + 1);
                        enemy.setScore(enemy.getScore() + player.getScore());
                    }
                }
            }
        } else if (getOwner() instanceof Projectile) {
            Projectile projectile = (Projectile)getOwner();
            if (other instanceof Projectile) {
                projectile.setDestroy(true);
            } else if (other instanceof Playable) {
                if (!(projectile instanceof Laser)) {
                    projectile.setDestroy(true);
                }
            }
        }
    }

}
