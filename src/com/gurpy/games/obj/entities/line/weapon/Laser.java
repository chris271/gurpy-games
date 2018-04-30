package com.gurpy.games.obj.entities.line.weapon;

import com.gurpy.games.obj.entities.EntityTypes;
import com.gurpy.games.obj.entities.line.LineElement;
import com.gurpy.games.obj.entities.ui.Playable;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Laser extends LineElement {

    private double hspeed;
    private double vspeed;
    private int stepsAlive;
    private Playable owner;

    public Laser(Point2D positionA, Point2D positionB, Color borderColor, Color bgColor,
                 double laserThickness, double speed, double direction, Playable owner) {
        super(new Line2D.Double(positionA, positionB), borderColor, bgColor, laserThickness, EntityTypes.LASER, direction);
        this.hspeed = Math.cos(direction) * speed;
        this.vspeed = Math.sin(direction) * speed;
        this.owner = owner;
        this.stepsAlive = 0;
    }

    public Playable getOwner() {
        return owner;
    }

    public void setOwner(Playable owner) {
        this.owner = owner;
    }

    public double getHspeed() {
        return hspeed;
    }

    public void setHspeed(double hspeed) {
        this.hspeed = hspeed;
    }

    public double getVspeed() {
        return vspeed;
    }

    public void setVspeed(double vspeed) {
        this.vspeed = vspeed;
    }

    public int getStepsAlive() {
        return stepsAlive;
    }

    public void setStepsAlive(int stepsAlive) {
        this.stepsAlive = stepsAlive;
    }
}
