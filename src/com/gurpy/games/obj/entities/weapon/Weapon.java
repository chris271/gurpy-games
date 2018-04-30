package com.gurpy.games.obj.entities.weapon;

import com.gurpy.games.obj.entities.EntityTypes;
import com.gurpy.games.obj.entities.ui.UIElement;

import java.awt.*;

public abstract class Weapon extends UIElement {

    UIElement owner;

    Weapon(UIElement owner, Color borderColor, Color bgColor) {
        super(owner.getPosition(), borderColor, bgColor, owner.getBorderThickness(), EntityTypes.WEAPON);
        this.owner = owner;
    }

    Weapon(UIElement owner, Color color) {
        super(owner.getPosition(), color, color, owner.getBorderThickness(),  EntityTypes.WEAPON);
        this.owner = owner;
    }

    Weapon(UIElement owner) {
        super(owner.getPosition(), owner.getBorderColor(), owner.getBgColor(), owner.getBorderThickness(), EntityTypes.WEAPON);
        this.owner = owner;
    }

    public UIElement getOwner() {
        return owner;
    }

    public void setOwner(UIElement owner) {
        this.owner = owner;
    }
}
