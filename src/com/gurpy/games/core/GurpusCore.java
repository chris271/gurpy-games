package com.gurpy.games.core;

import com.gurpy.games.gui.GurpusUI;
import com.gurpy.games.pojos.action.DestroyAction;
import com.gurpy.games.pojos.action.ShootAction;
import com.gurpy.games.pojos.action.TranslationAction;
import com.gurpy.games.pojos.component.ControlComponent;
import com.gurpy.games.pojos.component.PhysicsComponent;
import com.gurpy.games.pojos.control.Direction;
import com.gurpy.games.pojos.entities.*;
import com.gurpy.games.pojos.entities.Menu;
import com.gurpy.games.pojos.entities.MenuItem;
import com.gurpy.games.utils.Logger;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class GurpusCore implements Runnable{

    public static final double STEPS_PER_SEC = 100;
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

        final long STEP_TIME = 10 * NANO_IN_MILLI;
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
        for (Entity e : contentPane.getGuiElements()) {
            if (e instanceof Player) {
                Player player = (Player) e;
                if (!isMenu) {
                    checkPlayerMovement(player);
                    checkPlayerShoot(player);
                    ((Player)e).setDisplay(true);
                } else {
                    ((Player)e).setDisplay(false);
                }
            }
            if (e instanceof Laser) {
                Laser laser = (Laser) e;
                if (!isMenu) {
                    laser.setDisplay(true);
                    // 0 - UP
                    if (laser.getDirection() == 0) {
                        physicsComponent.performAction(new TranslationAction(laser,
                                new Point2D.Double(laser.getLines().get(0).getP1().getX(),
                                        laser.getLines().get(0).getP1().getY() - laser.getVspeed())));
                    }
                    // 1 - UP_RIGHT
                    else if (laser.getDirection() == 1) {
                        physicsComponent.performAction(new TranslationAction(laser,
                                new Point2D.Double(laser.getLines().get(0).getP1().getX() + laser.getHspeed() / 2,
                                        laser.getLines().get(0).getP1().getY() - laser.getVspeed() / 2)));
                    }
                    // 2 - RIGHT
                    else if (laser.getDirection() == 2) {
                        physicsComponent.performAction(new TranslationAction(laser,
                                new Point2D.Double(laser.getLines().get(0).getP1().getX() + laser.getHspeed(),
                                        laser.getLines().get(0).getP1().getY())));
                    }
                    // 3 - DOWN_RIGHT
                    else if (laser.getDirection() == 3) {
                        physicsComponent.performAction(new TranslationAction(laser,
                                new Point2D.Double(laser.getLines().get(0).getP1().getX() + laser.getHspeed() / 2,
                                        laser.getLines().get(0).getP1().getY() + laser.getVspeed() / 2)));
                    }
                    // 4 - DOWN
                    else if (laser.getDirection() == 4) {
                        physicsComponent.performAction(new TranslationAction(laser,
                                new Point2D.Double(laser.getLines().get(0).getP1().getX(),
                                        laser.getLines().get(0).getP1().getY() + laser.getVspeed())));
                    }
                    // 5 - DOWN_LEFT
                    else if (laser.getDirection() == 5) {
                        physicsComponent.performAction(new TranslationAction(laser,
                                new Point2D.Double(laser.getLines().get(0).getP1().getX() - laser.getHspeed() / 2,
                                        laser.getLines().get(0).getP1().getY() + laser.getVspeed() / 2)));
                    }
                    // 6 - LEFT
                    else if (laser.getDirection() == 6) {
                        physicsComponent.performAction(new TranslationAction(laser,
                                new Point2D.Double(laser.getLines().get(0).getP1().getX() - laser.getHspeed(),
                                        laser.getLines().get(0).getP1().getY())));
                    }
                    // 7 - UP_LEFT
                    else if (laser.getDirection() == 7) {
                        physicsComponent.performAction(new TranslationAction(laser,
                                new Point2D.Double(laser.getLines().get(0).getP1().getX() - laser.getHspeed() / 2,
                                        laser.getLines().get(0).getP1().getY() - laser.getVspeed() / 2)));
                    }
                    //Check if alive.
                    laser.setStepsAlive(laser.getStepsAlive() + 1);
                    if (laser.getOwner() instanceof Player &&
                            laser.getStepsAlive() > ((Player)laser.getOwner()).getRange() * STEPS_PER_SEC - 1) {
                        controlComponent.performAction(new DestroyAction((Laser) e, contentPane));
                    }
                } else {
                    laser.setDisplay(false);
                }
            }
            if (e instanceof TextElement) {
                TextElement textElement = (TextElement)e;
                if (textElement.getText().contains("FPS")) {
                    textElement.setText("FPS: " + contentPane.getFps());
                } else if (!isMenu) {
                    ((TextElement)e).setDisplay(true);
                } else {
                    ((TextElement)e).setDisplay(false);
                }
            }
            if (e instanceof Menu) {
                Menu menu = (Menu)e;
                if (menu.isDisplay()) {
                    for (MenuItem item : menu.getMenuItems()) {
                        Rectangle2D.Double itemRect = new Rectangle2D.Double(
                                item.getX(), item.getY(), item.getWidth(), item.getHeight());
                        if (itemRect.contains(contentPane.getMouseX(), contentPane.getMouseY())) {
                            item.setSelected(true);
                        } else {
                            item.setSelected(false);
                        }
                        if (item.isSelected() && contentPane.getMouseClickX() > -1 && contentPane.getMouseClickY() > -1) {
                            if (item.getItemText().equalsIgnoreCase("Play Game")) {
                                Logger.info("Starting Game.");
                                isMenu = false;
                                menu.setDisplay(false);
                                contentPane.setBackground(Color.WHITE);
                            } else if (item.getItemText().equalsIgnoreCase("Options")) {
                                Logger.info("In Options.");
                                menu.setDisplay(false);
                            } else if (item.getItemText().equalsIgnoreCase("Controls")) {
                                Logger.info("In Controls.");
                                menu.setDisplay(false);
                            } else if (item.getItemText().equalsIgnoreCase("Exit Game")) {
                                Logger.info("Exiting Game.");
                                System.exit(1);
                            }
                        }
                    }
                } else {
                    if (contentPane.getKeyCodes().contains(KeyEvent.VK_ESCAPE)) {
                        contentPane.setBackground(Color.BLACK);
                        isMenu = true;
                        ((Menu)e).setDisplay(true);
                    }
                }
            }
        }

        if (!contentPane.isShowing()) {
            Logger.error("Cannot find GUI!");
            System.exit(0);
        }
    }

    private void checkPlayerShoot(Player player) {
        if (player.getStepsSinceShot() < (STEPS_PER_SEC / player.getFireRate())) {
            player.setStepsSinceShot(player.getStepsSinceShot() + 1);
        }
        if (contentPane.getMouseClickX() > -1 && contentPane.getMouseClickY() > -1 &&
                player.getStepsSinceShot() > (STEPS_PER_SEC / player.getFireRate()) - 1) {
            controlComponent.performAction(new ShootAction(player, contentPane));
            Logger.info("Steps last second " + player.getStepsSinceShot());
            player.setStepsSinceShot(0);
            Logger.info("Shoot");
        }
    }

    private void checkPlayerMovement(Player player) {

        if ((contentPane.getKeyCodes().contains(KeyEvent.VK_UP) &&
                contentPane.getKeyCodes().contains(KeyEvent.VK_RIGHT)) ||
                (contentPane.getKeyCodes().contains(KeyEvent.VK_W) &&
                        contentPane.getKeyCodes().contains(KeyEvent.VK_D))) {

            physicsComponent.performAction(new TranslationAction(player,
                    new Point2D.Double(player.getX() + player.getHspeed() / 2, player.getY() - player.getVspeed() / 2)));
            player.setDirection(Direction.UP_RIGHT);

        }
        else if ((contentPane.getKeyCodes().contains(KeyEvent.VK_UP) &&
                contentPane.getKeyCodes().contains(KeyEvent.VK_LEFT)) ||
                (contentPane.getKeyCodes().contains(KeyEvent.VK_W) &&
                        contentPane.getKeyCodes().contains(KeyEvent.VK_A))) {

            physicsComponent.performAction(new TranslationAction(player,
                    new Point2D.Double(player.getX() - player.getHspeed() / 2, player.getY() - player.getVspeed() / 2)));
            player.setDirection(Direction.UP_LEFT);

        }
        else if ((contentPane.getKeyCodes().contains(KeyEvent.VK_DOWN) &&
                contentPane.getKeyCodes().contains(KeyEvent.VK_RIGHT)) ||
                (contentPane.getKeyCodes().contains(KeyEvent.VK_S) &&
                        contentPane.getKeyCodes().contains(KeyEvent.VK_D))) {

            physicsComponent.performAction(new TranslationAction(player,
                    new Point2D.Double(player.getX() + player.getHspeed() / 2, player.getY() + player.getVspeed() / 2)));
            player.setDirection(Direction.DOWN_RIGHT);

        }
        else if ((contentPane.getKeyCodes().contains(KeyEvent.VK_DOWN) &&
                contentPane.getKeyCodes().contains(KeyEvent.VK_LEFT)) ||
                (contentPane.getKeyCodes().contains(KeyEvent.VK_S) &&
                        contentPane.getKeyCodes().contains(KeyEvent.VK_A))) {

            physicsComponent.performAction(new TranslationAction(player,
                    new Point2D.Double(player.getX() - player.getHspeed() / 2, player.getY() + player.getVspeed() / 2)));
            player.setDirection(Direction.DOWN_LEFT);

        }
        else if (contentPane.getKeyCodes().contains(KeyEvent.VK_UP)||
                contentPane.getKeyCodes().contains(KeyEvent.VK_W)) {

            physicsComponent.performAction(new TranslationAction(player,
                    new Point2D.Double(player.getX(), player.getY() - player.getVspeed())));
            player.setDirection(Direction.UP);

        }
        else if (contentPane.getKeyCodes().contains(KeyEvent.VK_RIGHT)||
                contentPane.getKeyCodes().contains(KeyEvent.VK_D)) {

            physicsComponent.performAction(new TranslationAction(player,
                    new Point2D.Double(player.getX() + player.getHspeed(), player.getY())));
            player.setDirection(Direction.RIGHT);

        }
        else if (contentPane.getKeyCodes().contains(KeyEvent.VK_DOWN)||
                contentPane.getKeyCodes().contains(KeyEvent.VK_S)) {

            physicsComponent.performAction(new TranslationAction(player,
                    new Point2D.Double(player.getX(), player.getY() + player.getVspeed())));
            player.setDirection(Direction.DOWN);

        }
        else if (contentPane.getKeyCodes().contains(KeyEvent.VK_LEFT)||
                contentPane.getKeyCodes().contains(KeyEvent.VK_A)) {

            physicsComponent.performAction(new TranslationAction(player,
                    new Point2D.Double(player.getX() - player.getHspeed(), player.getY())));
            player.setDirection(Direction.LEFT);

        }
    }

}

