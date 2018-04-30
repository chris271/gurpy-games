package com.gurpy.games.obj.component;

import com.gurpy.games.obj.action.Action;

/**
 * Component for updating the GUI elements.
 */
public class RenderingComponent implements Component {

    @Override
    public boolean performAction(Action action) {
        return action.perform();
    }

}