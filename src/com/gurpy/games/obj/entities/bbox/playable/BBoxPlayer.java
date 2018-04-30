package com.gurpy.games.obj.entities.bbox.playable;

import com.gurpy.games.obj.control.movement.Direction;
import com.gurpy.games.obj.entities.*;
import com.gurpy.games.obj.entities.bbox.BBoxElement;
import com.gurpy.games.obj.entities.ui.Playable;
import com.gurpy.games.obj.entities.weapon.LaserRifle;
import com.gurpy.games.obj.entities.weapon.Weapon;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class BBoxPlayer extends BBoxElement implements Playable {

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
    private double health;
    private double maxHealth;
    private double score;
    private int stepsSinceShot;
    private int numBullets;
    private int killCount;
    private Weapon weapon;
    private Color healthBarColor;
    private Color healthBarBorder;
    private Color healthBarFillColor;
    private double relativeX;
    private double relativeY;

    /**
     * Constructor for custom color box player
     * @param position
     * @param dimension
     */
    public BBoxPlayer(Point2D position, Dimension dimension, Color borderColor, Color bgColor, double speed) {
        super(position, dimension, borderColor, bgColor, 10.0, EntityTypes.PLAYER);
        this.hspeed = speed;
        this.vspeed = speed;
        this.direction = Direction.UP;
        this.fireRate = 2;
        this.range = 1;
        this.bulletWidth = 10;
        this.bulletHeight = 25;
        this.shotSpeed = 20;
        this.stepsSinceShot = 0;
        this.numBullets = 1;
        this.controllable = false;
        this.focused = false;
        this.relativeX = position.getX();
        this.relativeY = position.getY();
        this.doubleShot = false;
        this.health = 100;
        this.maxHealth = 100;
        this.healthBarBorder = Color.GREEN;
        this.healthBarColor = Color.GREEN;
        this.healthBarFillColor = Color.RED;
        this.killCount = 0;
        this.score = 0;
        this.weapon = new LaserRifle(this, borderColor, bgColor);
    }

    /**
     * Constructor for solid color box player
     * @param position
     * @param dimension
     */
    public BBoxPlayer(Point2D position, Dimension dimension, Color color, double speed) {
        super(position, dimension, color, color, 10.0, EntityTypes.PLAYER);
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
        this.controllable = false;
        this.focused = false;
        this.relativeX = position.getX();
        this.relativeY = position.getY();
        this.doubleShot = false;
        this.healthBarBorder = Color.GREEN;
        this.healthBarColor = Color.GREEN;
        this.healthBarFillColor = Color.RED;
        this.killCount = 0;
        this.score = 0;
        this.weapon = new LaserRifle(this, color, color);
    }

    /**
     * Constructor for default box player
     * @param position
     * @param dimension
     */
    public BBoxPlayer(Point2D position, Dimension dimension) {
        super(position, dimension, Color.BLACK, Color.WHITE, 10.0, EntityTypes.PLAYER);
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
        this.controllable = false;
        this.focused = false;
        this.relativeX = position.getX();
        this.relativeY = position.getY();
        this.doubleShot = false;
        this.healthBarBorder = Color.GREEN;
        this.healthBarColor = Color.GREEN;
        this.healthBarFillColor = Color.RED;
        this.killCount = 0;
        this.score = 0;
        this.weapon = new LaserRifle(this, Color.GREEN, Color.YELLOW);
    }

    public Rectangle2D.Double getHealthBar() {
        return new Rectangle2D.Double(this.getX() + this.getWidth() / 2 - 50, this.getY() + this.getHeight() + 10, 100, 20);
    }

    public Rectangle2D.Double getHealthBarFill() {
        return new Rectangle2D.Double(this.getX() + this.getWidth() / 2 - 40, this.getY() + this.getHeight() + 14, 80, 12);
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
    public double getDirection() {
        return direction;
    }

    @Override
    public void setDirection(double direction) {
        this.direction = direction;
    }

    @Override
    public double getFireRate() {
        return fireRate;
    }

    @Override
    public void setFireRate(double fireRate) {
        this.fireRate = fireRate;
    }

    @Override
    public double getRange() {
        return range;
    }

    @Override
    public void setRange(double range) {
        this.range = range;
    }

    @Override
    public double getShotSpeed() {
        return shotSpeed;
    }

    @Override
    public void setShotSpeed(double shotSpeed) {
        this.shotSpeed = shotSpeed;
    }

    @Override
    public double getBulletWidth() {
        return bulletWidth;
    }

    @Override
    public void setBulletWidth(double bulletWidth) {
        this.bulletWidth = bulletWidth;
    }

    @Override
    public double getBulletHeight() {
        return bulletHeight;
    }

    @Override
    public void setBulletHeight(double bulletHeight) {
        this.bulletHeight = bulletHeight;
    }

    @Override
    public int getStepsSinceShot() {
        return stepsSinceShot;
    }

    @Override
    public void setStepsSinceShot(int stepsSinceShot) {
        this.stepsSinceShot = stepsSinceShot;
    }

    @Override
    public int getNumBullets() {
        return numBullets;
    }

    @Override
    public void setNumBullets(int numBullets) {
        this.numBullets = numBullets;
    }

    @Override
    public boolean isControllable() {
        return controllable;
    }

    @Override
    public void setControllable(boolean controllable) {
        this.controllable = controllable;
    }

    @Override
    public boolean isFocused() {
        return focused;
    }

    @Override
    public void setFocused(boolean focused) {
        this.focused = focused;
    }

    @Override
    public double getRelativeX() {
        return relativeX;
    }

    @Override
    public void setRelativeX(double relativeX) {
        this.relativeX = relativeX;
    }

    @Override
    public double getRelativeY() {
        return relativeY;
    }

    @Override
    public void setRelativeY(double relativeY) {
        this.relativeY = relativeY;
    }

    @Override
    public boolean isDoubleShot() {
        return doubleShot;
    }

    @Override
    public void setDoubleShot(boolean doubleShot) {
        this.doubleShot = doubleShot;
    }

    @Override
    public double getHealth() {
        return health;
    }

    @Override
    public void setHealth(double health) {
        this.health = health;
    }

    @Override
    public double getMaxHealth() {
        return maxHealth;
    }

    @Override
    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    @Override
    public Color getHealthBarColor() {
        return healthBarColor;
    }

    @Override
    public void setHealthBarColor(Color healthBarColor) {
        this.healthBarColor = healthBarColor;
    }

    @Override
    public Color getHealthBarBorder() {
        return healthBarBorder;
    }

    @Override
    public void setHealthBarBorder(Color healthBarBorder) {
        this.healthBarBorder = healthBarBorder;
    }

    @Override
    public Color getHealthBarFillColor() {
        return healthBarFillColor;
    }

    @Override
    public void setHealthBarFillColor(Color healthBarFillColor) {
        this.healthBarFillColor = healthBarFillColor;
    }

    @Override
    public double getScore() {
        return score;
    }

    @Override
    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public int getKillCount() {
        return killCount;
    }

    @Override
    public Weapon getWeapon() {
        return weapon;
    }

    @Override
    public void setKillCount(int killCount) {
        this.killCount = killCount;
    }

    @Override
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
