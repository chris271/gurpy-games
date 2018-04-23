package com.gurpy.games.pojos.entities;

import com.gurpy.games.pojos.action.Action;
import com.gurpy.games.pojos.control.Direction;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class BBoxPlayer extends BBoxElement {

    private double hspeed;
    private double vspeed;
    private double fireRate;
    private double range;
    private double shotSpeed;
    private double bulletWidth;
    private double bulletHeight;
    private int direction;
    private int stepsSinceShot;
    private int numBullets;

    /**
     * Constructor for custom color box player
     * @param position
     * @param dimension
     */
    public BBoxPlayer(Point2D position, Dimension dimension, Color borderColor, Color bgColor,
                      double borderThickness, double speed) {
        super(position, dimension, borderColor, bgColor, borderThickness, EntityTypes.PLAYER);
        this.hspeed = speed;
        this.vspeed = speed;
        this.direction = Direction.UP;
        this.fireRate = 2;
        this.range = 1;
        this.bulletWidth = 1;
        this.bulletHeight = 25;
        this.shotSpeed = 20;
        this.stepsSinceShot = 0;
        this.numBullets = 1;
    }

    /**
     * Constructor for solid color box player
     * @param position
     * @param dimension
     */
    public BBoxPlayer(Point2D position, Dimension dimension, Color color, double speed) {
        super(position, dimension, color, color, 0.0, EntityTypes.PLAYER);
        this.hspeed = speed;
        this.vspeed = speed;
        this.direction = Direction.UP;
        this.fireRate = 2;
        this.range = 1;
        this.bulletWidth = 1;
        this.bulletHeight = 25;
        this.shotSpeed = 20;
        this.stepsSinceShot = 0;
        this.numBullets = 1;
    }

    /**
     * Constructor for default box player
     * @param position
     * @param dimension
     */
    public BBoxPlayer(Point2D position, Dimension dimension) {
        super(position, dimension, Color.BLACK, Color.WHITE, 2.0, EntityTypes.PLAYER);
        this.hspeed = 1;
        this.vspeed = 1;
        this.direction = Direction.UP;
        this.fireRate = 2;
        this.range = 1;
        this.bulletWidth = 1;
        this.bulletHeight = 25;
        this.shotSpeed = 20 ;
        this.stepsSinceShot = 0;
        this.numBullets = 1;
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

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public double getFireRate() {
        return fireRate;
    }

    public void setFireRate(double fireRate) {
        this.fireRate = fireRate;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public double getbulletWidth() {
        return bulletWidth;
    }

    public double getShotSpeed() {
        return shotSpeed;
    }

    public void setShotSpeed(double shotSpeed) {
        this.shotSpeed = shotSpeed;
    }

    public double getBulletWidth() {
        return bulletWidth;
    }

    public void setBulletWidth(double bulletWidth) {
        this.bulletWidth = bulletWidth;
    }

    public double getBulletHeight() {
        return bulletHeight;
    }

    public void setBulletHeight(double bulletHeight) {
        this.bulletHeight = bulletHeight;
    }

    public int getStepsSinceShot() {
        return stepsSinceShot;
    }

    public void setStepsSinceShot(int stepsSinceShot) {
        this.stepsSinceShot = stepsSinceShot;
    }

    public int getNumBullets() {
        return numBullets;
    }

    public void setNumBullets(int numBullets) {
        this.numBullets = numBullets;
    }
}
