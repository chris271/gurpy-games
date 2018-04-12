package com.gurpy.games.pojos.entities;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Class GUIElement
 * Logical UI element of a certain type
 * Requires a location.
 */

public abstract class UIElement implements UIEntity{

    private double xLoc, yLoc;
    private int type;
    private Color borderColor;
    private Color bgColor;

    /**
     * Contructor used for custom UI element.
     * @param position
     * @param borderColor
     * @param bgColor
     * @param type
     */
    UIElement(Point2D position, Color borderColor, Color bgColor, int type){
        this.xLoc = xLoc;
        this.yLoc = yLoc;
        this.borderColor = borderColor;
        this.bgColor = bgColor;
        this.type = type;

    }

    /**
     * Constructor used for solid color custom UI element
     * @param position
     * @param color
     * @param type
     */
    UIElement(Point2D position, Color color, int type){
        this.xLoc = xLoc;
        this.yLoc = yLoc;
        this.borderColor = color;
        this.bgColor = color;
        this.type = type;

    }

    /**
     * Constructor used for custom black UI element.
     * @param position
     * @param type
     */
    UIElement(Point2D position, int type){
        this.xLoc = xLoc;
        this.yLoc = yLoc;
        this.borderColor = Color.BLACK;
        this.bgColor = Color.BLACK;
        this.type = type;

    }

    /**
     * Constructor used for generic black UI element.
     * @param position
     */
    UIElement(Point2D position){
        this.xLoc = position.getX();
        this.yLoc = position.getY();
        this.borderColor = Color.BLACK;
        this.bgColor = Color.BLACK;
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

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public Color getBgColor() {
        return bgColor;
    }

    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
    }
}
