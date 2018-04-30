package com.gurpy.games.obj.entities.ui;

import com.gurpy.games.obj.entities.Entity;

import java.awt.geom.Point2D;

public interface UIEntity extends Entity {

    public double getX();
    public double getY();
    public void setX(double x);
    public void setY(double y);
    public void addToX(double x);
    public void addToY(double y);
    public void setPosition(Point2D position);
    public Point2D getPosition();
    public void setDisplay(boolean display);
    public boolean isDisplay();
    public void setDestroy(boolean destroy);
    public boolean isDestroy();

}
