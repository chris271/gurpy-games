package com.gurpy.games.pojos.action;

import java.util.ArrayList;

public abstract class UIAction implements Action {
    @Override
    public void perform(String operation, ArrayList<Object> operands) {
        //Generic UI operation.
    }
}
