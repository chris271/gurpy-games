package com.gurpy.games.pojos.control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public final class UIMouseAdapter implements MouseListener{

    private int xDown = -1;
    private int yDown = -1;

    @Override
    public void mousePressed(MouseEvent e) {
        //Set mouse position to current panel location.
        xDown = e.getX();
        yDown = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //When released mouse position is no longer on panel.
        xDown = -1;
        yDown = -1;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public int getXDown(){
        return xDown;
    }

    //Allow for hard mouse reset.
    public void resetXDown(){
        xDown = -1;
    }

    public int getYDown(){
        return yDown;
    }

    //Allow for hard mouse reset.
    public void resetYDown(){
        yDown = -1;
    }

}