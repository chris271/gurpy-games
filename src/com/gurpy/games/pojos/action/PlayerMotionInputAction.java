package com.gurpy.games.pojos.action;

import com.gurpy.games.gui.GurpusUI;
import com.gurpy.games.pojos.control.Direction;
import com.gurpy.games.pojos.entities.BBoxPlayer;
import com.gurpy.games.pojos.entities.Entity;

import java.awt.event.KeyEvent;

public class PlayerMotionInputAction extends UIAction {

    private GurpusUI contentPane;

    public PlayerMotionInputAction(Entity owner, GurpusUI contentPane) {
        super(owner);
        this.contentPane = contentPane;
    }

    @Override
    public boolean perform() {
        if (getOwner() instanceof BBoxPlayer) {

            BBoxPlayer player = (BBoxPlayer)getOwner();
            //Movement
            boolean movePlayer = true;

            if (contentPane.getKeyCodes().contains(KeyEvent.VK_W) &&
                    contentPane.getKeyCodes().contains(KeyEvent.VK_D)) {

                player.setDirection(Direction.UP_RIGHT);

            } else if (contentPane.getKeyCodes().contains(KeyEvent.VK_W) &&
                    contentPane.getKeyCodes().contains(KeyEvent.VK_A)) {

                player.setDirection(Direction.UP_LEFT);

            } else if (contentPane.getKeyCodes().contains(KeyEvent.VK_S) &&
                    contentPane.getKeyCodes().contains(KeyEvent.VK_D)) {

                player.setDirection(Direction.DOWN_RIGHT);

            } else if (contentPane.getKeyCodes().contains(KeyEvent.VK_S) &&
                    contentPane.getKeyCodes().contains(KeyEvent.VK_A)) {

                player.setDirection(Direction.DOWN_LEFT);

            } else if (contentPane.getKeyCodes().contains(KeyEvent.VK_W)) {

                player.setDirection(Direction.UP);

            } else if (contentPane.getKeyCodes().contains(KeyEvent.VK_D)) {

                player.setDirection(Direction.RIGHT);

            } else if (contentPane.getKeyCodes().contains(KeyEvent.VK_S)) {

                player.setDirection(Direction.DOWN);

            } else if (contentPane.getKeyCodes().contains(KeyEvent.VK_A)) {

                player.setDirection(Direction.LEFT);

            } else {
                movePlayer = false;
            }

            return movePlayer && new MoveFocusedAction(player, contentPane.getCamera(),
                    contentPane.getGuiElements(),
                    Math.cos(player.getDirection()) * player.getHspeed(),
                    Math.sin(player.getDirection()) * player.getVspeed()).perform();

        } else {
            return false;
        }
    }
}
