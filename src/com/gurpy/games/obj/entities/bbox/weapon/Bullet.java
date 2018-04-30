package com.gurpy.games.obj.entities.bbox.weapon;


import com.gurpy.games.obj.entities.ui.UIEntity;
import com.gurpy.games.obj.entities.bbox.BBoxElement;

import java.awt.*;
import java.awt.geom.Point2D;

public class Bullet extends BBoxElement {

    private double hspeed;
    private double vspeed;
    private int stepsAlive;
    private UIEntity owner;

    public Bullet(Point2D position, Dimension dimension, Color borderColor, Color bgColor, double borderThickness, int type) {
        super(position, dimension, borderColor, bgColor, borderThickness, type);
    }

    public Bullet(Point2D position, Dimension dimension, Color borderColor, Color bgColor, double borderThickness) {
        super(position, dimension, borderColor, bgColor, borderThickness);
    }

    public Bullet(Point2D position, Dimension dimension) {
        super(position, dimension);
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

    public UIEntity getOwner() {
        return owner;
    }

    public void setOwner(UIEntity owner) {
        this.owner = owner;
    }
}
