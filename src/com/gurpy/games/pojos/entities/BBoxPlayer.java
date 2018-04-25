package com.gurpy.games.pojos.entities;

import com.gurpy.games.pojos.action.Action;
import com.gurpy.games.pojos.control.Direction;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class BBoxPlayer extends BBoxElement {

    private boolean controllable;
    private boolean focused;
    private boolean doubleShot;
    private double hspeed;
    private double vspeed;
    private double fireRate;
    private double range;
    private double shotSpeed;
    private double bulletWidth;
    private double bulletHeight;
    private double direction;
    private int stepsSinceShot;
    private int numBullets;
    private Color laserBorder;
    private Color laserColor;
    private double relativeX;
    private double relativeY;

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
        this.bulletWidth = 5;
        this.bulletHeight = 25;
        this.shotSpeed = 20;
        this.stepsSinceShot = 0;
        this.numBullets = 1;
        this.laserBorder = borderColor;
        this.laserColor = bgColor;
        this.controllable = false;
        this.focused = false;
        this.relativeX = position.getX();
        this.relativeY = position.getY();
        this.doubleShot = false;
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
        this.bulletWidth = 5;
        this.bulletHeight = 25;
        this.shotSpeed = 20;
        this.stepsSinceShot = 0;
        this.numBullets = 1;
        this.laserBorder = color;
        this.laserColor = color;
        this.controllable = false;
        this.focused = false;
        this.relativeX = position.getX();
        this.relativeY = position.getY();
        this.doubleShot = false;
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
        this.bulletWidth = 5;
        this.bulletHeight = 25;
        this.shotSpeed = 20 ;
        this.stepsSinceShot = 0;
        this.numBullets = 1;
        this.laserBorder = Color.GREEN;
        this.laserColor = Color.YELLOW;
        this.controllable = false;
        this.focused = false;
        this.relativeX = position.getX();
        this.relativeY = position.getY();
        this.doubleShot = false;
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

    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
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

    public Color getLaserBorder() {
        return laserBorder;
    }

    public void setLaserBorder(Color laserBorder) {
        this.laserBorder = laserBorder;
    }

    public Color getLaserColor() {
        return laserColor;
    }

    public void setLaserColor(Color laserColor) {
        this.laserColor = laserColor;
    }

    public boolean isControllable() {
        return controllable;
    }

    public void setControllable(boolean controllable) {
        this.controllable = controllable;
    }

    public boolean isFocused() {
        return focused;
    }

    public void setFocused(boolean focused) {
        this.focused = focused;
    }

    public double getRelativeX() {
        return relativeX;
    }

    public void setRelativeX(double relativeX) {
        this.relativeX = relativeX;
    }

    public double getRelativeY() {
        return relativeY;
    }

    public void setRelativeY(double relativeY) {
        this.relativeY = relativeY;
    }

    public boolean isDoubleShot() {
        return doubleShot;
    }

    public void setDoubleShot(boolean doubleShot) {
        this.doubleShot = doubleShot;
    }
}
