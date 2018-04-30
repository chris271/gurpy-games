package com.gurpy.games.obj.control.spawner;

import com.gurpy.games.obj.entities.EntityTypes;
import com.gurpy.games.obj.entities.ui.UIElement;
import com.gurpy.games.obj.entities.ui.UIEntity;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class BBoxSpawner implements Spawner {

    private CopyOnWriteArrayList<UIEntity> spawnList;
    private Rectangle2D.Double innerBox;
    private Rectangle2D.Double outerBox;
    private Color borderColor;
    private Color bgColor;
    private boolean destroy;
    private boolean display;
    private double borderThickness;
    private int type;

    public BBoxSpawner(Rectangle2D.Double innerBox, Rectangle2D.Double outerBox) {
        this.spawnList = new CopyOnWriteArrayList<>();
        this.innerBox = innerBox;
        this.outerBox = outerBox;
        this.type = EntityTypes.SPAWNER;
        this.borderColor = Color.BLACK;
        this.bgColor = Color.WHITE;
        this.borderThickness = 0;
    }

    public BBoxSpawner(Rectangle2D.Double bBox) {
        this.spawnList = new CopyOnWriteArrayList<>();
        this.innerBox = new Rectangle2D.Double(0, 0, 0, 0);
        this.outerBox = bBox;
        this.type = EntityTypes.SPAWNER;
        this.borderColor = Color.BLACK;
        this.bgColor = Color.WHITE;
        this.borderThickness = 0;
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

    public UIEntity nextElement() {
        return spawnList.get(new Random().nextInt(spawnList.size()));
    }

    @Override
    public CopyOnWriteArrayList<UIEntity> getSpawnList() {
        return spawnList;
    }

    @Override
    public void setSpawnList(CopyOnWriteArrayList<UIEntity> spawnList) {
        this.spawnList = spawnList;
    }

    @Override
    public void addSpawnList(UIEntity element) {
        spawnList.add(element);
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public void setType(int type) {
        this.type = type;
    }

    @Override
    public double getX() {
        return outerBox.getX();
    }

    @Override
    public double getY() {
        return outerBox.getY();
    }

    @Override
    public void setX(double x) {
        innerBox.setRect(x, innerBox.getY(), innerBox.getWidth(), innerBox.getHeight());
        outerBox.setRect((innerBox.getX() - outerBox.getX()) + x, outerBox.getY(), outerBox.getWidth(), outerBox.getHeight());
    }

    @Override
    public void setY(double y) {
        innerBox.setRect(innerBox.getX(), y, innerBox.getWidth(), innerBox.getHeight());
        outerBox.setRect(outerBox.getX(), (innerBox.getX() - outerBox.getX()) + y, outerBox.getWidth(), outerBox.getHeight());
    }

    @Override
    public double getWidth() {
        return outerBox.getWidth();
    }

    @Override
    public double getHeight() {
        return outerBox.getHeight();
    }

    @Override
    public void setWidth(double width) {
        outerBox.setRect(outerBox.getX(), outerBox.getY(), width, outerBox.getHeight());
    }

    @Override
    public void setHeight(double height) {
        outerBox.setRect(outerBox.getX(), outerBox.getY(), outerBox.getWidth(), height);
    }

    @Override
    public void addToX(double x) {
        innerBox.setRect(innerBox.getX() + x, innerBox.getY(), innerBox.getWidth(), innerBox.getHeight());
        outerBox.setRect(outerBox.getX() + x, outerBox.getY(), outerBox.getWidth(), outerBox.getHeight());
    }

    @Override
    public void addToY(double y) {
        innerBox.setRect(innerBox.getX(), innerBox.getY() + y, innerBox.getWidth(), innerBox.getHeight());
        outerBox.setRect(outerBox.getX(), outerBox.getY() + y, outerBox.getWidth(), outerBox.getHeight());
    }

    @Override
    public void setPosition(Point2D position) {
        setX(position.getX());
        setY(position.getY());
    }

    @Override
    public Color getBorderColor() {
        return borderColor;
    }

    @Override
    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    @Override
    public Color getBgColor() {
        return bgColor;
    }

    @Override
    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
    }

    @Override
    public double getBorderThickness() {
        return borderThickness;
    }

    @Override
    public void setBorderThickness(double borderThickness) {
        this.borderThickness = borderThickness;
    }

    @Override
    public Point2D getPosition() {
        return new Point2D.Double(getX(), getY());
    }

    @Override
    public void setDisplay(boolean display) {
        this.display = display;
    }

    @Override
    public boolean isDisplay() {
        return display;
    }

    @Override
    public void setDestroy(boolean destroy) {
        this.destroy = destroy;
    }

    @Override
    public boolean isDestroy() {
        return destroy;
    }
}
