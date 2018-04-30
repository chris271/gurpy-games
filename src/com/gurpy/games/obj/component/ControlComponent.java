package com.gurpy.games.obj.component;

import com.gurpy.games.obj.action.Action;

/**
 * Component for updating control level items.
 */
public class ControlComponent implements Component {

    @Override
    public boolean performAction(Action action) {
        return action.perform();
    }

}
