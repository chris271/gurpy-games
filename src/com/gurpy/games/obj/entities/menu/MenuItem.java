package com.gurpy.games.obj.entities.menu;

import com.gurpy.games.obj.entities.ui.UIEntity;

public interface MenuItem extends UIEntity{

    public String getItemText();
    public void setItemText(String itemText);
    public boolean isSelected();
    public void setSelected(boolean selected);

}
