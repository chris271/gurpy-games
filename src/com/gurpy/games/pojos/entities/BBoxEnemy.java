package com.gurpy.games.pojos.entities;

import java.awt.*;
import java.awt.geom.Point2D;

public class BBoxEnemy extends BBoxPlayer {

    public BBoxEnemy(Point2D position, Dimension dimension, Color borderColor, Color bgColor, double borderThickness, double speed) {
        super(position, dimension, borderColor, bgColor, borderThickness, speed);
    }

    public BBoxEnemy(Point2D position, Dimension dimension, Color color, double speed) {
        super(position, dimension, color, speed);
    }

    public BBoxEnemy(Point2D position, Dimension dimension) {
        super(position, dimension);
    }
}
