package com.gurpy.games.obj.entities.weapon;

import com.gurpy.games.obj.entities.line.weapon.Laser;
import com.gurpy.games.obj.entities.ui.Playable;
import com.gurpy.games.obj.entities.ui.Projectile;

import java.awt.*;
import java.awt.geom.Point2D;

public class LaserRifle extends Weapon {

    public LaserRifle(Playable owner, Color borderColor, Color bgColor) {
        super(
                owner,
                .1,
                1,
                5,
                25,
                25,
                50,
                1,
                false,
                1
        );
        setBorderColor(borderColor);
        setBgColor(bgColor);
    }

    public LaserRifle(Playable owner, Color color) {
        super(
                owner,
                .1,
                1,
                5,
                25,
                25,
                50,
                1,
                false,
                1
        );
        setBorderColor(color);
        setBgColor(color);
    }

    public LaserRifle(Playable owner) {
        super(
                owner,
                .1,
                1,
                5,
                25,
                25,
                50,
                1,
                false,
                1
        );
    }

    @Override
    public Projectile getProjectile(double projectileDirection, double doubleShotOffset) {

        double bulletXOffset = Math.cos(projectileDirection) * getProjectileHeight();
        double bulletYOffset = Math.sin(projectileDirection) * getProjectileHeight();
        double doubleXOffset = Math.sin(projectileDirection) * doubleShotOffset;
        double doubleYOffset = Math.cos(projectileDirection) * doubleShotOffset;
        double playerXOffset = getOwner().getWidth() / 2 + (Math.cos(projectileDirection) * (getOwner().getWidth() / 2));
        double playerYOffset = getOwner().getHeight() / 2 + (Math.sin(projectileDirection) * (getOwner().getHeight() / 2));

        return new Laser(
                new Point2D.Double(getOwner().getX() + bulletXOffset - doubleXOffset + playerXOffset,
                        getOwner().getY() + bulletYOffset + doubleYOffset + playerYOffset),
                new Point2D.Double(getOwner().getX() - bulletXOffset - doubleXOffset + playerXOffset,
                        getOwner().getY() - bulletYOffset + doubleYOffset + playerYOffset),
                getBorderColor(),
                getBgColor(),
                getOwner().getWeapon().getProjectileWidth(),
                getOwner().getWeapon().getShotSpeed(),
                projectileDirection,
                getOwner());
    }
}
