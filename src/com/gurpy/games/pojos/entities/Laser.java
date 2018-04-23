package com.gurpy.games.pojos.entities;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Laser extends LineElement {

    private double hspeed;
    private double vspeed;
    private int stepsAlive;
    private UIEntity owner;

    public Laser(Point2D positionA, Point2D positionB, Color borderColor, Color bgColor,
                 double borderThickness, double speed, int direction, UIEntity owner) {
        super(new Line2D.Double(positionA, positionB), borderColor, bgColor, borderThickness, EntityTypes.LASER, direction);
        this.hspeed = speed;
        this.vspeed = speed;
        this.owner = owner;
        this.stepsAlive = 0;
    }

    public UIEntity getOwner() {
        return owner;
    }

    public void setOwner(UIEntity owner) {
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
