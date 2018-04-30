package com.gurpy.games.obj.entities.line.weapon;

import com.gurpy.games.obj.entities.EntityTypes;
import com.gurpy.games.obj.entities.line.LineElement;
import com.gurpy.games.obj.entities.ui.Playable;
import com.gurpy.games.obj.entities.ui.Projectile;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Laser extends LineElement implements Projectile {

    private double hspeed;
    private double vspeed;
    private int stepsAlive;
    private Playable owner;

    public Laser(Point2D positionA, Point2D positionB, Color borderColor, Color bgColor,
                 double laserThickness, double speed, double direction, Playable owner) {
        super(new Line2D.Double(positionA, positionB), borderColor, bgColor, laserThickness, EntityTypes.PROJECTILE, direction);
        this.hspeed = Math.cos(direction) * speed;
        this.vspeed = Math.sin(direction) * speed;
        this.owner = owner;
        this.stepsAlive = 0;
    }

    @Override
    public Playable getOwner() {
        return owner;
    }

    @Override
    public void setOwner(Playable owner) {
        this.owner = owner;
    }

    @Override
    public double getHspeed() {
        return hspeed;
    }

    @Override
    public void setHspeed(double hspeed) {
        this.hspeed = hspeed;
    }

    @Override
    public double getVspeed() {
        return vspeed;
    }

    @Override
    public void setVspeed(double vspeed) {
        this.vspeed = vspeed;
    }

    @Override
    public int getStepsAlive() {
        return stepsAlive;
    }

    @Override
    public void setStepsAlive(int stepsAlive) {
        this.stepsAlive = stepsAlive;
    }
}
