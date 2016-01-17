package com.company;

import java.awt.*;

/**
 * Created by Павел on 31.12.2015.
 */
public class Borders extends Rect {

    public Borders(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }

    @Override
    public void paint(Graphics g) {
        g.drawRect(x1, y1, x2 - x1, y2 - y1);
    }

    @Override
    public boolean isInside(int x, int y) {
        return !((x1 + 50 <= x) && (x <= x2 - 50) && (y1 + 50 <= y) && (y <= y2 - 50));
    }
}
