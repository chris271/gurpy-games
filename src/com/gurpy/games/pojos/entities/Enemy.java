package com.gurpy.games.pojos.entities;

import com.gurpy.games.pojos.action.Action;

import java.awt.*;
import java.awt.geom.Point2D;

public class Enemy extends BBoxElement {

    public Enemy(Point2D position, Dimension dimension) {
        super(position, dimension, EntityTypes.PLAYER);
    }

    @Override
    public void performAction(Action action) {
        //Generic enemy action
    }
}
