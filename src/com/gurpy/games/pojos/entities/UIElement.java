package com.gurpy.games.pojos.entities;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Class GUIElement
 * Logical UI element of a certain type
 * Requires a location.
 */

public abstract class UIElement implements UIEntity{

    private volatile double xLoc, yLoc;
    private volatile boolean display;
    private volatile boolean destroy;
    private double borderThickness;
    private int type;
    private Color borderColor;
    private Color bgColor;
    private ArrayList<UIEntity> collisions;

    /**
     * Contructor used for custom UI element.
     * @param position
     * @param borderColor
     * @param bgColor
     * @param type
     */
    UIElement(Point2D position, Color borderColor, Color bgColor, double borderThickness, int type){
        this.xLoc = position.getX();
        this.yLoc = position.getY();
        this.borderColor = borderColor;
        this.bgColor = bgColor;
        this.type = type;
        this.borderThickness = borderThickness;
        this.collisions = new ArrayList<>();
    }

    /**
     * Constructor used for solid color custom UI element
     * @param position
     * @param color
     * @param type
     */
    UIElement(Point2D position, Color color, int type){
        this.xLoc = position.getX();
        this.yLoc = position.getY();
        this.borderColor = color;
        this.bgColor = color;
        this.type = type;
        this.borderThickness = 0.0;
        this.collisions = new ArrayList<>();
    }

    /**
     * Constructor used for custom black UI element.
     * @param position
     * @param type
     */
    UIElement(Point2D position, int type){
        this.xLoc = position.getX();
        this.yLoc = position.getY();
        this.borderColor = Color.BLACK;
        this.bgColor = Color.BLACK;
        this.type = type;
        this.borderThickness = 0.0;
        this.collisions = new ArrayList<>();
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
        this.type = EntityTypes.DEFAULT;
        this.borderThickness = 0.0;
        this.collisions = new ArrayList<>();
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

    public double getBorderThickness() {
        return borderThickness;
    }

    public void setBorderThickness(double borderThickness) {
        this.borderThickness = borderThickness;
    }

    public ArrayList<UIEntity> getCollisions() {
        return collisions;
    }

    public void addCollision(UIEntity collision) {
        collisions.add(collision);
    }

    public boolean isDestroy() {
        return destroy;
    }

    public void setDestroy(boolean destroy) {
        this.destroy = destroy;
    }
}
