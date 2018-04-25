package com.gurpy.games.core;

import com.gurpy.games.gui.GurpusUI;
import com.gurpy.games.pojos.action.collision.CollisionAction;
import com.gurpy.games.pojos.action.collision.CollisionCheckAction;
import com.gurpy.games.pojos.action.control.DestroyAction;
import com.gurpy.games.pojos.action.control.MenuAction;
import com.gurpy.games.pojos.action.input.PlayerMotionInputAction;
import com.gurpy.games.pojos.action.input.PlayerShootingInputAction;
import com.gurpy.games.pojos.action.movement.TranslationAction;
import com.gurpy.games.pojos.action.movement.UpdateLaserAction;
import com.gurpy.games.pojos.component.ControlComponent;
import com.gurpy.games.pojos.component.InputComponent;
import com.gurpy.games.pojos.component.PhysicsComponent;
import com.gurpy.games.pojos.control.Camera;
import com.gurpy.games.pojos.entities.*;
import com.gurpy.games.pojos.entities.Menu;
import com.gurpy.games.pojos.entities.MenuItem;
import com.gurpy.games.utils.Logger;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class GurpusCore implements Runnable{

    public static final double STEPS_PER_SEC = 200;
    private final GurpusUI contentPane;
    private final static String OS = System.getProperty("os.name").toLowerCase();
    private boolean isMenu = true;
    private PhysicsComponent physicsComponent;
    private ControlComponent controlComponent;
    private InputComponent inputComponent;

    GurpusCore(GurpusUI contentPane) {
        this.contentPane = contentPane;
        this.physicsComponent = new PhysicsComponent();
        this.controlComponent = new ControlComponent();
        this.inputComponent = new InputComponent();
    }

    public void run() {

        final long NANO_IN_MILLI = 1000000;

        final long STEP_TIME = 5 * NANO_IN_MILLI;
        Logger.info("Running on " + OS);

        while (contentPane.isShowing()) {
            long startStep = System.nanoTime();
            updateCurrentState();
            long nanoToSleep = STEP_TIME - (System.nanoTime() - startStep);
            if (nanoToSleep > 0) {
                try {
                    Thread.sleep(nanoToSleep / NANO_IN_MILLI, (int) (nanoToSleep % NANO_IN_MILLI));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        System.exit(1);
    }

    private void updateCurrentState() {
        //Iterate over each element.
        for (UIElement e : contentPane.getGuiElements()) {
            //Check if element is marked for destruction.
            if (e.isDestroy()) {
                controlComponent.performAction(new DestroyAction(e, contentPane));
                continue;
            }
            //Check collisions with other elements.
            for (UIEntity other : contentPane.getGuiElements()) {
                controlComponent.performAction(new CollisionCheckAction(e, other));
            }
            //Check user input and update physics.
            if (e instanceof BBoxPlayer) {
                BBoxPlayer player = (BBoxPlayer) e;
                if (!isMenu) {
                    if (player.isControllable()) {
                        inputComponent.performAction(new PlayerMotionInputAction(player, contentPane));
                        inputComponent.performAction(new PlayerShootingInputAction(player, contentPane));
                    } else {
                        //Player is AI controlled.
                    }
                    controlComponent.performAction(new CollisionAction(player));
                    player.setDisplay(true);
                } else {
                    player.setDisplay(false);
                }
            }
            if (e instanceof Laser) {
                Laser laser = (Laser) e;
                if (!isMenu) {
                    laser.setDisplay(true);
                    physicsComponent.performAction(new UpdateLaserAction(laser, contentPane));
                } else {
                    laser.setDisplay(false);
                }

            }
            if (e instanceof TextElement) {
                TextElement textElement = (TextElement)e;
                if (textElement.getText().contains("FPS")) {
                    textElement.setText("FPS: " + contentPane.getFps());
                } else if (!isMenu) {
                    e.setDisplay(true);
                } else {
                    e.setDisplay(false);
                }
            }
            if (e instanceof Menu) {
                Menu menu = (Menu)e;
                if (menu.isDisplay()) {
                    for (MenuItem item : menu.getMenuItems()) {
                        if (new Rectangle2D.Double(item.getX(), item.getY(), item.getWidth(), item.getHeight())
                                .contains(contentPane.getMouseX(), contentPane.getMouseY())) {
                            item.setSelected(true);
                        } else {
                            item.setSelected(false);
                        }
                        if (item.isSelected() && contentPane.mouseClick()) {
                            isMenu = controlComponent.performAction(new MenuAction(menu, item, contentPane));
                        }
                    }
                } else {
                    if (contentPane.getKeyCodes().contains(KeyEvent.VK_ESCAPE)) {
                        contentPane.setBackground(Color.BLACK);
                        isMenu = true;
                        e.setDisplay(true);
                    }
                }
            }
        }

        detectWindowChanges();

    }

    private void detectWindowChanges() {
        //On window resize update element draw positions...
        if (contentPane.getCurrentWidth() != contentPane.getWidth() || contentPane.getCurrentHeight() != contentPane.getHeight()) {
            for (UIElement e : contentPane.getGuiElements()) {
                if (!(e instanceof TextElement && ((TextElement) e).getText().contains("FPS"))) {
                    physicsComponent.performAction(new TranslationAction(e, new Point2D.Double(
                        e.getX() + (contentPane.getWidth() - contentPane.getCurrentWidth()) / 2.0,
                        e.getY() + (contentPane.getHeight() - contentPane.getCurrentHeight()) / 2.0)));

                }
            }
            contentPane.setCurrentWidth(contentPane.getWidth());
            contentPane.setCurrentHeight(contentPane.getHeight());
            contentPane.setCamera(new Camera(0, contentPane.getCurrentWidth(),
                    0, contentPane.getCurrentHeight(),
                    contentPane.MIN_X, contentPane.MAX_X - contentPane.MIN_X,
                    contentPane.MIN_Y, contentPane.MAX_Y - contentPane.MIN_Y));
        }

        if (!contentPane.isShowing()) {
            Logger.error("Cannot find GUI!");
            System.exit(0);
        }
    }

}

