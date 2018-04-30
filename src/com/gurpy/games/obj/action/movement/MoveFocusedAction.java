package com.gurpy.games.obj.action.movement;

import com.gurpy.games.obj.action.UIAction;
import com.gurpy.games.obj.control.movement.Camera;
import com.gurpy.games.obj.entities.*;
import com.gurpy.games.obj.entities.bbox.playable.BBoxPlayer;
import com.gurpy.games.obj.entities.menu.Menu;
import com.gurpy.games.obj.entities.text.TextElement;
import com.gurpy.games.obj.entities.ui.UIElement;
import com.gurpy.games.obj.entities.ui.UIEntity;

import java.awt.geom.Point2D;
import java.util.concurrent.CopyOnWriteArrayList;

public class MoveFocusedAction extends UIAction {

    private Camera camera;
    private CopyOnWriteArrayList<UIEntity> guiElements;
    private double xAmount, yAmount;

    public MoveFocusedAction(UIEntity owner, Camera camera, CopyOnWriteArrayList<UIEntity> guiElements, double xAmount, double yAmount) {
        super(owner);
        this.camera = camera;
        this.guiElements = guiElements;
        this.xAmount = xAmount;
        this.yAmount = yAmount;
    }

    @Override
    public boolean perform() {
        if (getOwner() instanceof BBoxPlayer) {
            BBoxPlayer player = (BBoxPlayer)getOwner();
            if (player.isFocused()) {
                //Adjust x amount of elements according to relative player position
                player.setRelativeX(player.getRelativeX() + xAmount);
                if (player.getRelativeX() > camera.getOuterX1() + camera.getinnerW() / 2 &&
                        player.getRelativeX() < camera.getOuterX1() + camera.getouterW() - camera.getinnerW() / 2) {
                    for (UIEntity uiElement : guiElements) {
                        if ((uiElement instanceof TextElement && ((TextElement) uiElement).isStaticText()) || uiElement instanceof Menu)
                            continue;
                        if (!getOwner().equals(uiElement)) {
                            new TranslationAction(uiElement,
                                    new Point2D.Double(uiElement.getX() - xAmount, uiElement.getY())).perform();
                        }
                    }
                } else if (player.getRelativeX() > camera.getOuterX1() + player.getWidth() / 2 &&
                        player.getRelativeX() < camera.getOuterX1() + camera.getouterW() - player.getWidth() / 2) {
                    new TranslationAction(player,
                            new Point2D.Double(player.getX() + xAmount, player.getY())).perform();
                } else {
                    player.setRelativeX(player.getRelativeX() - xAmount);
                    if (player.getX() < camera.getInnerX1()) {
                        new TranslationAction(player,
                                new Point2D.Double(camera.getInnerX1(), player.getY())).perform();
                    } else if (player.getX() + player.getWidth() > camera.getInnerX1() + camera.getinnerW()) {
                        new TranslationAction(player,
                                new Point2D.Double(camera.getInnerX1() + camera.getinnerW() - player.getWidth(), player.getY())).perform();
                    }
                }
                //Adjust y amount of elements according to relative player position
                player.setRelativeY(player.getRelativeY() + yAmount);
                if (player.getRelativeY() > camera.getOuterY1() + camera.getinnerH() / 2 &&
                        player.getRelativeY() < camera.getOuterY1() + camera.getouterH() - camera.getinnerH() / 2) {
                    for (UIEntity uiElement : guiElements) {
                        if ((uiElement instanceof TextElement && ((TextElement) uiElement).isStaticText()) || uiElement instanceof Menu)
                            continue;
                        if (!getOwner().equals(uiElement)) {
                            new TranslationAction(uiElement,
                                    new Point2D.Double(uiElement.getX(), uiElement.getY() - yAmount)).perform();
                        }
                    }
                } else if (player.getRelativeY() > camera.getOuterY1() + player.getHeight() / 2 &&
                        player.getRelativeY() < camera.getOuterY1() + camera.getouterH() - player.getHeight() / 2) {
                    new TranslationAction(player,
                            new Point2D.Double(player.getX(), player.getY() + yAmount)).perform();
                } else {
                    player.setRelativeY(player.getRelativeY() - yAmount);
                    if (player.getY() < camera.getInnerY1()) {
                        new TranslationAction(player,
                                new Point2D.Double(player.getX(), camera.getInnerY1())).perform();
                    } else if (player.getY() + player.getHeight() > camera.getInnerY1() + camera.getinnerH()) {
                        new TranslationAction(player,
                                new Point2D.Double(player.getX(), camera.getInnerY1() + camera.getinnerH() - player.getHeight())).perform();
                    }
                }
            }
            return true;
        }
        return false;
    }
}
