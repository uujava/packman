package com.company;

import java.awt.*;

/**
 * Created by Павел on 09.01.2016.
 */
class Coin {
    int x, y;

    public Coin(int a, int b) {
        this.x = a;
        this.y = b;
    }

    public void paint(Graphics g) {
        g.setColor(Color.orange);
        g.fillOval(x - 10, y - 10, 30, 30);
    }

    public void clear(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x - 10, y - 10, 30, 30);
    }
}
