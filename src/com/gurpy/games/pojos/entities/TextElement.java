package com.gurpy.games.pojos.entities;

import java.awt.*;
import java.awt.geom.Point2D;

public class TextElement extends UIElement {

    private String text;

    public TextElement(Point2D position, Color textColor, Color bgColor, String text, double textSize) {
        super(position, textColor, bgColor, textSize, EntityTypes.TEXT);
        this.text = text;
    }

    public TextElement(Point2D position, Color textColor, String text) {
        super(position, textColor, Color.WHITE, 10.0, EntityTypes.TEXT);
        this.text = text;
    }

    public TextElement(Point2D position, String text) {
        super(position, Color.BLACK, Color.WHITE, 10.0, EntityTypes.TEXT);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
