package com.gurpy.games.obj.entities.text;

import com.gurpy.games.obj.entities.EntityTypes;
import com.gurpy.games.obj.entities.ui.UIElement;

import java.awt.*;
import java.awt.geom.Point2D;

public class TextElement extends UIElement {

    private String text;
    private boolean staticText;

    public TextElement(Point2D position, Color textColor, Color bgColor, String text, double textSize, boolean staticText) {
        super(position, new Dimension(text.length(), (int)textSize), textColor, bgColor, textSize, EntityTypes.TEXT);
        this.text = text;
        this.staticText = staticText;
    }

    public TextElement(Point2D position, Color textColor, String text) {
        super(position, new Dimension(text.length(), 10), textColor, Color.WHITE, 10.0, EntityTypes.TEXT);
        this.text = text;
        this.staticText = false;
    }

    public TextElement(Point2D position, String text) {
        super(position, new Dimension(text.length(), 10), Color.BLACK, Color.WHITE, 10.0, EntityTypes.TEXT);
        this.text = text;
        this.staticText = false;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isStaticText() {
        return staticText;
    }

    public void setStaticText(boolean staticText) {
        this.staticText = staticText;
    }
}
