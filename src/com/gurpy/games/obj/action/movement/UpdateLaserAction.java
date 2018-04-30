package com.gurpy.games.obj.action.movement;

import com.gurpy.games.core.GurpusCore;
import com.gurpy.games.gui.GurpusUI;
import com.gurpy.games.obj.action.UIAction;
import com.gurpy.games.obj.action.control.DestroyAction;
import com.gurpy.games.obj.entities.line.weapon.Laser;

import java.awt.geom.Point2D;

public class UpdateLaserAction extends UIAction {
    
    private GurpusUI contentPane;
    
    public UpdateLaserAction(Laser owner, GurpusUI contentPane) {
        super(owner);
        this.contentPane = contentPane;
    }

    @Override
    public boolean perform() {
        Laser laser = (Laser) getOwner();
        //Check laser is still alive.
        laser.setStepsAlive(laser.getStepsAlive() + 1);
        if (laser.getStepsAlive() > laser.getOwner().getRange() * GurpusCore.STEPS_PER_SEC - 1) {
            new DestroyAction(laser, contentPane).perform();
            return true;
        }
        return new TranslationAction(laser, new Point2D.Double(laser.getLines().get(0).getP1().getX() + laser.getHspeed(),
                laser.getLines().get(0).getP1().getY() + laser.getVspeed())).perform();
    }
}
