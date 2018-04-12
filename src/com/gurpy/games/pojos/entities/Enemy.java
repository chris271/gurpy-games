package com.gurpy.games.pojos.entities;

import com.gurpy.games.pojos.action.Action;

import java.awt.*;
import java.awt.geom.Point2D;

public class Enemy extends BBoxElement {

    /**
     * Constructor for custom color box enemy.
     * @param position
     * @param dimension
     */
    public Enemy(Point2D position, Dimension dimension, Color borderColor, Color bgColor) {
        super(position, dimension, borderColor, bgColor, EntityTypes.PLAYER);
    }

    /**
     * Constructor for solid color box enemy.
     * @param position
     * @param dimension
     */
    public Enemy(Point2D position, Dimension dimension, Color color) {
        super(position, dimension, color, color, EntityTypes.PLAYER);
    }

    /**
     * Constructor for default box enemy.
     * @param position
     * @param dimension
     */
    public Enemy(Point2D position, Dimension dimension) {
        super(position, dimension, Color.BLACK, Color.WHITE, EntityTypes.PLAYER);
    }

}
