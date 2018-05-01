package com.gurpy.games.obj.component;

import com.gurpy.games.obj.action.Action;

/**
 * Component for updating the GUI elements.
 */
public class RenderingComponent implements Component {

    private static RenderingComponent renderingComponent = null;

    private RenderingComponent() { }

    public static RenderingComponent getInstance() {
        if (renderingComponent == null)
            renderingComponent = new RenderingComponent();

        return renderingComponent;
    }

    @Override
    public boolean performAction(Action action) {
        return action.perform();
    }

}