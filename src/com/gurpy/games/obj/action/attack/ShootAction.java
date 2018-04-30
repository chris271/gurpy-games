package com.gurpy.games.obj.action.attack;

import com.gurpy.games.gui.GurpusUI;
import com.gurpy.games.obj.action.control.SpawnAction;
import com.gurpy.games.obj.entities.line.weapon.Laser;
import com.gurpy.games.obj.entities.bbox.playable.BBoxPlayer;
import com.gurpy.games.obj.entities.ui.Playable;
import com.gurpy.games.obj.entities.ui.Projectile;
import com.gurpy.games.obj.entities.ui.UIElement;

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
            for (int i = 0; i < player.getWeapon().getNumBullets(); i++) {
                if (player.getWeapon().isDoubleShot()) {
                    addProjectile(player, i, -(player.getWeapon().getProjectileWidth() + 5));
                    addProjectile(player, i, 5);
                } else {
                    addProjectile(player, i, 0);
                }
            }
        }
        return false;
    }

    private void addProjectile(Playable player, int i, double doubleOffset) {

        Projectile projectile;
        if (i % 2 == 1) {
            projectile = player.getWeapon().getProjectile(
                    (shootDir + Math.toRadians((i + 1) / 2 * 20)) % Math.toRadians(360),
                    doubleOffset);
        } else {
            projectile = player.getWeapon().getProjectile(
                    (shootDir - Math.toRadians((i + 1) / 2 * 20)) % Math.toRadians(360),
                    doubleOffset);
        }
        getContentPane().getGuiElements().add(projectile);

    }


}
