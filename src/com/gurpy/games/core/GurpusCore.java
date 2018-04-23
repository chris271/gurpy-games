package com.gurpy.games.core;

import com.gurpy.games.gui.GurpusUI;
import com.gurpy.games.pojos.action.CollisionAction;
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
            //Check collisions
            for (UIEntity other : contentPane.getGuiElements()) {
                controlComponent.performAction(new CollisionAction((UIEntity)e, other));
            }
            //Check input and update physics.
            if (e instanceof BBoxPlayer) {
                BBoxPlayer player = (BBoxPlayer) e;
                if (!isMenu) {
                    checkPlayerInput(player);
                    player.setDisplay(true);
                } else {
                    player.setDisplay(false);
                }
                for (UIEntity collision : player.getCollisions()) {
                    if (collision instanceof Laser) {
                        player.setDisplay(false);
                    }
                }
                player.getCollisions().clear();
            }
            if (e instanceof Laser) {
                Laser laser = (Laser) e;
                if (!isMenu) {
                    laser.setDisplay(true);
                    updateLaserMovement(laser);
                    updateLaserState(laser);
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
                        if (new Rectangle2D.Double(item.getX(), item.getY(), item.getWidth(), item.getHeight())
                                .contains(contentPane.getMouseX(), contentPane.getMouseY())) {
                            item.setSelected(true);
                        } else {
                            item.setSelected(false);
                        }
                        if (item.isSelected() && selectInput()) {
                            checkMenuItem(menu, item);
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

    private void updateLaserState(Laser laser) {
        laser.setStepsAlive(laser.getStepsAlive() + 1);
        if (laser.getOwner() instanceof BBoxPlayer &&
                laser.getStepsAlive() > ((BBoxPlayer)laser.getOwner()).getRange() * STEPS_PER_SEC - 1) {
            controlComponent.performAction(new DestroyAction(laser, contentPane));
        }
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

    private void updateLaserMovement(Laser laser) {
        int laserDir = laser.getDirection();
        TranslationAction translationAction = new TranslationAction(laser, new Point2D.Double(0, 0));
        switch (laserDir) {
            case Direction.UP:
                translationAction.setPosition(new Point2D.Double(laser.getLines().get(0).getP1().getX(),
                        laser.getLines().get(0).getP1().getY() - laser.getVspeed()));
                break;
            case Direction.UP_RIGHT:
                translationAction.setPosition(new Point2D.Double(laser.getLines().get(0).getP1().getX() + laser.getHspeed() / 2,
                        laser.getLines().get(0).getP1().getY() - laser.getVspeed() / 2));
                break;
            case Direction.RIGHT:
                translationAction.setPosition(new Point2D.Double(laser.getLines().get(0).getP1().getX() + laser.getHspeed(),
                        laser.getLines().get(0).getP1().getY()));
                break;
            case Direction.DOWN_RIGHT:
                translationAction.setPosition(new Point2D.Double(laser.getLines().get(0).getP1().getX() + laser.getHspeed() / 2,
                        laser.getLines().get(0).getP1().getY() + laser.getVspeed() / 2));
                break;
            case Direction.DOWN:
                translationAction.setPosition(new Point2D.Double(laser.getLines().get(0).getP1().getX(),
                        laser.getLines().get(0).getP1().getY() + laser.getVspeed()));
                break;
            case Direction.DOWN_LEFT:
                translationAction.setPosition(new Point2D.Double(laser.getLines().get(0).getP1().getX() - laser.getHspeed() / 2,
                        laser.getLines().get(0).getP1().getY() + laser.getVspeed() / 2));
                break;
            case Direction.LEFT:
                translationAction.setPosition(new Point2D.Double(laser.getLines().get(0).getP1().getX() - laser.getHspeed(),
                        laser.getLines().get(0).getP1().getY()));
                break;
            case Direction.UP_LEFT:
                translationAction.setPosition(new Point2D.Double(laser.getLines().get(0).getP1().getX() - laser.getHspeed() / 2,
                        laser.getLines().get(0).getP1().getY() - laser.getVspeed() / 2));
                break;
            default:
                translationAction.setPosition(new Point2D.Double(laser.getLines().get(0).getP1().getX(),
                        laser.getLines().get(0).getP1().getY() - laser.getVspeed()));
                break;
        }
        physicsComponent.performAction(translationAction);
    }

    private void checkPlayerInput(BBoxPlayer player) {

        //Movement
        if (contentPane.getKeyCodes().size() > 2) {

            player.setDirection(Direction.UP);

        } else if ((contentPane.getKeyCodes().contains(KeyEvent.VK_UP) &&
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
        //Shooting
        if (player.getStepsSinceShot() < (STEPS_PER_SEC / player.getFireRate())) {
            player.setStepsSinceShot(player.getStepsSinceShot() + 1);
        }
        if (selectInput() && player.getStepsSinceShot() > (STEPS_PER_SEC / player.getFireRate()) - 1) {
            controlComponent.performAction(new ShootAction(player, contentPane));
            player.setStepsSinceShot(0);
        }
    }

    private boolean selectInput() {
        return (contentPane.getMouseClickX() > -1 && contentPane.getMouseClickY() > -1) ||
                contentPane.getKeyCodes().contains(KeyEvent.VK_ENTER) ||
                contentPane.getKeyCodes().contains(KeyEvent.VK_SPACE);
    }

}

