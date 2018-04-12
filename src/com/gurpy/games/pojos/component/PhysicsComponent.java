package com.gurpy.games.pojos.component;

import com.gurpy.games.pojos.action.Action;

/**
 * Component for updating object positions and movement.
 */
public class PhysicsComponent implements Component {

    @Override
    public boolean performAction(Action action) {
        return action.perform();
    }

}
