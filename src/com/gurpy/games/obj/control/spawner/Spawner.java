package com.gurpy.games.obj.control.spawner;

import com.gurpy.games.obj.entities.Entity;
import com.gurpy.games.obj.entities.ui.UIElement;

import java.util.concurrent.CopyOnWriteArrayList;

public interface Spawner extends Entity {

    public CopyOnWriteArrayList<UIElement> getSpawnList();
    public void setSpawnList(CopyOnWriteArrayList<UIElement> spawnList);
    public void addSpawnList(UIElement element);

}
