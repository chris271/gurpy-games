package com.gurpy.games.obj.entities.ui;

import com.gurpy.games.obj.entities.weapon.Weapon;

import java.awt.*;

public interface Playable extends UIEntity {

    boolean isControllable();
    boolean isFocused();
    double getHspeed();
    double getVspeed();
    double getDirection();
    double getHealth();
    double getMaxHealth();
    double getScore();
    int getKillCount();
    Weapon getWeapon();
    Color getHealthBarColor();
    Color getHealthBarBorder();
    Color getHealthBarFillColor();
    double getRelativeX();
    double getRelativeY();

    void setControllable(boolean controllable);
    void setFocused(boolean focused);
    void setHspeed(double hspeed);
    void setVspeed(double vspeed);
    void setDirection(double direction);
    void setHealth(double health);
    void setMaxHealth(double maxHealth);
    void setScore(double score);
    void setKillCount(int killCount);
    void setWeapon(Weapon weapon);
    void setHealthBarColor(Color healthBarColor);
    void setHealthBarBorder(Color healthBarBorder);
    void setHealthBarFillColor(Color healthBarFillColor);
    void setRelativeX(double relativeX);
    void setRelativeY(double relativeY);
}
