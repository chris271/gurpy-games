package com.gurpy.games.obj.component;

import com.gurpy.games.obj.action.Action;

/**
 * Component for updating control level items.
 */
public class ControlComponent implements Component {

    private static ControlComponent controlComponent = null;

    private ControlComponent() { }

    public static ControlComponent getInstance() {
        if (controlComponent == null)
            controlComponent = new ControlComponent();

        return controlComponent;
    }

    @Override
    public boolean performAction(Action action) {
        return action.perform();
    }

}
