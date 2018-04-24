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

    int shootDir;

    public ShootAction(UIElement owner, GurpusUI contentPane, int shootDir) {
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
        int laserDir;
        double bulletOffset = player.getBulletHeight();
        if (i % 2 == 1)
            laserDir = (shootDir + (i + 1) / 2) % 8;
        else
            laserDir = (8 - (i / 2) + shootDir) % 8;

        switch (laserDir) {
            case Direction.UP:
                getContentPane().getGuiElements().add(new Laser(
                        new Point2D.Double(player.getX() + player.getWidth() / 2 + doubleOffset, player.getY() + bulletOffset),
                        new Point2D.Double(player.getX() + player.getWidth() / 2 + doubleOffset, player.getY() - bulletOffset),
                        player.getLaserBorder(),
                        player.getLaserColor(),
                        player.getBulletWidth(),
                        player.getShotSpeed(),
                        laserDir,
                        player));
                break;
            case Direction.UP_RIGHT:
                getContentPane().getGuiElements().add(new Laser(
                        new Point2D.Double(player.getX() + player.getWidth() + bulletOffset + doubleOffset / 2, player.getY() - bulletOffset + doubleOffset / 2),
                        new Point2D.Double(player.getX() + player.getWidth() - bulletOffset + doubleOffset / 2, player.getY() + bulletOffset + doubleOffset / 2),
                        player.getLaserBorder(),
                        player.getLaserColor(),
                        player.getBulletWidth(),
                        player.getShotSpeed(),
                        laserDir,
                        player));
                break;
            case Direction.RIGHT:
                getContentPane().getGuiElements().add(new Laser(
                        new Point2D.Double(player.getX() + player.getWidth() + bulletOffset, player.getY() + player.getHeight() / 2 + doubleOffset),
                        new Point2D.Double(player.getX() + player.getWidth() - bulletOffset, player.getY() + player.getHeight() / 2 + doubleOffset),
                        player.getLaserBorder(),
                        player.getLaserColor(),
                        player.getBulletWidth(),
                        player.getShotSpeed(),
                        laserDir,
                        player));
                break;
            case Direction.DOWN_RIGHT:
                getContentPane().getGuiElements().add(new Laser(
                        new Point2D.Double(player.getX() + player.getWidth() + bulletOffset + doubleOffset / 2, player.getY() + player.getHeight() + bulletOffset - doubleOffset / 2),
                        new Point2D.Double(player.getX() + player.getWidth() - bulletOffset + doubleOffset / 2, player.getY() + player.getHeight() - bulletOffset - doubleOffset / 2),
                        player.getLaserBorder(),
                        player.getLaserColor(),
                        player.getBulletWidth(),
                        player.getShotSpeed(),
                        laserDir,
                        player));
                break;
            case Direction.DOWN:
                getContentPane().getGuiElements().add(new Laser(
                        new Point2D.Double(player.getX() + player.getWidth() / 2 + doubleOffset, player.getY() + player.getHeight() + bulletOffset),
                        new Point2D.Double(player.getX() + player.getWidth() / 2 + doubleOffset, player.getY() + player.getHeight() - bulletOffset),
                        player.getLaserBorder(),
                        player.getLaserColor(),
                        player.getBulletWidth(),
                        player.getShotSpeed(),
                        laserDir,
                        player));
                break;
            case Direction.DOWN_LEFT:
                getContentPane().getGuiElements().add(new Laser(
                        new Point2D.Double(player.getX() + bulletOffset + doubleOffset / 2, player.getY() + player.getHeight() - bulletOffset + doubleOffset / 2),
                        new Point2D.Double(player.getX() - bulletOffset + doubleOffset / 2, player.getY() + player.getHeight() + bulletOffset + doubleOffset / 2),
                        player.getLaserBorder(),
                        player.getLaserColor(),
                        player.getBulletWidth(),
                        player.getShotSpeed(),
                        laserDir,
                        player));
                break;
            case Direction.LEFT:
                getContentPane().getGuiElements().add(new Laser(
                        new Point2D.Double(player.getX() + bulletOffset, player.getY() + player.getHeight() / 2 + doubleOffset),
                        new Point2D.Double(player.getX() - bulletOffset, player.getY() + player.getHeight() / 2 + doubleOffset),
                        player.getLaserBorder(),
                        player.getLaserColor(),
                        player.getBulletWidth(),
                        player.getShotSpeed(),
                        laserDir,
                        player));
                break;
            case Direction.UP_LEFT:
                getContentPane().getGuiElements().add(new Laser(
                        new Point2D.Double(player.getX() + bulletOffset + doubleOffset / 2, player.getY() + bulletOffset - doubleOffset / 2),
                        new Point2D.Double(player.getX() - bulletOffset + doubleOffset / 2, player.getY() - bulletOffset - doubleOffset / 2),
                        player.getLaserBorder(),
                        player.getLaserColor(),
                        player.getBulletWidth(),
                        player.getShotSpeed(),
                        laserDir,
                        player));
                break;
            default:
                getContentPane().getGuiElements().add(new Laser(
                        new Point2D.Double(player.getX() + player.getWidth() / 2 + doubleOffset, player.getY() + bulletOffset),
                        new Point2D.Double(player.getX() + player.getWidth() / 2 + doubleOffset, player.getY() - bulletOffset),
                        player.getLaserBorder(),
                        player.getLaserColor(),
                        player.getBulletWidth(),
                        player.getShotSpeed(),
                        laserDir,
                        player));
                break;
        }
    }


}
