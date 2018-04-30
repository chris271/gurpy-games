package com.gurpy.games.obj.entities.bbox;

import com.gurpy.games.obj.entities.EntityTypes;
import com.gurpy.games.obj.entities.ui.UIElement;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

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
    public BBoxElement(Point2D position, Dimension dimension, Color borderColor, Color bgColor, double borderThickness, int type) {
        super(position, borderColor, bgColor, borderThickness, type);
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
    public BBoxElement(Point2D position, Dimension dimension, Color borderColor, Color bgColor, double borderThickness) {
        super(position, borderColor, bgColor, borderThickness, EntityTypes.BACKGROUND);
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

    public Rectangle2D getBBox() {
        return new Rectangle2D.Double(getX(), getY(), getWidth(), getHeight());
    }

}
