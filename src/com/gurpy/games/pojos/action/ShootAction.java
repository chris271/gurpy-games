package com.gurpy.games.pojos.action;

import com.gurpy.games.gui.GurpusUI;
import com.gurpy.games.pojos.control.Direction;
import com.gurpy.games.pojos.entities.Laser;
import com.gurpy.games.pojos.entities.BBoxPlayer;
import com.gurpy.games.pojos.entities.UIEntity;

import java.awt.geom.Point2D;

public class ShootAction extends SpawnAction {
    
    public ShootAction(UIEntity owner, GurpusUI contentPane) {
        super(owner, contentPane);
    }

    @Override
    public boolean perform() {
        if (getOwner() instanceof BBoxPlayer) {
            BBoxPlayer player = (BBoxPlayer) getOwner();
            for (int i = 0; i < player.getNumBullets(); i++) {

                int laserDir;
                double bulletOffset = player.getBulletHeight();
                if (i % 2 == 1)
                    laserDir = (player.getDirection() + (i + 1) / 2) % 8;
                else
                    laserDir = (8 - (i / 2) + player.getDirection()) % 8;

                switch (laserDir) {
                    case Direction.UP:
                        getContentPane().getGuiElements().add(new Laser(
                                new Point2D.Double(player.getX() + player.getWidth() / 2, player.getY() + bulletOffset),
                                new Point2D.Double(player.getX() + player.getWidth() / 2, player.getY() - bulletOffset),
                                player.getBorderColor(),
                                player.getBgColor(),
                                player.getBulletWidth(),
                                player.getShotSpeed(),
                                laserDir,
                                player));
                        break;
                    case Direction.UP_RIGHT:
                        getContentPane().getGuiElements().add(new Laser(
                                new Point2D.Double(player.getX() + player.getWidth() + bulletOffset, player.getY() - bulletOffset),
                                new Point2D.Double(player.getX() + player.getWidth() - bulletOffset, player.getY() + bulletOffset),
                                player.getBorderColor(),
                                player.getBgColor(),
                                player.getBulletWidth(),
                                player.getShotSpeed(),
                                laserDir,
                                player));
                        break;
                    case Direction.RIGHT:
                        getContentPane().getGuiElements().add(new Laser(
                                new Point2D.Double(player.getX() + player.getWidth() + bulletOffset, player.getY() + player.getHeight() / 2),
                                new Point2D.Double(player.getX() + player.getWidth() - bulletOffset, player.getY() + player.getHeight() / 2),
                                player.getBorderColor(),
                                player.getBgColor(),
                                player.getBulletWidth(),
                                player.getShotSpeed(),
                                laserDir,
                                player));
                        break;
                    case Direction.DOWN_RIGHT:
                        getContentPane().getGuiElements().add(new Laser(
                                new Point2D.Double(player.getX() + player.getWidth() + bulletOffset, player.getY() + player.getHeight() + bulletOffset),
                                new Point2D.Double(player.getX() + player.getWidth() - bulletOffset, player.getY() + player.getHeight() - bulletOffset),
                                player.getBorderColor(),
                                player.getBgColor(),
                                player.getBulletWidth(),
                                player.getShotSpeed(),
                                laserDir,
                                player));
                        break;
                    case Direction.DOWN:
                        getContentPane().getGuiElements().add(new Laser(
                                new Point2D.Double(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() + bulletOffset),
                                new Point2D.Double(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() - bulletOffset),
                                player.getBorderColor(),
                                player.getBgColor(),
                                player.getBulletWidth(),
                                player.getShotSpeed(),
                                laserDir,
                                player));
                        break;
                    case Direction.DOWN_LEFT:
                        getContentPane().getGuiElements().add(new Laser(
                                new Point2D.Double(player.getX() + bulletOffset, player.getY() + player.getHeight() - bulletOffset),
                                new Point2D.Double(player.getX() - bulletOffset, player.getY() + player.getHeight() + bulletOffset),
                                player.getBorderColor(),
                                player.getBgColor(),
                                player.getBulletWidth(),
                                player.getShotSpeed(),
                                laserDir,
                                player));
                        break;
                    case Direction.LEFT:
                        getContentPane().getGuiElements().add(new Laser(
                                new Point2D.Double(player.getX() + bulletOffset, player.getY() + player.getHeight() / 2),
                                new Point2D.Double(player.getX() - bulletOffset, player.getY() + player.getHeight() / 2),
                                player.getBorderColor(),
                                player.getBgColor(),
                                player.getBulletWidth(),
                                player.getShotSpeed(),
                                laserDir,
                                player));
                        break;
                    case Direction.UP_LEFT:
                        getContentPane().getGuiElements().add(new Laser(
                                new Point2D.Double(player.getX() + bulletOffset, player.getY() + bulletOffset),
                                new Point2D.Double(player.getX() - bulletOffset, player.getY() - bulletOffset),
                                player.getBorderColor(),
                                player.getBgColor(),
                                player.getBulletWidth(),
                                player.getShotSpeed(),
                                laserDir,
                                player));
                        break;
                    default:
                        getContentPane().getGuiElements().add(new Laser(
                                new Point2D.Double(player.getX() + player.getWidth() / 2, player.getY() + bulletOffset),
                                new Point2D.Double(player.getX() + player.getWidth() / 2, player.getY() - bulletOffset),
                                player.getBorderColor(),
                                player.getBgColor(),
                                player.getBulletWidth(),
                                player.getShotSpeed(),
                                laserDir,
                                player));
                        break;
                }
            }
        }
        return false;
    }
}
