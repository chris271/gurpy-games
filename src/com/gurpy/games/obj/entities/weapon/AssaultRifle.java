package com.gurpy.games.obj.entities.weapon;

import com.gurpy.games.obj.entities.bbox.weapon.Bullet;
import com.gurpy.games.obj.entities.ui.Playable;
import com.gurpy.games.obj.entities.ui.Projectile;

import java.awt.*;
import java.awt.geom.Point2D;

public class AssaultRifle extends Weapon {

    public AssaultRifle(Playable owner, Color borderColor, Color bgColor) {
        super(
                owner,
                1,
                1,
                5,
                25,
                25,
                25,
                1,
                false,
                1
        );
        setBorderColor(borderColor);
        setBgColor(bgColor);
    }

    public AssaultRifle(Playable owner, Color color) {
        super(
                owner,
                1,
                1,
                5,
                25,
                25,
                25,
                1,
                false,
                1
        );
        setBorderColor(color);
        setBgColor(color);
    }

    public AssaultRifle(Playable owner) {
        super(
                owner,
                1,
                1,
                5,
                25,
                25,
                25,
                1,
                false,
                1
        );
    }

    @Override
    public Projectile getProjectile(double projectileDirection, double doubleShotOffset) {

        double doubleXOffset = Math.sin(projectileDirection) * doubleShotOffset;
        double doubleYOffset = Math.cos(projectileDirection) * doubleShotOffset;
        double playerXOffset = getOwner().getWidth() / 2 + (Math.cos(projectileDirection) * (getOwner().getWidth() / 2));
        double playerYOffset = getOwner().getHeight() / 2 + (Math.sin(projectileDirection) * (getOwner().getHeight() / 2));

        return new Bullet(
                new Point2D.Double(getOwner().getX() - doubleXOffset + playerXOffset,
                        getOwner().getY() + doubleYOffset + playerYOffset),
                new Dimension((int)getProjectileWidth(), (int)getProjectileHeight()),
                getBorderColor(),
                getBgColor(),
                getOwner().getWeapon().getBorderThickness(),
                getOwner());
    }

}
