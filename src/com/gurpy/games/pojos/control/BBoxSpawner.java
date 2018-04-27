package com.gurpy.games.pojos.control;

import com.gurpy.games.pojos.entities.EntityTypes;
import com.gurpy.games.pojos.entities.UIElement;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class BBoxSpawner implements Spawner {

    private CopyOnWriteArrayList<UIElement> spawnList;
    private Rectangle2D.Double innerBox;
    private Rectangle2D.Double outerBox;
    private int type;

    public BBoxSpawner(Rectangle2D.Double innerBox, Rectangle2D.Double outerBox) {
        this.spawnList = new CopyOnWriteArrayList<>();
        this.innerBox = innerBox;
        this.outerBox = outerBox;
        this.type = EntityTypes.SPAWNER;
    }

    public BBoxSpawner(Rectangle2D.Double bBox) {
        this.spawnList = new CopyOnWriteArrayList<>();
        this.innerBox = bBox;
        this.outerBox = new Rectangle2D.Double(0, 0, 0, 0);
        this.type = EntityTypes.SPAWNER;
    }

    @Override
    public CopyOnWriteArrayList<UIElement> getSpawnList() {
        return spawnList;
    }

    @Override
    public void setSpawnList(CopyOnWriteArrayList<UIElement> spawnList) {
        this.spawnList = spawnList;
    }

    @Override
    public void addSpawnList(UIElement element) {
        spawnList.add(element);
    }

    public Rectangle2D.Double getInnerBox() {
        return innerBox;
    }

    public void setInnerBox(Rectangle2D.Double innerBox) {
        this.innerBox = innerBox;
    }

    public Rectangle2D.Double getOuterBox() {
        return outerBox;
    }

    public void setOuterBox(Rectangle2D.Double outerBox) {
        this.outerBox = outerBox;
    }

    public boolean randomSpawn(double chance) {
        return new Random().nextDouble() < chance;
    }

    public Point2D.Double randomLocation() {
        Random random = new Random();
        Point2D.Double rngPoint = new Point2D.Double(
                random.nextInt((int)outerBox.width) + outerBox.x,
                random.nextInt((int)outerBox.height) + outerBox.y);
        while (innerBox.contains(rngPoint)) {
            rngPoint = new Point2D.Double(
                    random.nextInt((int)outerBox.width) + outerBox.x,
                    random.nextInt((int)outerBox.height) + outerBox.y);
        }
        return rngPoint;
    }

    public UIElement nextElement() {
        return spawnList.get(new Random().nextInt(spawnList.size()));
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public void setType(int type) {
        this.type = type;
    }
}
