package com.gurpy.games.pojos.entities;

import java.awt.*;
import java.awt.geom.Point2D;

public class MenuItem extends BBoxElement {

    private String itemText;
    private boolean selected;

    public MenuItem(Point2D position, Dimension dimension, Color borderColor, Color bgColor,
                    String text, double borderThickness) {
        super(position, dimension, borderColor, bgColor, borderThickness, EntityTypes.MENU_ITEM);
        this.itemText = text;
        this.selected = false;
    }

    public String getItemText() {
        return itemText;
    }

    public void setItemText(String itemText) {
        this.itemText = itemText;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
