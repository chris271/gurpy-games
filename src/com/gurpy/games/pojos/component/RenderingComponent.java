package com.gurpy.games.pojos.component;

import com.gurpy.games.pojos.action.Action;

/**
 * Component for updating the GUI elements.
 */
public class RenderingComponent implements Component {

    @Override
    public boolean performAction(Action action) {
        return action.perform();
    }

}