package com.gurpy.games.obj.entities.ui;

import com.gurpy.games.obj.entities.EntityTypes;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Class GUIElement
 * Logical UI element of a certain type
 * Requires a location.
 */

public abstract class UIElement implements UIEntity{

    private volatile double xLoc, yLoc;
    private volatile double width, height;
    private volatile boolean display;
    private volatile boolean destroy;
    private double borderThickness;
    private int type;
    private Color borderColor;
    private Color bgColor;

    public UIElement(Point2D position, Dimension dimension, Color borderColor, Color bgColor, double borderThickness, int type){
        this.xLoc = position.getX();
        this.yLoc = position.getY();
        this.borderColor = borderColor;
        this.bgColor = bgColor;
        this.type = type;
        this.borderThickness = borderThickness;
        this.width = dimension.getWidth();
        this.height = dimension.getHeight();
    }

    public UIElement(Point2D position, Dimension dimension, Color color, int type){
        this.xLoc = position.getX();
        this.yLoc = position.getY();
        this.borderColor = color;
        this.bgColor = color;
        this.type = type;
        this.borderThickness = 0.0;
        this.width = dimension.getWidth();
        this.height = dimension.getHeight();
    }

    public UIElement(Point2D position, Dimension dimension, int type){
        this.xLoc = position.getX();
        this.yLoc = position.getY();
        this.borderColor = Color.BLACK;
        this.bgColor = Color.BLACK;
        this.type = type;
        this.borderThickness = 0.0;
        this.width = dimension.getWidth();
        this.height = dimension.getHeight();
    }

    public UIElement(Point2D position, Dimension dimension){
        this.xLoc = position.getX();
        this.yLoc = position.getY();
        this.borderColor = Color.BLACK;
        this.bgColor = Color.BLACK;
        this.type = EntityTypes.DEFAULT;
        this.borderThickness = 0.0;
        this.width = dimension.getWidth();
        this.height = dimension.getHeight();
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

    @Override
    public Point2D getPosition() {
        return new Point2D.Double(this.getX(), this.getY());
    }

    @Override
    public boolean isDisplay() {
        return display;
    }

    @Override
    public void setDisplay(boolean display) {
        this.display = display;
    }

    @Override
    public Color getBorderColor() {
        return borderColor;
    }

    @Override
    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    @Override
    public Color getBgColor() {
        return bgColor;
    }

    @Override
    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
    }

    @Override
    public double getBorderThickness() {
        return borderThickness;
    }

    @Override
    public void setBorderThickness(double borderThickness) {
        this.borderThickness = borderThickness;
    }

    @Override
    public boolean isDestroy() {
        return destroy;
    }

    @Override
    public void setDestroy(boolean destroy) {
        this.destroy = destroy;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public void setHeight(double height) {
        this.height = height;
    }
}
