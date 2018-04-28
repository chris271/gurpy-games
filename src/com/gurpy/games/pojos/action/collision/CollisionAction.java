package com.gurpy.games.pojos.action.collision;

import com.gurpy.games.pojos.action.UIAction;
import com.gurpy.games.pojos.entities.*;


public class CollisionAction extends UIAction {

    public CollisionAction(UIEntity owner) {
        super(owner);
    }

    @Override
    public boolean perform() {
        if (getOwner() instanceof BBoxPlayer) {
            BBoxPlayer player = (BBoxPlayer)getOwner();
            for (UIEntity collision : player.getCollisions()) {
                if (player.isDestroy()) {
                    break;
                }
                if (collision instanceof Laser && ((Laser)collision).getOwner() instanceof BBoxPlayer) {
                    BBoxPlayer laserOwner = (BBoxPlayer)((Laser)collision).getOwner();
                    if (!(player.equals(laserOwner))) {
                        if (player.getHealth() > 0) {
                            player.setHealth(player.getHealth() - .1);
                        } else {
                            player.setDestroy(true);
                            laserOwner.setKillCount(laserOwner.getKillCount() + 1);
                            laserOwner.setScore(laserOwner.getScore() + player.getScore());
                        }
                    }

                } else if (collision instanceof BBoxEnemy) {
                    if (!(player instanceof BBoxEnemy))
                        player.setHealth(player.getHealth() - .1);
                }
            }
            player.getCollisions().clear();
            return true;
        }
        return false;
    }

}
