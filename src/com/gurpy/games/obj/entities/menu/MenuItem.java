package com.gurpy.games.obj.entities.menu;

import com.gurpy.games.obj.entities.ui.UIEntity;

public interface MenuItem extends UIEntity{

    String getItemText();
    void setItemText(String itemText);
    boolean isSelected();
    void setSelected(boolean selected);

}
