package com.company;

import java.awt.*;

/**
 * Created by Павел on 06.01.2016.
 */
public class PackmanHunt extends Packman {

    private final Packman packman;
    int speedcount;

    public PackmanHunt(TurnModel model, Packman packman) {
        super(model);
        this.packman = packman;
    }

    public void paint(Graphics g) {
//        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.white);
        g.fillOval(x-35, y-35, wP, wP);
        g.setColor(Color.red);
        g.fillArc(_x-35, _y-35, wP, wP, openUp, openAngle);
    }

    public void go() {
        int x2 = packman._x;
        int y2 = packman._y;
        if (Math.abs(x2 - _x) > Math.abs(y2 - _y)) {
            if (x2 > _x) {
                _direction = Direction.RIGHT;
            } else {
                _direction = Direction.LEFT;
            }
        } else {
            if (y2 > _y) {
                _direction = Direction.DOWN;
            } else {
                _direction = Direction.UP;
            }
        }
        speedcount++;
        if (speedcount % 30 == 0) {
            SPEED = 10;
        } else {
            SPEED = 5;
        }
        super.go();
    }

    public void reset(){
        super.reset();
        this.x = 1850;
        this.y = 950;
        this._x = this.x;
        this._y = this.y;
    }

    @Override
    protected void eat() {
    }
}
