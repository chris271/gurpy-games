package com.gurpy.games.pojos.entities;

import com.gurpy.games.pojos.action.Action;

import java.awt.*;
import java.awt.geom.Point2D;

public class BBoxElement extends UIElement {

    private double width, height;

    public BBoxElement(Point2D position, Dimension dimension, int type) {
        super(position, type);
        this.width = dimension.getWidth();
        this.height = dimension.getHeight();
    }

    public BBoxElement(Point2D position, Dimension dimension) {
        super(position, EntityTypes.BACKGROUND);
        this.width = dimension.getWidth();
        this.height = dimension.getHeight();
    }

    @Override
    public void performAction(Action action) {
        //Generic BBox action...
    }
}
