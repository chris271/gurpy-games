package com.gurpy.games.pojos.action;

import com.gurpy.games.pojos.entities.BBoxElement;
import com.gurpy.games.pojos.entities.TextElement;
import com.gurpy.games.pojos.entities.UIEntity;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class DrawAction extends UIAction {

    private Graphics2D g2d;

    public DrawAction(UIEntity owner, Graphics2D g2d) {
        super(owner);
        this.g2d = g2d;
    }

    @Override
    public boolean perform() {
        if (getOwner() instanceof BBoxElement) {
            BBoxElement boxElem = (BBoxElement)getOwner();
            //Draw border as a filled rectangle.
            g2d.setColor(boxElem.getBorderColor());
            g2d.fill(new Rectangle2D.Double(
                    boxElem.getX(),
                    boxElem.getY(),
                    boxElem.getWidth(),
                    boxElem.getHeight()));
            //Draw background as a filled rectangle inside of the border rectangle.
            g2d.setColor(boxElem.getBgColor());
            g2d.fill(new Rectangle2D.Double(
                    boxElem.getX() + boxElem.getBorderThickness(),
                    boxElem.getY() + boxElem.getBorderThickness(),
                    boxElem.getWidth() - 2 * boxElem.getBorderThickness(),
                    boxElem.getHeight() - 2 * boxElem.getBorderThickness()));
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
        } else {
            return false;
        }
    }
}
