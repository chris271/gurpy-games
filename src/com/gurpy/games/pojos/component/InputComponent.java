package com.gurpy.games.pojos.component;

import com.gurpy.games.pojos.action.Action;

public class InputComponent implements Component{

    @Override
    public boolean performAction(Action action) {
        return action.perform();
    }

}
