package com.gurpy.games.pojos.action;

import com.gurpy.games.gui.GurpusUI;
import com.gurpy.games.pojos.control.Direction;
import com.gurpy.games.pojos.entities.Laser;
import com.gurpy.games.pojos.entities.BBoxPlayer;
import com.gurpy.games.pojos.entities.UIElement;
import com.gurpy.games.pojos.entities.UIEntity;
import com.gurpy.games.utils.Logger;

import java.awt.geom.Point2D;

public class ShootAction extends SpawnAction {

    private double shootDir;

    public ShootAction(UIElement owner, GurpusUI contentPane, double shootDir) {
        super(owner, contentPane);
        this.shootDir = shootDir;
    }

    @Override
    public boolean perform() {
        if (getOwner() instanceof BBoxPlayer) {
            BBoxPlayer player = (BBoxPlayer) getOwner();
            for (int i = 0; i < player.getNumBullets(); i++) {
                if (player.isDoubleShot()) {
                    addBullet(player, i, -(player.getBulletWidth() + 5));
                    addBullet(player, i, 5);
                } else {
                    addBullet(player, i, 0);
                }
            }
        }
        return false;
    }

    private void addBullet(BBoxPlayer player, int i, double doubleOffset) {

        double laserDir;
        if (i % 2 == 1)
            laserDir = (shootDir + Math.toRadians((i + 1) / 2 * 45)) % Math.toRadians(360);
        else
            laserDir = (shootDir - Math.toRadians((i + 1) / 2 * 45)) % Math.toRadians(360);

        double bulletXOffset = Math.cos(laserDir) * player.getBulletHeight();
        double bulletYOffset = Math.sin(laserDir) * player.getBulletHeight();
        double doubleXOffset = Math.sin(laserDir) * doubleOffset;
        double doubleYOffset = Math.cos(laserDir) * doubleOffset;
        double playerXOffset = player.getWidth() / 2 + (Math.cos(laserDir) * (player.getWidth() / 2));
        double playerYOffset = player.getHeight() / 2 + (Math.sin(laserDir) * (player.getHeight() / 2));

        getContentPane().getGuiElements().add(new Laser(
                new Point2D.Double(player.getX() + bulletXOffset - doubleXOffset + playerXOffset,
                        player.getY() + bulletYOffset + doubleYOffset + playerYOffset),
                new Point2D.Double(player.getX() - bulletXOffset - doubleXOffset + playerXOffset,
                        player.getY() - bulletYOffset + doubleYOffset + playerYOffset),
                player.getLaserBorder(),
                player.getLaserColor(),
                player.getBulletWidth(),
                player.getShotSpeed(),
                laserDir,
                player));

    }


}
