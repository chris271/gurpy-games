package com.gurpy.games.pojos.entities;

import java.awt.*;
import java.awt.geom.Point2D;

public class BBoxEnemy extends BBoxPlayer {

    private int enemyType;

    public BBoxEnemy(Point2D position, Dimension dimension, Color borderColor, Color bgColor, double borderThickness, double speed, int enemyType) {
        super(position, dimension, borderColor, bgColor, borderThickness, speed);
        this.enemyType = enemyType;
    }

    public BBoxEnemy(Point2D position, Dimension dimension, Color color, double speed, int enemyType) {
        super(position, dimension, color, speed);
        this.enemyType = enemyType;
    }

    public BBoxEnemy(Point2D position, Dimension dimension, int enemyType) {
        super(position, dimension);
        this.enemyType = enemyType;
    }

    public int getEnemyType() {
        return enemyType;
    }

    public void setEnemyType(int enemyType) {
        this.enemyType = enemyType;
    }
}
