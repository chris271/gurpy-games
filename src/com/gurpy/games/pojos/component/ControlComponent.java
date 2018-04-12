package com.gurpy.games.pojos.component;

import com.gurpy.games.pojos.action.Action;

import java.util.ArrayList;
import java.util.List;

/**
 * Component for updating control level items.
 */
public class ControlComponent implements Component {

    @Override
    public boolean performAction(Action action) {
        return action.perform();
    }

}
