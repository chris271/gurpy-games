package com.gurpy.games.pojos.control;

import com.gurpy.games.pojos.entities.Entity;
import com.gurpy.games.pojos.entities.UIElement;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public interface Spawner extends Entity {

    public CopyOnWriteArrayList<UIElement> getSpawnList();
    public void setSpawnList(CopyOnWriteArrayList<UIElement> spawnList);
    public void addSpawnList(UIElement element);

}
