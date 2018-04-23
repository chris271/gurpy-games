package com.gurpy.games.pojos.action;

import com.gurpy.games.gui.GurpusUI;
import com.gurpy.games.pojos.control.Direction;
import com.gurpy.games.pojos.entities.Laser;
import com.gurpy.games.pojos.entities.Player;
import com.gurpy.games.pojos.entities.UIEntity;

import java.awt.geom.Point2D;

public class ShootAction extends SpawnAction {
    
    public ShootAction(UIEntity owner, GurpusUI contentPane) {
        super(owner, contentPane);
    }

    @Override
    public boolean perform() {
        if (getOwner() instanceof Player) {
            Player player = (Player) getOwner();
            int playerDir = player.getDirection();
            switch (playerDir) {
                case Direction.UP:
                    getContentPane().getGuiElements().add(new Laser(
                            new Point2D.Double(player.getX() + player.getWidth() / 2, player.getY() + player.getBulletHeight()),
                            new Point2D.Double(player.getX() + player.getWidth() / 2, player.getY() - player.getBulletHeight()),
                            player.getBorderColor(),
                            player.getBgColor(),
                            player.getBulletWidth(),
                            player.getShotSpeed(),
                            player.getDirection(),
                            player));
                    break;
                case Direction.UP_RIGHT:
                    getContentPane().getGuiElements().add(new Laser(
                            new Point2D.Double(player.getX() + player.getWidth() + player.getBulletHeight(), player.getY() - player.getBulletHeight()),
                            new Point2D.Double(player.getX() + player.getWidth() - player.getBulletHeight(), player.getY() + player.getBulletHeight()),
                            player.getBorderColor(),
                            player.getBgColor(),
                            player.getBulletWidth(),
                            player.getShotSpeed(),
                            player.getDirection(),
                            player));
                    break;
                case Direction.RIGHT:
                    getContentPane().getGuiElements().add(new Laser(
                            new Point2D.Double(player.getX() + player.getWidth() + player.getBulletHeight(), player.getY() + player.getHeight() / 2),
                            new Point2D.Double(player.getX() + player.getWidth() - player.getBulletHeight(), player.getY() + player.getHeight() / 2),
                            player.getBorderColor(),
                            player.getBgColor(),
                            player.getBulletWidth(),
                            player.getShotSpeed(),
                            player.getDirection(),
                            player));
                    break;
                case Direction.DOWN_RIGHT:
                    getContentPane().getGuiElements().add(new Laser(
                            new Point2D.Double(player.getX() + player.getWidth() + player.getBulletHeight(), player.getY() + player.getHeight() + player.getBulletHeight()),
                            new Point2D.Double(player.getX() + player.getWidth() - player.getBulletHeight(), player.getY() + player.getHeight() - player.getBulletHeight()),
                            player.getBorderColor(),
                            player.getBgColor(),
                            player.getBulletWidth(),
                            player.getShotSpeed(),
                            player.getDirection(),
                            player));
                    break;
                case Direction.DOWN:
                    getContentPane().getGuiElements().add(new Laser(
                            new Point2D.Double(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() + player.getBulletHeight()),
                            new Point2D.Double(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() - player.getBulletHeight()),
                            player.getBorderColor(),
                            player.getBgColor(),
                            player.getBulletWidth(),
                            player.getShotSpeed(),
                            player.getDirection(),
                            player));
                    break;
                case Direction.DOWN_LEFT:
                    getContentPane().getGuiElements().add(new Laser(
                            new Point2D.Double(player.getX() + player.getBulletHeight(), player.getY() + player.getHeight() - player.getBulletHeight()),
                            new Point2D.Double(player.getX() - player.getBulletHeight(), player.getY() + player.getHeight() + player.getBulletHeight()),
                            player.getBorderColor(),
                            player.getBgColor(),
                            player.getBulletWidth(),
                            player.getShotSpeed(),
                            player.getDirection(),
                            player));
                    break;
                case Direction.LEFT:
                    getContentPane().getGuiElements().add(new Laser(
                            new Point2D.Double(player.getX() + player.getBulletHeight(), player.getY() + player.getHeight() / 2),
                            new Point2D.Double(player.getX() - player.getBulletHeight(), player.getY() + player.getHeight() / 2),
                            player.getBorderColor(),
                            player.getBgColor(),
                            player.getBulletWidth(),
                            player.getShotSpeed(),
                            player.getDirection(),
                            player));
                    break;
                case Direction.UP_LEFT:
                    getContentPane().getGuiElements().add(new Laser(
                            new Point2D.Double(player.getX() + player.getBulletHeight(), player.getY() + player.getBulletHeight()),
                            new Point2D.Double(player.getX() - player.getBulletHeight(), player.getY() - player.getBulletHeight()),
                            player.getBorderColor(),
                            player.getBgColor(),
                            player.getBulletWidth(),
                            player.getShotSpeed(),
                            player.getDirection(),
                            player));
                    break;
                default:
                    getContentPane().getGuiElements().add(new Laser(
                            new Point2D.Double(player.getX() + player.getWidth() / 2, player.getY() + player.getBulletHeight()),
                            new Point2D.Double(player.getX() + player.getWidth() / 2, player.getY() - player.getBulletHeight()),
                            player.getBorderColor(),
                            player.getBgColor(),
                            player.getBulletWidth(),
                            player.getShotSpeed(),
                            player.getDirection(),
                            player));
                    break;
            }
        }
        return false;
    }
}
