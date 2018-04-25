package com.gurpy.games.core;

import com.gurpy.games.gui.GurpusUI;
import com.gurpy.games.pojos.action.*;
import com.gurpy.games.pojos.component.ControlComponent;
import com.gurpy.games.pojos.component.PhysicsComponent;
import com.gurpy.games.pojos.control.Camera;
import com.gurpy.games.pojos.control.Direction;
import com.gurpy.games.pojos.entities.*;
import com.gurpy.games.pojos.entities.Menu;
import com.gurpy.games.pojos.entities.MenuItem;
import com.gurpy.games.utils.Logger;
import com.gurpy.games.utils.UtilFunctions;

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

    GurpusCore(GurpusUI contentPane) {
        this.contentPane = contentPane;
        this.physicsComponent = new PhysicsComponent();
        this.controlComponent = new ControlComponent();
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
                controlComponent.performAction(new CollisionAction(e, other));
            }
            //Check user input and update physics.
            if (e instanceof BBoxPlayer) {
                BBoxPlayer player = (BBoxPlayer) e;
                if (!isMenu) {
                    if (player.isControllable()) {
                        checkPlayerInput(player);
                    } else {
                        //Player is AI controlled.
                    }
                    player.setDisplay(true);
                } else {
                    player.setDisplay(false);
                }
                checkPlayerCollisions(player);
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
                        if (item.isSelected() && mouseClick()) {
                            checkMenuItem(menu, item);
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

    private void checkPlayerCollisions(BBoxPlayer player) {
        for (UIEntity collision : player.getCollisions()) {
            if (collision instanceof Laser && !((Laser)collision).getOwner().equals(player)) {
                player.setDestroy(true);
            }
        }
        player.getCollisions().clear();
    }

    private void checkMenuItem(Menu menu, MenuItem item) {
        String itemText = item.getItemText();
        switch (itemText) {
            case "Play Game":
                Logger.info("Starting Game.");
                isMenu = false;
                menu.setDisplay(false);
                contentPane.setBackground(Color.WHITE);
                break;
            case "Options":
                Logger.info("In Options.");
                menu.setDisplay(false);
                break;
            case "Controls":
                Logger.info("In Controls.");
                menu.setDisplay(false);
                break;
            case "Exit Game":
                Logger.info("Exiting Game.");
                System.exit(1);
                break;
        }
    }

    private void checkPlayerInput(BBoxPlayer player) {

        for (Integer i : contentPane.getKeyCodes()) {
            Logger.info("Key: " + i);
        }
        //Movement
        if (contentPane.getKeyCodes().contains(KeyEvent.VK_W) &&
                        contentPane.getKeyCodes().contains(KeyEvent.VK_D)) {

            physicsComponent.performAction(new MoveFocusedAction(player, contentPane.getCamera(),
                    contentPane.getGuiElements(), player.getHspeed() / 2, -(player.getVspeed() / 2)));
            player.setDirection(Direction.UP_RIGHT);

        }
        else if (contentPane.getKeyCodes().contains(KeyEvent.VK_W) &&
                        contentPane.getKeyCodes().contains(KeyEvent.VK_A)) {

            physicsComponent.performAction(new MoveFocusedAction(player, contentPane.getCamera(),
                    contentPane.getGuiElements(), -(player.getHspeed() / 2), -(player.getVspeed() / 2)));
            player.setDirection(Direction.UP_LEFT);

        }
        else if (contentPane.getKeyCodes().contains(KeyEvent.VK_S) &&
                        contentPane.getKeyCodes().contains(KeyEvent.VK_D)) {

            physicsComponent.performAction(new MoveFocusedAction(player, contentPane.getCamera(),
                    contentPane.getGuiElements(), player.getHspeed() / 2, player.getVspeed() / 2));
            player.setDirection(Direction.DOWN_RIGHT);

        }
        else if (contentPane.getKeyCodes().contains(KeyEvent.VK_S) &&
                        contentPane.getKeyCodes().contains(KeyEvent.VK_A)) {

            physicsComponent.performAction(new MoveFocusedAction(player, contentPane.getCamera(),
                    contentPane.getGuiElements(), -(player.getHspeed() / 2), player.getVspeed() / 2));
            player.setDirection(Direction.DOWN_LEFT);

        }
        else if (contentPane.getKeyCodes().contains(KeyEvent.VK_W)) {

            physicsComponent.performAction(new MoveFocusedAction(player, contentPane.getCamera(),
                    contentPane.getGuiElements(), 0, -(player.getVspeed())));
            player.setDirection(Direction.UP);

        }
        else if (contentPane.getKeyCodes().contains(KeyEvent.VK_D)) {

            physicsComponent.performAction(new MoveFocusedAction(player, contentPane.getCamera(),
                    contentPane.getGuiElements(), player.getHspeed(), 0));
            player.setDirection(Direction.RIGHT);

        }
        else if (contentPane.getKeyCodes().contains(KeyEvent.VK_S)) {

            physicsComponent.performAction(new MoveFocusedAction(player, contentPane.getCamera(),
                    contentPane.getGuiElements(), 0, player.getVspeed()));
            player.setDirection(Direction.DOWN);

        }
        else if (contentPane.getKeyCodes().contains(KeyEvent.VK_A)) {

            physicsComponent.performAction(new MoveFocusedAction(player, contentPane.getCamera(),
                    contentPane.getGuiElements(), -(player.getHspeed()), 0));
            player.setDirection(Direction.LEFT);

        }

        //Shooting
        double shootDir = -1;
        if (!mouseClick()) {
            if (contentPane.getKeyCodes().contains(KeyEvent.VK_UP) &&
                    contentPane.getKeyCodes().contains(KeyEvent.VK_RIGHT)) {

                shootDir = Direction.UP_RIGHT;

            }
            else if (contentPane.getKeyCodes().contains(KeyEvent.VK_UP) &&
                    contentPane.getKeyCodes().contains(KeyEvent.VK_LEFT)) {

                shootDir = Direction.UP_LEFT;

            }
            else if (contentPane.getKeyCodes().contains(KeyEvent.VK_DOWN) &&
                    contentPane.getKeyCodes().contains(KeyEvent.VK_RIGHT)) {

                shootDir = Direction.DOWN_RIGHT;

            }
            else if (contentPane.getKeyCodes().contains(KeyEvent.VK_DOWN) &&
                    contentPane.getKeyCodes().contains(KeyEvent.VK_LEFT)) {

                shootDir = Direction.DOWN_LEFT;

            }
            else if (contentPane.getKeyCodes().contains(KeyEvent.VK_UP)) {

                shootDir = Direction.UP;

            }
            else if (contentPane.getKeyCodes().contains(KeyEvent.VK_RIGHT)) {

                shootDir = Direction.RIGHT;

            }
            else if (contentPane.getKeyCodes().contains(KeyEvent.VK_DOWN)) {

                shootDir = Direction.DOWN;

            }
            else if (contentPane.getKeyCodes().contains(KeyEvent.VK_LEFT)) {

                shootDir = Direction.LEFT;

            }
        } else {
            shootDir = UtilFunctions.getAngle(
                    new Point2D.Double(contentPane.getMouseX(), contentPane.getMouseY()),
                    new Point2D.Double(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2));

        }

        if (player.getStepsSinceShot() < (STEPS_PER_SEC / player.getFireRate())) {
            player.setStepsSinceShot(player.getStepsSinceShot() + 1);
        }
        if (shootDir > -1 && player.getStepsSinceShot() > (STEPS_PER_SEC / player.getFireRate()) - 1) {
            controlComponent.performAction(new ShootAction(player, contentPane, shootDir));
            player.setStepsSinceShot(0);
        }
    }

    private boolean mouseClick() {
        return (contentPane.getMouseClickX() > -1 && contentPane.getMouseClickY() > -1);
    }



}

