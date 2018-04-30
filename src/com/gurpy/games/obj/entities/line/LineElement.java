package com.gurpy.games.obj.entities.line;

import com.gurpy.games.obj.entities.ui.UIElement;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class LineElement extends UIElement {

    private ArrayList<Line2D> lines = new ArrayList<>();
    private double direction;

    public LineElement(Line2D.Double line, Color borderColor, Color bgColor,
                        double borderThickness, int type, double direction) {
        super(line.getP1(), borderColor, bgColor, borderThickness, type);
        this.direction = direction;
        for (int i = 0; i < borderThickness; i++) {
            lines.add(new Line2D.Double(
                    new Point2D.Double(line.getP1().getX() - (i * Math.sin(direction)), line.getP1().getY() + (i * Math.cos(direction))),
                    new Point2D.Double(line.getP2().getX() - (i * Math.sin(direction)), line.getP2().getY() + (i * Math.cos(direction)))));
        }

    }

    @Override
    public void setPosition(Point2D position) {
        for (int i = 0; i < lines.size(); i++) {
            Line2D line = lines.get(i);
            lines.get(i).setLine(
                    position.getX() - (i * Math.sin(direction)),
                    position.getY() + (i * Math.cos(direction)),
                    position.getX() + (line.getX2() - line.getX1()) - (i * Math.sin(direction)),
                    position.getY() + (line.getY2() - line.getY1()) + (i * Math.cos(direction)));
        }
        super.setPosition(position);
    }

    public ArrayList<Line2D> getLines() {
        return lines;
    }

    public void setLines(ArrayList<Line2D> lines) {
        this.lines = lines;
    }


    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }
}
