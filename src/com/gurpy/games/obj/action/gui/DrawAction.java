package com.gurpy.games.obj.action.gui;

import com.gurpy.games.obj.action.UIAction;
import com.gurpy.games.obj.entities.menu.Menu;
import com.gurpy.games.obj.entities.bbox.menu.BBoxMenuItem;
import com.gurpy.games.obj.entities.bbox.playable.BBoxPlayer;
import com.gurpy.games.obj.entities.line.weapon.Laser;
import com.gurpy.games.obj.entities.menu.MenuItem;
import com.gurpy.games.obj.entities.text.TextElement;
import com.gurpy.games.obj.entities.ui.UIElement;
import com.gurpy.games.obj.entities.ui.UIEntity;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class DrawAction extends UIAction {

    private Graphics2D g2d;

    public DrawAction(UIEntity owner, Graphics2D g2d) {
        super(owner);
        this.g2d = g2d;
    }

    @Override
    public boolean perform() {
        if (getOwner() instanceof BBoxPlayer) {
            BBoxPlayer player = (BBoxPlayer)getOwner();
            //Draw border as a filled rectangle.
            g2d.setColor(player.getBorderColor());
            g2d.fill(new Rectangle2D.Double(
                    player.getX(),
                    player.getY(),
                    player.getWidth(),
                    player.getHeight()));
            //Draw background as a filled rectangle inside of the border rectangle.
            g2d.setColor(player.getBgColor());
            g2d.fill(new Rectangle2D.Double(
                    player.getX() + player.getBorderThickness(),
                    player.getY() + player.getBorderThickness(),
                    player.getWidth() - 2 * player.getBorderThickness(),
                    player.getHeight() - 2 * player.getBorderThickness()));
            drawPlayerHealthbar(player, g2d);
            return true;
        } else if (getOwner() instanceof TextElement) {
            TextElement textElem = (TextElement)getOwner();
            Font tr = new Font("TimesRoman", Font.PLAIN, (int)textElem.getBorderThickness());
            g2d.setFont(tr);
            //Draw background as a filled rectangle inside of the border rectangle.
            FontMetrics fontMetrics = g2d.getFontMetrics();
            double textWidth = fontMetrics.stringWidth(textElem.getText());
            double textHeight = fontMetrics.getHeight();
            g2d.setColor(textElem.getBgColor());
            g2d.fill(new Rectangle2D.Double(
                    textElem.getX() - 2,
                    textElem.getY() - textHeight * .75,
                    textWidth + 4,
                    textHeight));
            //Draw text.
            g2d.setColor(textElem.getBorderColor());
            g2d.drawString(textElem.getText(),
                    (float)textElem.getX(),
                    (float)textElem.getY());
            return true;
        } else if (getOwner() instanceof Menu) {
            Menu menu = (Menu)getOwner();
            new DrawAction(menu.getTitle(), g2d).perform();
            for (MenuItem item : menu.getMenuItems()) {
                new DrawAction(item, g2d).perform();
            }
            return true;
        } else if (getOwner() instanceof BBoxMenuItem) {
            BBoxMenuItem item = (BBoxMenuItem)getOwner();
            if (item.isSelected()) {
                drawBBoxMenuItem(g2d, item, item.getBgColor(), item.getBorderColor());
            } else {
                drawBBoxMenuItem(g2d, item, item.getBorderColor(), item.getBgColor());
            }
            return true;
        } else if (getOwner() instanceof Laser) {
            Laser item = (Laser)getOwner();
            for (int i = 0; i < item.getLines().size(); i++) {
                if (i >= item.getLines().size() * .2 && i <= item.getLines().size() * .7) {
                    g2d.setColor(item.getBgColor());
                } else {
                    g2d.setColor(item.getBorderColor());
                }
                g2d.draw(item.getLines().get(i));
            }
            return true;
        } else {
            return false;
        }
    }

    private void drawPlayerHealthbar(BBoxPlayer player, Graphics2D g2d) {
        Rectangle2D.Double healthBar = player.getHealthBar();
        g2d.setColor(player.getHealthBarBorder());
        g2d.fill(healthBar);
        g2d.setColor(player.getHealthBarFillColor());
        Rectangle2D.Double fillRect = player.getHealthBarFill();
        g2d.fill(fillRect);
        fillRect.setRect(fillRect.getX(), fillRect.getY(),
                fillRect.getWidth() * (player.getHealth() / player.getMaxHealth()),
                fillRect.getHeight());
        g2d.setColor(player.getHealthBarColor());
        g2d.fill(fillRect);
        g2d.setColor(Color.BLACK);
        Font tr = new Font("TimesRoman", Font.PLAIN, 12);
        g2d.setFont(tr);
        FontMetrics fontMetrics = g2d.getFontMetrics();
        NumberFormat formatter = new DecimalFormat("#0.00");
        String healthString = formatter.format(player.getHealth()) + " / " + formatter.format(player.getMaxHealth());
        double textWidth = fontMetrics.stringWidth(healthString);
        double textHeight = fontMetrics.getHeight();
        g2d.drawString(healthString,
                (float)(healthBar.getX() + healthBar.getWidth() / 2 - textWidth / 2),
                (float)(healthBar.getY() + healthBar.getHeight() + textHeight + 2));

    }

    private void drawBBoxMenuItem(Graphics2D g2d, BBoxMenuItem item, Color borderColor, Color bgColor) {
        //Draw border as a filled rectangle.
        g2d.setColor(borderColor);
        g2d.fill(new RoundRectangle2D.Double(
                item.getX(),
                item.getY(),
                item.getWidth(),
                item.getHeight(),
                50,
                50));
        //Draw background as a filled rectangle inside of the border rectangle.
        g2d.setColor(bgColor);
        g2d.fill(new RoundRectangle2D.Double(
                item.getX() + item.getBorderThickness(),
                item.getY() + item.getBorderThickness(),
                item.getWidth() - 2 * item.getBorderThickness(),
                item.getHeight() - 2 * item.getBorderThickness(),
                50,
                50));
        Font tr = new Font("TimesRoman", Font.PLAIN, 20);
        g2d.setFont(tr);
        //Draw background as a filled rectangle inside of the border rectangle.
        FontMetrics fontMetrics = g2d.getFontMetrics();
        double textWidth = fontMetrics.stringWidth(item.getItemText());
        double textHeight = fontMetrics.getHeight();
        //Draw text.
        g2d.setColor(borderColor);
        g2d.drawString(item.getItemText(),
                (float)(item.getX() + item.getWidth() / 2 - textWidth / 2),
                (float)(item.getY() + item.getHeight() / 2 + textHeight / 4));
    }
}
