package com.gurpy.games.obj.entities.bbox;

import com.gurpy.games.obj.entities.EntityTypes;
import com.gurpy.games.obj.entities.ui.UIElement;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class BBoxElement extends UIElement {

    public BBoxElement(Point2D position, Dimension dimension, Color borderColor, Color bgColor, double borderThickness, int type) {
        super(position, dimension, borderColor, bgColor, borderThickness, type);
    }

    public BBoxElement(Point2D position, Dimension dimension, Color borderColor, Color bgColor, double borderThickness) {
        super(position, dimension, borderColor, bgColor, borderThickness, EntityTypes.BACKGROUND);
    }

    public BBoxElement(Point2D position, Dimension dimension) {
        super(position, dimension, EntityTypes.BACKGROUND);
    }

    public Rectangle2D getBBox() {
        return new Rectangle2D.Double(getX(), getY(), getWidth(), getHeight());
    }

}
