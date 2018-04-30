package com.gurpy.games.obj.action.movement;

import com.gurpy.games.core.GurpusCore;
import com.gurpy.games.gui.GurpusUI;
import com.gurpy.games.obj.action.UIAction;
import com.gurpy.games.obj.action.control.DestroyAction;
import com.gurpy.games.obj.entities.line.weapon.Laser;
import com.gurpy.games.obj.entities.ui.Projectile;

import java.awt.geom.Point2D;

public class UpdateProjectileAction extends UIAction {
    
    private GurpusUI contentPane;
    
    public UpdateProjectileAction(Laser owner, GurpusUI contentPane) {
        super(owner);
        this.contentPane = contentPane;
    }

    @Override
    public boolean perform() {
        Projectile projectile = (Projectile)getOwner();
        //Check laser is still alive.
        projectile.setStepsAlive(projectile.getStepsAlive() + 1);
        if (projectile.getStepsAlive() > projectile.getOwner().getWeapon().getRange() * GurpusCore.STEPS_PER_SEC - 1) {
            new DestroyAction(projectile, contentPane).perform();
            return true;
        }
        return new TranslationAction(projectile, new Point2D.Double(projectile.getX() + projectile.getHspeed(),
                projectile.getY() + projectile.getVspeed())).perform();
    }
}
