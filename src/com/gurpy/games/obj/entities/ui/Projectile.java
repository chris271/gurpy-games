package com.gurpy.games.obj.entities.ui;

public interface Projectile extends UIEntity {

    Playable getOwner();
    void setOwner(Playable owner);
    double getHspeed();
    void setHspeed(double hspeed);
    double getVspeed();
    void setVspeed(double vspeed);
    int getStepsAlive();
    void setStepsAlive(int stepsAlive);

}
