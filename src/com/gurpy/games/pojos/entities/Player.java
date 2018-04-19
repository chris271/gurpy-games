package com.gurpy.games.pojos.entities;

import com.gurpy.games.pojos.action.Action;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Player extends BBoxElement {

    private double hspeed;
    private double vspeed;

    /**
     * Constructor for custom color box player
     * @param position
     * @param dimension
     */
    public Player(Point2D position, Dimension dimension, Color borderColor, Color bgColor,
                  double borderThickness, double speed) {
        super(position, dimension, borderColor, bgColor, borderThickness, EntityTypes.PLAYER);
        this.hspeed = speed;
        this.vspeed = speed;
    }

    /**
     * Constructor for solid color box player
     * @param position
     * @param dimension
     */
    public Player(Point2D position, Dimension dimension, Color color, double speed) {
        super(position, dimension, color, color, 0.0, EntityTypes.PLAYER);
        this.hspeed = speed;
        this.vspeed = speed;
    }

    /**
     * Constructor for default box player
     * @param position
     * @param dimension
     */
    public Player(Point2D position, Dimension dimension) {
        super(position, dimension, Color.BLACK, Color.WHITE, 2.0, EntityTypes.PLAYER);
        this.hspeed = 1;
        this.vspeed = 1;
    }

    public double getHspeed() {
        return hspeed;
    }

    public void setHspeed(double hspeed) {
        this.hspeed = hspeed;
    }

    public double getVspeed() {
        return vspeed;
    }

    public void setVspeed(double vspeed) {
        this.vspeed = vspeed;
    }
}
