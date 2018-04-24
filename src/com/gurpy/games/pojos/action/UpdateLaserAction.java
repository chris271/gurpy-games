package com.gurpy.games.pojos.action;

import com.gurpy.games.core.GurpusCore;
import com.gurpy.games.gui.GurpusUI;
import com.gurpy.games.pojos.control.Direction;
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
        //Set laser translation direction.
        int laserDir = laser.getDirection();
        TranslationAction laserTranslation = new TranslationAction(laser, new Point2D.Double(0, 0));
        switch (laserDir) {
            case Direction.UP:
                laserTranslation.setPosition(new Point2D.Double(laser.getLines().get(0).getP1().getX(),
                        laser.getLines().get(0).getP1().getY() - laser.getVspeed()));
                break;
            case Direction.UP_RIGHT:
                laserTranslation.setPosition(new Point2D.Double(laser.getLines().get(0).getP1().getX() + laser.getHspeed() / 2,
                        laser.getLines().get(0).getP1().getY() - laser.getVspeed() / 2));
                break;
            case Direction.RIGHT:
                laserTranslation.setPosition(new Point2D.Double(laser.getLines().get(0).getP1().getX() + laser.getHspeed(),
                        laser.getLines().get(0).getP1().getY()));
                break;
            case Direction.DOWN_RIGHT:
                laserTranslation.setPosition(new Point2D.Double(laser.getLines().get(0).getP1().getX() + laser.getHspeed() / 2,
                        laser.getLines().get(0).getP1().getY() + laser.getVspeed() / 2));
                break;
            case Direction.DOWN:
                laserTranslation.setPosition(new Point2D.Double(laser.getLines().get(0).getP1().getX(),
                        laser.getLines().get(0).getP1().getY() + laser.getVspeed()));
                break;
            case Direction.DOWN_LEFT:
                laserTranslation.setPosition(new Point2D.Double(laser.getLines().get(0).getP1().getX() - laser.getHspeed() / 2,
                        laser.getLines().get(0).getP1().getY() + laser.getVspeed() / 2));
                break;
            case Direction.LEFT:
                laserTranslation.setPosition(new Point2D.Double(laser.getLines().get(0).getP1().getX() - laser.getHspeed(),
                        laser.getLines().get(0).getP1().getY()));
                break;
            case Direction.UP_LEFT:
                laserTranslation.setPosition(new Point2D.Double(laser.getLines().get(0).getP1().getX() - laser.getHspeed() / 2,
                        laser.getLines().get(0).getP1().getY() - laser.getVspeed() / 2));
                break;
            default:
                laserTranslation.setPosition(new Point2D.Double(laser.getLines().get(0).getP1().getX(),
                        laser.getLines().get(0).getP1().getY() - laser.getVspeed()));
                break;
        }
        return laserTranslation.perform();
    }
}
