package com.company;

import java.awt.*;

/**
 * Created by Павел on 08.01.2016.
 */
public class Rect {
    protected int x1, y1, x2, y2;

    public Rect(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public void paint(Graphics g) {
//        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.darkGray);
        g.fillRect(x1, y1, x2 - x1, y2 - y1);
    }

    public boolean isInside(int x, int y) {
        return (x1 - 50 < x) && (x < x2 + 50) && (y1 - 50 < y) && (y < y2 + 50);
    }
}
