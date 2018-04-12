package com.gurpy.games.pojos.entities;

import java.awt.geom.Point2D;

/**
 * Class GUIElement
 * Logical UI element of a certain type
 * Requires a location.
 */

public abstract class UIElement implements UIEntity{

    private double xLoc, yLoc;
    private int type;

    public UIElement(Point2D position, int type){
        this.xLoc = xLoc;
        this.yLoc = yLoc;
        this.type = type;
    }

    public UIElement(Point2D position){
        this.xLoc = position.getX();
        this.yLoc = position.getY();
        type = EntityTypes.DEFAULT;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public double getX(){
        return xLoc;
    }

    @Override
    public double getY(){
        return yLoc;
    }

    @Override
    public void setType(int type) {
        this.type = type;
    }

    @Override
    public void setX(double x) {
        xLoc = x;
    }

    @Override
    public void setY(double y) {
        yLoc = y;
    }

    @Override
    public void addToX(double x) {
        xLoc = xLoc + x;
    }

    @Override
    public void addToY(double y) {
        yLoc = yLoc + y;
    }

    @Override
    public void setPosition(Point2D position) {
        xLoc = position.getX();
        yLoc = position.getY();
    }

}
