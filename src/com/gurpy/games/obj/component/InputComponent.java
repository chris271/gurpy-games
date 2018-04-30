package com.gurpy.games.obj.component;

import com.gurpy.games.obj.action.Action;

public class InputComponent implements Component{

    @Override
    public boolean performAction(Action action) {
        return action.perform();
    }

}
