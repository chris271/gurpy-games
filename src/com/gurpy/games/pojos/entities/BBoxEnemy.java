package com.gurpy.games.pojos.entities;

import java.awt.*;
import java.awt.geom.Point2D;

public class BBoxEnemy extends BBoxPlayer {

    private int enemyType;

    public BBoxEnemy(Dimension dimension, Color borderColor, Color bgColor, double borderThickness, double speed, double health, int enemyType) {
        super(new Point2D.Double(0, 0), dimension, borderColor, bgColor, borderThickness, speed);
        this.enemyType = enemyType;
        setHealth(health);
        setMaxHealth(health);
        setScore(health);
    }

    public BBoxEnemy(Point2D position, Dimension dimension, Color color, double speed, int enemyType) {
        super(position, dimension, color, speed);
        this.enemyType = enemyType;
        setScore(getHealth());
    }

    public BBoxEnemy(Point2D position, Dimension dimension, int enemyType) {
        super(position, dimension);
        this.enemyType = enemyType;
        setScore(getHealth());
    }

    public int getEnemyType() {
        return enemyType;
    }

    public void setEnemyType(int enemyType) {
        this.enemyType = enemyType;
    }
}
