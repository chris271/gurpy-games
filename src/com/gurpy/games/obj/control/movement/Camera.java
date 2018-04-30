package com.gurpy.games.obj.control.movement;

import java.awt.geom.Rectangle2D;

public class Camera {
    
    private volatile double innerX1;
    private volatile double innerW;
    private volatile double innerY1;
    private volatile double innerH;
    private volatile double outerX1;
    private volatile double outerW;
    private volatile double outerY1;
    private volatile double outerH;
    
    
    public Camera(double innerX1, double innerW, double innerY1, double innerH, 
                  double outerX1, double outerW, double outerY1, double outerH) {
        this.innerX1 = innerX1;
        this.innerW = innerW;
        this.innerY1 = innerY1;
        this.innerH = innerH;
        this.outerX1 = outerX1;
        this.outerW = outerW;
        this.outerY1 = outerY1;
        this.outerH = outerH;
    }

    public double getInnerX1() {
        return innerX1;
    }

    public void setInnerX1(double innerX1) {
        this.innerX1 = innerX1;
    }

    public double getinnerW() {
        return innerW;
    }

    public void setinnerW(double innerW) {
        this.innerW = innerW;
    }

    public double getInnerY1() {
        return innerY1;
    }

    public void setInnerY1(double innerY1) {
        this.innerY1 = innerY1;
    }

    public double getinnerH() {
        return innerH;
    }

    public void setinnerH(double innerH) {
        this.innerH = innerH;
    }

    public double getOuterX1() {
        return outerX1;
    }

    public void setOuterX1(double outerX1) {
        this.outerX1 = outerX1;
    }

    public double getouterW() {
        return outerW;
    }

    public void setouterW(double outerW) {
        this.outerW = outerW;
    }

    public double getOuterY1() {
        return outerY1;
    }

    public void setOuterY1(double outerY1) {
        this.outerY1 = outerY1;
    }

    public double getouterH() {
        return outerH;
    }

    public void setouterH(double outerH) {
        this.outerH = outerH;
    }
    
    public Rectangle2D getInnerRect() {
        return new Rectangle2D.Double(innerX1, innerY1, innerW, innerH);
    }

    public Rectangle2D getOuterRect() {
        return new Rectangle2D.Double(outerX1, outerY1, outerW, outerH);
    }
}
