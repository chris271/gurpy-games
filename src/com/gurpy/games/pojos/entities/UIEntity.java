package com.gurpy.games.pojos.entities;

import com.gurpy.games.pojos.action.Action;

import java.awt.geom.Point2D;

public interface UIEntity extends Entity{

    public double getX();
    public double getY();
    public void setX(double x);
    public void setY(double y);
    public void addToX(double x);
    public void addToY(double y);
    public void setPosition(Point2D position);

}
