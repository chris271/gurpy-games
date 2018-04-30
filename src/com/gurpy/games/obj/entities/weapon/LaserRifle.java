package com.gurpy.games.obj.entities.weapon;

import com.gurpy.games.obj.entities.ui.UIElement;

import java.awt.*;

public class LaserRifle extends Weapon {
    public LaserRifle(UIElement owner, Color borderColor, Color bgColor) {
        super(owner, borderColor, bgColor);
    }

    public LaserRifle(UIElement owner, Color color) {
        super(owner, color);
    }

    public LaserRifle(UIElement owner) {
        super(owner);
    }

}
