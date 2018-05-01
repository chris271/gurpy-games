package com.gurpy.games.obj.component;

import com.gurpy.games.obj.action.Action;

public class InputComponent implements Component{

    private static InputComponent inputComponent = null;

    private InputComponent() { }

    public static InputComponent getInstance() {
        if (inputComponent == null)
            inputComponent = new InputComponent();

        return inputComponent;
    }


    @Override
    public boolean performAction(Action action) {
        return action.perform();
    }

}
