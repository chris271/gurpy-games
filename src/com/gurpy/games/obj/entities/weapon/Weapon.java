package com.gurpy.games.obj.entities.weapon;

import com.gurpy.games.obj.entities.EntityTypes;
import com.gurpy.games.obj.entities.ui.Playable;
import com.gurpy.games.obj.entities.ui.Projectile;
import com.gurpy.games.obj.entities.ui.UIElement;

import java.awt.*;

public abstract class Weapon extends UIElement {

    private Playable owner;
    private double damageMultiplier;
    private double damage;
    private double fireRate;
    private double shotSpeed;
    private double range;
    private boolean doubleShot;
    private double projectileWidth;
    private double projectileHeight;
    private int stepsSinceShot;
    private int numBullets;

    Weapon(Playable owner, double damageMultiplier, double damage, double fireRate, double shotSpeed,
           double projectileWidth, double projectileHeight, double range, boolean doubleShot, int numBullets) {
        super(owner.getPosition(), new Dimension(0, 0), owner.getBorderColor(),
                owner.getBgColor(), owner.getBorderThickness(), EntityTypes.WEAPON);
        this.owner = owner;
        this.damageMultiplier = damageMultiplier;
        this.damage = damage;
        this.fireRate = fireRate;
        this.shotSpeed = shotSpeed;
        this.range = range;
        this.doubleShot = doubleShot;
        this.numBullets = numBullets;
        this.projectileWidth = projectileWidth;
        this.projectileHeight = projectileHeight;
    }

    public Playable getOwner() {
        return owner;
    }

    public void setOwner(Playable owner) {
        this.owner = owner;
    }

    public double getDamageMultiplier() {
        return damageMultiplier;
    }

    public void setDamageMultiplier(double damageMultiplier) {
        this.damageMultiplier = damageMultiplier;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getFireRate() {
        return fireRate;
    }

    public void setFireRate(double fireRate) {
        this.fireRate = fireRate;
    }

    public double getShotSpeed() {
        return shotSpeed;
    }

    public void setShotSpeed(double shotSpeed) {
        this.shotSpeed = shotSpeed;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public boolean isDoubleShot() {
        return doubleShot;
    }

    public void setDoubleShot(boolean doubleShot) {
        this.doubleShot = doubleShot;
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

    public Projectile getProjectile(double projectileDirection, double doubleShotOffset) {
        return null;
    }

    public double getProjectileWidth() {
        return projectileWidth;
    }

    public void setProjectileWidth(double projectileWidth) {
        this.projectileWidth = projectileWidth;
    }

    public double getProjectileHeight() {
        return projectileHeight;
    }

    public void setProjectileHeight(double projectileHeight) {
        this.projectileHeight = projectileHeight;
    }
}
