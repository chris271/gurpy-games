package com.gurpy.games.pojos.control;

import java.awt.event.*;

public final class UIMouseListener implements MouseListener, MouseMotionListener, MouseWheelListener{

    private double xPos = -1;
    private double yPos = -1;
    private double xPress = -1;
    private double yPress = -1;

    @Override
    public void mousePressed(MouseEvent e) {
        //Set mouse position to current panel location.
        xPress = e.getX();
        yPress = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        xPress = -1;
        yPress = -1;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //Not implemented...
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //Not implemented...
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //Not implemented...
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //Not implemented...
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        xPos = e.getX();
        yPos = e.getY();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        //Not implemented...
    }

    public double getxPos() {
        return xPos;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    public double getxPress() {
        return xPress;
    }

    public void setxPress(double xPress) {
        this.xPress = xPress;
    }

    public double getyPress() {
        return yPress;
    }

    public void setyPress(double yPress) {
        this.yPress = yPress;
    }
}