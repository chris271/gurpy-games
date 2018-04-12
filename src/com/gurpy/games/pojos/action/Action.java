package com.gurpy.games.pojos.action;

import java.util.ArrayList;

public interface Action {
    public void perform(String operation, ArrayList<Object> operands);
}
