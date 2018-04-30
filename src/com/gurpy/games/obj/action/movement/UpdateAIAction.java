package com.gurpy.games.obj.action.movement;

import com.gurpy.games.obj.action.UIAction;
import com.gurpy.games.obj.entities.bbox.playable.BBoxEnemy;
import com.gurpy.games.obj.entities.bbox.playable.EnemyTypes;
import com.gurpy.games.obj.entities.ui.Playable;
import com.gurpy.games.utils.UtilFunctions;

import java.awt.geom.Point2D;

public class UpdateAIAction extends UIAction {

    private Playable target;

    public UpdateAIAction(Playable owner, Playable target) {
        super(owner);
        this.target = target;
    }

    @Override
    public boolean perform() {

        if (getOwner() instanceof BBoxEnemy) {
            BBoxEnemy enemy = (BBoxEnemy)getOwner();
            if (enemy.getEnemyType() == EnemyTypes.HOMING) {
                double angle = UtilFunctions.getAngle(target.getPosition(), enemy.getPosition());
                enemy.setDirection(angle);
                enemy.setRelativeX(enemy.getX() + Math.cos(enemy.getDirection()) * enemy.getHspeed());
                enemy.setRelativeY(enemy.getY() + Math.sin(enemy.getDirection()) * enemy.getVspeed());
                new TranslationAction(enemy, new Point2D.Double(
                        enemy.getX() + Math.cos(enemy.getDirection()) * enemy.getHspeed(),
                        enemy.getY() + Math.sin(enemy.getDirection()) * enemy.getVspeed())).perform();
                return true;
            }
        }
        return false;

    }
}
