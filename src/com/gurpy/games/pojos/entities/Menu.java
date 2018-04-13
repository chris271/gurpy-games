package com.gurpy.games.pojos.entities;

import java.awt.geom.Point2D;
import java.util.concurrent.CopyOnWriteArrayList;

public final class Menu extends UIElement{

    private TextElement title;
    private CopyOnWriteArrayList<MenuItem> menuItems;
    private int selectedItem;

    public Menu(TextElement title, CopyOnWriteArrayList<MenuItem> menuItems, boolean display) {
        super(new Point2D.Double(0, 0), EntityTypes.MENU);
        this.title = title;
        this.menuItems = menuItems;
        setDisplay(display);
    }

    public TextElement getTitle() {
        return title;
    }

    public void setTitle(TextElement title) {
        this.title = title;
    }

    public CopyOnWriteArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(CopyOnWriteArrayList<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public int getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(int selectedItem) {
        this.selectedItem = selectedItem;
    }

}
