package com.gurpy.games.obj.component;

import com.gurpy.games.obj.action.Action;

/**
 * Component for updating object positions and movement.
 */
public class PhysicsComponent implements Component {

    @Override
    public boolean performAction(Action action) {
        return action.perform();
    }

}
