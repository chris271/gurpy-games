package com.gurpy.games.core;

import com.gurpy.games.gui.GurpusUI;
import com.gurpy.games.obj.action.collision.CollisionCheckAction;
import com.gurpy.games.obj.action.control.DestroyAction;
import com.gurpy.games.obj.action.control.MenuAction;
import com.gurpy.games.obj.action.control.SpawnAction;
import com.gurpy.games.obj.action.control.StepSpawnerAction;
import com.gurpy.games.obj.action.input.PlayerMotionInputAction;
import com.gurpy.games.obj.action.input.PlayerShootingInputAction;
import com.gurpy.games.obj.action.movement.TranslationAction;
import com.gurpy.games.obj.action.movement.UpdateAIAction;
import com.gurpy.games.obj.action.movement.UpdateProjectileAction;
import com.gurpy.games.obj.component.ControlComponent;
import com.gurpy.games.obj.component.InputComponent;
import com.gurpy.games.obj.component.PhysicsComponent;
import com.gurpy.games.obj.control.spawner.BBoxSpawner;
import com.gurpy.games.obj.control.movement.Camera;
import com.gurpy.games.obj.entities.menu.Menu;
import com.gurpy.games.obj.entities.bbox.menu.BBoxMenuItem;
import com.gurpy.games.obj.entities.bbox.playable.BBoxEnemy;
import com.gurpy.games.obj.entities.bbox.playable.BBoxPlayer;
import com.gurpy.games.obj.entities.bbox.playable.EnemyTypes;
import com.gurpy.games.obj.entities.line.weapon.Laser;
import com.gurpy.games.obj.entities.menu.MenuItem;
import com.gurpy.games.obj.entities.text.TextElement;
import com.gurpy.games.obj.entities.ui.Playable;
import com.gurpy.games.obj.entities.ui.UIEntity;
import com.gurpy.games.utils.Logger;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class GurpusCore implements Runnable{

    public static final double STEPS_PER_SEC = 200;
    private final GurpusUI contentPane;
    private final PhysicsComponent physicsComponent;
    private final ControlComponent controlComponent;
    private final InputComponent inputComponent;
    private final static String OS = System.getProperty("os.name").toLowerCase();
    private boolean isMenu = true;
    private boolean gameOver = false;
    private Playable mainPlayer;

    GurpusCore(GurpusUI contentPane) {
        this.contentPane = contentPane;
        this.physicsComponent = new PhysicsComponent();
        this.controlComponent = new ControlComponent();
        this.inputComponent = new InputComponent();
        mainPlayer = new BBoxPlayer(new Point2D.Double(0, 0), new Dimension(0, 0));
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
        for (UIEntity e : contentPane.getGuiElements()) {
            //Check if element is marked for destruction.
            if (e.isDestroy()) {
                if (e.equals(mainPlayer)) {
                    TextElement textElement = new TextElement(
                            new Point2D.Double(contentPane.getWidth() / 2, contentPane.getHeight() / 2),
                            "OH...YOU DIED... PRESS ENTER OR R TO RESTART...");
                    textElement.addToX(-textElement.getText().length() * textElement.getBorderThickness() / 4);
                    controlComponent.performAction(new SpawnAction(textElement, contentPane));
                    gameOver = true;
                }
                controlComponent.performAction(new DestroyAction(e, contentPane));
            } else {
                //Check each Playable UIEntity.
                if (e instanceof Playable) {
                    Playable player = (Playable) e;
                    if (player.isFocused()) {
                        mainPlayer = player;
                        if (player.getHealth() <= 0) {
                            player.setDestroy(true);
                        }
                    }
                    if (!isMenu) {
                        player.setDisplay(true);
                        if (player.isControllable()) {
                            inputComponent.performAction(new PlayerMotionInputAction(player, contentPane));
                            inputComponent.performAction(new PlayerShootingInputAction(player, contentPane));
                        } else {
                            physicsComponent.performAction(new UpdateAIAction(player, mainPlayer));
                        }
                    } else {
                        player.setDisplay(false);
                    }
                }
                if (e instanceof Laser) {
                    Laser laser = (Laser) e;
                    if (!isMenu) {
                        laser.setDisplay(true);
                        physicsComponent.performAction(new UpdateProjectileAction(laser, contentPane));
                    } else {
                        laser.setDisplay(false);
                    }

                }
                if (e instanceof TextElement) {
                    TextElement textElement = (TextElement) e;
                    if (e.equals(contentPane.getFpsCounter())) {
                        textElement.setText("FPS: " + contentPane.getFps());
                    } else if (!isMenu) {
                        e.setDisplay(true);
                        if (textElement.getText().contains("Kill Count")) {
                            textElement.setText("Kill Count: " + mainPlayer.getKillCount());
                        } else if (textElement.getText().contains("Score")) {
                            textElement.setText("Score: " + mainPlayer.getScore());
                        }
                    } else {
                        e.setDisplay(false);
                    }
                }
                if (e instanceof Menu) {
                    Menu menu = (Menu) e;
                    if (menu.isDisplay()) {
                        for (MenuItem item : menu.getMenuItems()) {
                            if (item instanceof BBoxMenuItem) {
                                if (((BBoxMenuItem)item).getBBox()
                                        .contains(contentPane.getMouseX(), contentPane.getMouseY())) {
                                    item.setSelected(true);
                                } else {
                                    item.setSelected(false);
                                }
                            }
                            if (item.isSelected() && contentPane.mouseClick()) {
                                isMenu = controlComponent.performAction(new MenuAction(menu, item, contentPane));
                                if (!isMenu) {
                                    contentPane.resetGame();
                                    break;
                                }
                            }
                        }
                        if (!isMenu)
                            break;
                    } else {
                        if (contentPane.getKeyCodes().contains(KeyEvent.VK_ESCAPE)) {
                            isMenu = true;
                            e.setDisplay(true);
                        }
                    }
                }

                //Check collisions
                for (UIEntity other : contentPane.getGuiElements()) {
                    if (!other.equals(e) && !other.isDestroy() && !e.isDestroy()) {
                        controlComponent.performAction(new CollisionCheckAction(e, other));
                    }
                    if (e.isDestroy()) {
                        break;
                    }
                }
            }
        }



        if (gameOver && (contentPane.getKeyCodes().contains(KeyEvent.VK_ENTER) ||
                         contentPane.getKeyCodes().contains(KeyEvent.VK_R))) {
            gameOver = false;
            contentPane.resetGame();
        }

        stepSpawner();
        detectWindowChanges();

    }

    private void detectWindowChanges() {
        //On window resize update element draw positions...
        if (contentPane.getCurrentWidth() != contentPane.getWidth() || contentPane.getCurrentHeight() != contentPane.getHeight()) {
            for (UIEntity e : contentPane.getGuiElements()) {
                if (!(e instanceof TextElement && ((TextElement)e).isStaticText())) {
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

        if (isMenu) {
            contentPane.setBackground(Color.BLACK);
        } else {
            contentPane.setBackground(Color.WHITE);
        }

        if (!contentPane.isShowing()) {
            Logger.error("Cannot find GUI!");
            System.exit(0);
        }
    }

    private void stepSpawner() {
        BBoxSpawner mainSpawner = new BBoxSpawner(
                new Rectangle2D.Double(0, 0, contentPane.getCurrentWidth(), contentPane.getCurrentHeight()),
                new Rectangle2D.Double(contentPane.MIN_X, contentPane.MIN_Y,
                        contentPane.MAX_X - contentPane.MIN_X, contentPane.MAX_Y - contentPane.MIN_Y));
        mainSpawner.addSpawnList(new BBoxEnemy(
                new Dimension(100, 100),
                Color.BLACK,
                Color.YELLOW,
                2,
                200,
                EnemyTypes.HOMING));
        mainSpawner.addSpawnList(new BBoxEnemy(
                new Dimension(25, 25),
                Color.BLACK,
                Color.GREEN,
                2,
                5,
                EnemyTypes.HOMING));
        mainSpawner.addSpawnList(new BBoxEnemy(
                new Dimension(50, 250),
                Color.BLACK,
                Color.CYAN,
                2,
                25,
                EnemyTypes.HOMING));
        mainSpawner.addSpawnList(new BBoxEnemy(
                new Dimension(50, 50),
                Color.RED,
                Color.BLUE,
                2,
                75,
                EnemyTypes.HOMING));
        mainSpawner.addSpawnList(new BBoxEnemy(
                new Dimension(400, 400),
                Color.BLUE,
                Color.ORANGE,
                2,
                250,
                EnemyTypes.HOMING));
        mainSpawner.addSpawnList(new BBoxEnemy(
                new Dimension(300, 300),
                Color.MAGENTA,
                Color.CYAN,
                2,
                225,
                EnemyTypes.HOMING));
        mainSpawner.addSpawnList(new BBoxEnemy(
                new Dimension(100, 200),
                Color.MAGENTA,
                Color.PINK,
                2,
                215,
                EnemyTypes.HOMING));
        controlComponent.performAction(new StepSpawnerAction(mainSpawner, contentPane, .05));
    }

}

