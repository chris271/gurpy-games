package com.gurpy.games.pojos.action.gui;

import com.gurpy.games.pojos.action.UIAction;
import com.gurpy.games.pojos.entities.*;
import com.gurpy.games.pojos.entities.Menu;
import com.gurpy.games.pojos.entities.MenuItem;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class DrawAction extends UIAction {

    private Graphics2D g2d;

    public DrawAction(UIElement owner, Graphics2D g2d) {
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
        } else if (getOwner() instanceof MenuItem) {
            MenuItem item = (MenuItem)getOwner();
            if (item.isSelected()) {
                drawMenuItem(g2d, item, item.getBgColor(), item.getBorderColor());
            } else {
                drawMenuItem(g2d, item, item.getBorderColor(), item.getBgColor());
            }
            return true;
        } else if (getOwner() instanceof Laser) {
            Laser item = (Laser)getOwner();
            for (int i = 0; i < item.getLines().size(); i++) {
                if (i != 0 && i != item.getLines().size() - 1) {
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

    private void drawMenuItem(Graphics2D g2d, MenuItem item, Color borderColor, Color bgColor) {
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
