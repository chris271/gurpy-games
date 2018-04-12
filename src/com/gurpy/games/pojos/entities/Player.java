package com.gurpy.games.pojos.entities;

import com.gurpy.games.pojos.action.Action;

import java.awt.Dimension;
import java.awt.geom.Point2D;

public class Player extends BBoxElement {

    public Player(Point2D position, Dimension dimension) {
        super(position, dimension, EntityTypes.PLAYER);
    }

    @Override
    public void performAction(Action action) {
        //Generic player action
    }
}
