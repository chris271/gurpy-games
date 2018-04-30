package com.gurpy.games.obj.entities.bbox.weapon;


import com.gurpy.games.obj.entities.EntityTypes;
import com.gurpy.games.obj.entities.ui.Playable;
import com.gurpy.games.obj.entities.ui.Projectile;
import com.gurpy.games.obj.entities.ui.UIEntity;
import com.gurpy.games.obj.entities.bbox.BBoxElement;

import java.awt.*;
import java.awt.geom.Point2D;

public class Bullet extends BBoxElement implements Projectile {

    private double hspeed;
    private double vspeed;
    private int stepsAlive;
    private Playable owner;

    public Bullet(Point2D position, Dimension dimension, Color borderColor, Color bgColor, double borderThickness, Playable owner) {
        super(position, dimension, borderColor, bgColor, borderThickness, EntityTypes.PROJECTILE);
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

    @Override
    public Playable getOwner() {
        return owner;
    }

    @Override
    public void setOwner(Playable owner) {
        this.owner = owner;
    }
}
