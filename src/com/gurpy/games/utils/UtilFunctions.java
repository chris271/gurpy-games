package com.gurpy.games.utils;

import java.awt.geom.Point2D;

public class UtilFunctions {

    public static float getAngle(Point2D src, Point2D target) {
        float angle = (float) Math.toDegrees(Math.atan2(target.getY() - src.getY(), target.getX() - src.getX()));

        if(angle < 0){
            angle += 360;
        }

        return angle;
    }
}
