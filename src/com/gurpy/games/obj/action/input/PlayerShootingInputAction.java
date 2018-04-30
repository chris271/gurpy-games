package com.gurpy.games.obj.action.input;

import com.gurpy.games.core.GurpusCore;
import com.gurpy.games.gui.GurpusUI;
import com.gurpy.games.obj.action.attack.ShootAction;
import com.gurpy.games.obj.action.UIAction;
import com.gurpy.games.obj.control.movement.Direction;
import com.gurpy.games.obj.entities.bbox.playable.BBoxPlayer;
import com.gurpy.games.obj.entities.Entity;
import com.gurpy.games.obj.entities.ui.UIEntity;
import com.gurpy.games.utils.UtilFunctions;

import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;

public class PlayerShootingInputAction extends UIAction {

    private GurpusUI contentPane;

    public PlayerShootingInputAction(UIEntity owner, GurpusUI contentPane) {
        super(owner);
        this.contentPane = contentPane;
    }

    @Override
    public boolean perform() {
        if (getOwner() instanceof BBoxPlayer) {

            BBoxPlayer player = (BBoxPlayer)getOwner();
            //Shooting
            double shootDir = -1;
            if (!contentPane.mouseClick()) {
                if (contentPane.getKeyCodes().contains(KeyEvent.VK_UP) &&
                        contentPane.getKeyCodes().contains(KeyEvent.VK_RIGHT)) {

                    shootDir = Direction.UP_RIGHT;

                }
                else if (contentPane.getKeyCodes().contains(KeyEvent.VK_UP) &&
                        contentPane.getKeyCodes().contains(KeyEvent.VK_LEFT)) {

                    shootDir = Direction.UP_LEFT;

                }
                else if (contentPane.getKeyCodes().contains(KeyEvent.VK_DOWN) &&
                        contentPane.getKeyCodes().contains(KeyEvent.VK_RIGHT)) {

                    shootDir = Direction.DOWN_RIGHT;

                }
                else if (contentPane.getKeyCodes().contains(KeyEvent.VK_DOWN) &&
                        contentPane.getKeyCodes().contains(KeyEvent.VK_LEFT)) {

                    shootDir = Direction.DOWN_LEFT;

                }
                else if (contentPane.getKeyCodes().contains(KeyEvent.VK_UP)) {

                    shootDir = Direction.UP;

                }
                else if (contentPane.getKeyCodes().contains(KeyEvent.VK_RIGHT)) {

                    shootDir = Direction.RIGHT;

                }
                else if (contentPane.getKeyCodes().contains(KeyEvent.VK_DOWN)) {

                    shootDir = Direction.DOWN;

                }
                else if (contentPane.getKeyCodes().contains(KeyEvent.VK_LEFT)) {

                    shootDir = Direction.LEFT;

                }
            } else {
                shootDir = UtilFunctions.getAngle(
                        new Point2D.Double(contentPane.getMouseX(), contentPane.getMouseY()),
                        new Point2D.Double(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2));

            }

            if (player.getWeapon().getStepsSinceShot() < (GurpusCore.STEPS_PER_SEC / player.getWeapon().getFireRate())) {
                player.getWeapon().setStepsSinceShot(player.getWeapon().getStepsSinceShot() + 1);
            }
            if (shootDir > -1 && player.getWeapon().getStepsSinceShot() > (GurpusCore.STEPS_PER_SEC / player.getWeapon().getFireRate()) - 1) {
                player.getWeapon().setStepsSinceShot(0);
                return new ShootAction(player, contentPane, shootDir).perform();
            }

            return false;

        } else {
            return false;
        }
    }

}
