package com.gurpy.games.pojos.entities;

import com.gurpy.games.pojos.control.Direction;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class LineElement extends UIElement {

    private ArrayList<Line2D> lines = new ArrayList<>();
    private int direction;

    public LineElement(Line2D.Double line, Color borderColor, Color bgColor,
                        double borderThickness, int type, int direction) {
        super(line.getP1(), borderColor, bgColor, borderThickness, type);
        this.direction = direction;
        if (this.direction == Direction.UP || this.direction == Direction.DOWN) {
            for (int i = 0; i < borderThickness; i++) {
                lines.add(new Line2D.Double(
                        new Point2D.Double(line.getP1().getX() + i, line.getP1().getY()),
                        new Point2D.Double(line.getP2().getX() + i, line.getP2().getY())));
            }
        }
        else if (this.direction == Direction.DOWN_RIGHT || this.direction == Direction.UP_LEFT) {
            for (int i = 0; i < borderThickness; i++) {
                lines.add(new Line2D.Double(
                        new Point2D.Double(line.getP1().getX() + i, line.getP1().getY() - 1),
                        new Point2D.Double(line.getP2().getX() + i, line.getP2().getY() - 1)));
            }
        }
        else if (this.direction == Direction.UP_RIGHT || this.direction == Direction.DOWN_LEFT) {
            for (int i = 0; i < borderThickness; i++) {
                lines.add(new Line2D.Double(
                        new Point2D.Double(line.getP1().getX() + i, line.getP1().getY() + i),
                        new Point2D.Double(line.getP2().getX() + i, line.getP2().getY() + i)));
            }
        }
        else if (this.direction == Direction.RIGHT || this.direction == Direction.LEFT) {
            for (int i = 0; i < borderThickness; i++) {
                lines.add(new Line2D.Double(
                        new Point2D.Double(line.getP1().getX(), line.getP1().getY() + i),
                        new Point2D.Double(line.getP2().getX(), line.getP2().getY() + i)));
            }
        }

    }

    @Override
    public void setPosition(Point2D position) {
        if (this.direction == Direction.UP || this.direction == Direction.DOWN) {
            for (int i = 0; i < lines.size(); i++) {
                Line2D line = lines.get(i);
                lines.get(i).setLine(
                        position.getX() + i,
                        position.getY(),
                        position.getX() + (line.getX2() - line.getX1()) + i,
                        position.getY() + (line.getY2() - line.getY1()));
            }
        }
        else if (this.direction == Direction.DOWN_RIGHT || this.direction == Direction.UP_LEFT) {
            for (int i = 0; i < lines.size(); i++) {
                Line2D line = lines.get(i);
                lines.get(i).setLine(
                        position.getX() + i,
                        position.getY() - i,
                        position.getX() + (line.getX2() - line.getX1()) + i,
                        position.getY() + (line.getY2() - line.getY1()) - i);
            }
        }
        else if (this.direction == Direction.UP_RIGHT || this.direction == Direction.DOWN_LEFT) {
            for (int i = 0; i < lines.size(); i++) {
                Line2D line = lines.get(i);
                lines.get(i).setLine(
                        position.getX() + i,
                        position.getY() + i,
                        position.getX() + (line.getX2() - line.getX1()) + i,
                        position.getY() + (line.getY2() - line.getY1()) + i);
            }
        }
        else if (this.direction == Direction.RIGHT || this.direction == Direction.LEFT) {
            for (int i = 0; i < lines.size(); i++) {
                Line2D line = lines.get(i);
                lines.get(i).setLine(
                        position.getX(),
                        position.getY() + i,
                        position.getX() + (line.getX2() - line.getX1()),
                        position.getY() + (line.getY2() - line.getY1()) + i);
            }
        }
        super.setPosition(position);
    }

    public ArrayList<Line2D> getLines() {
        return lines;
    }

    public void setLines(ArrayList<Line2D> lines) {
        this.lines = lines;
    }


    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
