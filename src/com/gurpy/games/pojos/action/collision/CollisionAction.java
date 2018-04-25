package com.gurpy.games.pojos.action.collision;

import com.gurpy.games.pojos.action.UIAction;
import com.gurpy.games.pojos.entities.BBoxPlayer;
import com.gurpy.games.pojos.entities.Laser;
import com.gurpy.games.pojos.entities.UIEntity;


public class CollisionAction extends UIAction {

    public CollisionAction(UIEntity owner) {
        super(owner);
    }

    @Override
    public boolean perform() {
        if (getOwner() instanceof BBoxPlayer) {
            BBoxPlayer player = (BBoxPlayer)getOwner();
            for (UIEntity collision : player.getCollisions()) {
                if (collision instanceof Laser && !((Laser)collision).getOwner().equals(player)) {
                    player.setDestroy(true);
                }
            }
            player.getCollisions().clear();
            return true;
        }
        return false;
    }

}
