package com.gurpy.games.pojos.entities;

import com.gurpy.games.pojos.action.Action;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Player extends BBoxElement {

    /**
     * Constructor for custom color box player
     * @param position
     * @param dimension
     */
    public Player(Point2D position, Dimension dimension, Color borderColor, Color bgColor, double borderThickness) {
        super(position, dimension, borderColor, bgColor, borderThickness, EntityTypes.PLAYER);
    }

    /**
     * Constructor for solid color box player
     * @param position
     * @param dimension
     */
    public Player(Point2D position, Dimension dimension, Color color) {
        super(position, dimension, color, color, 0.0, EntityTypes.PLAYER);
    }

    /**
     * Constructor for default box player
     * @param position
     * @param dimension
     */
    public Player(Point2D position, Dimension dimension) {
        super(position, dimension, Color.BLACK, Color.WHITE, 2.0, EntityTypes.PLAYER);
    }

}
