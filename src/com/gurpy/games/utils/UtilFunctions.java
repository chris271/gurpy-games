package com.gurpy.games.utils;

import java.awt.geom.Point2D;

public class UtilFunctions {

    public static double getAngle(Point2D target, Point2D src) {
        double angle = Math.toDegrees(Math.atan2(target.getY() - src.getY(), target.getX() - src.getX()));

        if(angle < 0){
            angle += 360;
        }

        return Math.toRadians(angle);
    }
}
