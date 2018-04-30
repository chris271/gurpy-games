package com.gurpy.games.obj.entities.ui;

import com.gurpy.games.obj.entities.Entity;

import java.awt.*;
import java.awt.geom.Point2D;

public interface UIEntity extends Entity {

    double getX();
    double getY();
    void setX(double x);
    void setY(double y);
    double getWidth();
    double getHeight();
    void setWidth(double x);
    void setHeight(double y);
    void addToX(double x);
    void addToY(double y);
    void setPosition(Point2D position);
    Color getBorderColor();
    void setBorderColor(Color borderColor);
    Color getBgColor();
    void setBgColor(Color bgColor);
    double getBorderThickness();
    void setBorderThickness(double borderThickness);
    Point2D getPosition();
    void setDisplay(boolean display);
    boolean isDisplay();
    void setDestroy(boolean destroy);
    boolean isDestroy();

}
