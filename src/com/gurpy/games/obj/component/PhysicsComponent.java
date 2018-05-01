package com.gurpy.games.obj.component;

import com.gurpy.games.obj.action.Action;

/**
 * Component for updating object positions and movement.
 */
public class PhysicsComponent implements Component {

    private static PhysicsComponent physicsComponent = null;

    private PhysicsComponent() { }

    public static PhysicsComponent getInstance() {
        if (physicsComponent == null)
            physicsComponent = new PhysicsComponent();

        return physicsComponent;
    }


    @Override
    public boolean performAction(Action action) {
        return action.perform();
    }

}
