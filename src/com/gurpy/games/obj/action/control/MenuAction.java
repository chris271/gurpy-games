package com.gurpy.games.obj.action.control;

import com.gurpy.games.gui.GurpusUI;
import com.gurpy.games.obj.action.UIAction;
import com.gurpy.games.obj.entities.menu.Menu;
import com.gurpy.games.obj.entities.bbox.menu.BBoxMenuItem;
import com.gurpy.games.obj.entities.menu.MenuItem;

import java.awt.*;

public class MenuAction extends UIAction {

    private MenuItem menuItem;
    private GurpusUI contentPane;

    public MenuAction(Menu owner, MenuItem menuItem, GurpusUI contentPane) {
        super(owner);
        this.menuItem = menuItem;
        this.contentPane = contentPane;
    }

    @Override
    public boolean perform() {
        Menu menu = (Menu)getOwner();
        String itemText = menuItem.getItemText();
        switch (itemText) {
            case "Play Game":
                contentPane.setBackground(Color.WHITE);
                contentPane.resetGame();
                return false;
            case "Options":
                menu.setDisplay(false);
                return true;
            case "Controls":
                menu.setDisplay(false);
                return true;
            case "Exit Game":
                System.exit(1);
                return true;
            default:
                return true;
        }
    }
}
