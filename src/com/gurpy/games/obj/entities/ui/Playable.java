package com.gurpy.games.obj.entities.ui;

import com.gurpy.games.obj.entities.weapon.Weapon;

import java.awt.*;

public interface Playable extends UIEntity {

    public boolean isControllable();
    public boolean isFocused();
    public boolean isDoubleShot();
    public double getHspeed();
    public double getVspeed();
    public double getFireRate();
    public double getRange();
    public double getShotSpeed();
    public double getBulletWidth();
    public double getBulletHeight();
    public double getDirection();
    public double getHealth();
    public double getMaxHealth();
    public double getScore();
    public int getStepsSinceShot();
    public int getNumBullets();
    public int getKillCount();
    public Weapon getWeapon();
    public Color getHealthBarColor();
    public Color getHealthBarBorder();
    public Color getHealthBarFillColor();
    public double getRelativeX();
    public double getRelativeY();

    public void setControllable(boolean controllable);
    public void setFocused(boolean focused);
    public void setDoubleShot(boolean doubleShot);
    public void setHspeed(double hspeed);
    public void setVspeed(double vspeed);
    public void setFireRate(double fireRate);
    public void setRange(double range);
    public void setShotSpeed(double shotSpeed);
    public void setBulletWidth(double bulletWidth);
    public void setBulletHeight(double bulletHeight);
    public void setDirection(double direction);
    public void setHealth(double health);
    public void setMaxHealth(double maxHealth);
    public void setScore(double score);
    public void setStepsSinceShot(int stepsSinceShot);
    public void setNumBullets(int numBullets);
    public void setKillCount(int killCount);
    public void setWeapon(Weapon weapon);
    public void setHealthBarColor(Color healthBarColor);
    public void setHealthBarBorder(Color healthBarBorder);
    public void setHealthBarFillColor(Color healthBarFillColor);
    public void setRelativeX(double relativeX);
    public void setRelativeY(double relativeY);
}
