package com.gurpy.games.obj.entities.bbox.playable;

import com.gurpy.games.obj.entities.EntityTypes;
import com.gurpy.games.obj.entities.ui.Enemy;

import java.awt.*;
import java.awt.geom.Point2D;

public class BBoxEnemy extends BBoxPlayer implements Enemy {

    private int enemyType;

    public BBoxEnemy(Dimension dimension, Color borderColor, Color bgColor, double speed, double health, int enemyType) {
        super(new Point2D.Double(0, 0), dimension, borderColor, bgColor, speed);
        this.enemyType = enemyType;
        setHealth(health);
        setMaxHealth(health);
        setScore(health);
        setType(EntityTypes.ENEMY);
    }

    public BBoxEnemy(Point2D position, Dimension dimension, Color color, double speed, int enemyType) {
        super(position, dimension, color, speed);
        this.enemyType = enemyType;
        setScore(getHealth());
        setType(EntityTypes.ENEMY);
    }

    public BBoxEnemy(Point2D position, Dimension dimension, int enemyType) {
        super(position, dimension);
        this.enemyType = enemyType;
        setScore(getHealth());
        setType(EntityTypes.ENEMY);
    }

    @Override
    public int getEnemyType() {
        return enemyType;
    }

    @Override
    public void setEnemyType(int enemyType) {
        this.enemyType = enemyType;
    }
}
