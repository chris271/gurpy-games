package com.gurpy.games.obj.control.spawner;

import com.gurpy.games.obj.entities.ui.UIEntity;

import java.util.concurrent.CopyOnWriteArrayList;

public interface Spawner extends UIEntity {

    CopyOnWriteArrayList<UIEntity> getSpawnList();
    void setSpawnList(CopyOnWriteArrayList<UIEntity> spawnList);
    void addSpawnList(UIEntity element);

}
