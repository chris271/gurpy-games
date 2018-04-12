package com.gurpy.games.pojos.entities;

import com.gurpy.games.pojos.action.Action;

import java.awt.*;
import java.awt.geom.Point2D;

public class BBoxElement extends UIElement {

    private double width, height;

    /**
     * Constructor used for custom type box.
     * @param position
     * @param dimension
     * @param borderColor
     * @param bgColor
     * @param type
     */
    public BBoxElement(Point2D position, Dimension dimension, Color borderColor, Color bgColor, int type) {
        super(position, borderColor, bgColor, type);
        this.width = dimension.getWidth();
        this.height = dimension.getHeight();
    }

    /**
     * Constructor used for custom background box.
     * @param position
     * @param dimension
     * @param borderColor
     * @param bgColor
     */
    public BBoxElement(Point2D position, Dimension dimension, Color borderColor, Color bgColor) {
        super(position, borderColor, bgColor, EntityTypes.BACKGROUND);
        this.width = dimension.getWidth();
        this.height = dimension.getHeight();
    }

    /**
     * Constructor used for black background box.
     * @param position
     * @param dimension
     */
    public BBoxElement(Point2D position, Dimension dimension) {
        super(position, EntityTypes.BACKGROUND);
        this.width = dimension.getWidth();
        this.height = dimension.getHeight();
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

}
