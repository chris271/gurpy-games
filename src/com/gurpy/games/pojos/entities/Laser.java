package com.gurpy.games.pojos.entities;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Laser extends LineElement {

    private double speed;

    public Laser(Point2D positionA, Point2D positionB, Color borderColor, Color bgColor,
                 double borderThickness, double speed, int direction) {
        super(new Line2D.Double(positionA, positionB), borderColor, bgColor, borderThickness, EntityTypes.LASER, direction);
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
