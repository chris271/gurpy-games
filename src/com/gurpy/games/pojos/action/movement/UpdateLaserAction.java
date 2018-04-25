package com.gurpy.games.pojos.action.movement;

import com.gurpy.games.core.GurpusCore;
import com.gurpy.games.gui.GurpusUI;
import com.gurpy.games.pojos.action.UIAction;
import com.gurpy.games.pojos.action.control.DestroyAction;
import com.gurpy.games.pojos.entities.BBoxPlayer;
import com.gurpy.games.pojos.entities.Laser;

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
        if (laser.getOwner() instanceof BBoxPlayer &&
                laser.getStepsAlive() > ((BBoxPlayer)laser.getOwner()).getRange() * GurpusCore.STEPS_PER_SEC - 1) {
            new DestroyAction(laser, contentPane).perform();
            return true;
        }
        return new TranslationAction(laser, new Point2D.Double(laser.getLines().get(0).getP1().getX() + laser.getHspeed(),
                laser.getLines().get(0).getP1().getY() + laser.getVspeed())).perform();
    }
}
