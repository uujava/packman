package com.company;

import java.awt.*;

/**
 * Created by Павел on 31.12.2015.
 */
public class Packman {
    static final int wP = 70;
    static final int wD = 100;
    int SPEED = 5;

    protected int direction = Direction.UP;
    protected int _direction = Direction.UP;

    protected TurnModel model;

    protected int x;
    protected int y;
    protected int _y;
    protected int _x;

    private boolean open;
    protected int openUp;
    protected int openAngle;

    public void reset(){
        _x = x = wD / 2;
        _y = y = wD / 2;
        open = true;
        openUp = 0;
        openAngle = 360;
    }

    public Packman(TurnModel model) {
        this.model = model;
    }

    public void paint(Graphics g) {
//        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.white);
        g.fillOval(x-35, y-35, wP, wP);
        g.setColor(Color.blue);
        g.fillArc(_x-35, _y-35, wP, wP, openUp, openAngle);
    }

    void turn(int direction) {
        this._direction = direction;
    }

    public void go() {
        if(goTo(_direction)) {
            direction = _direction;
        } else {
            goTo(direction);
        };

        calculateOpen();

        eat();

    }

    protected void eat() {
        model.eat(x, y);
    }

    private boolean goTo(int d) {
        boolean canGo = false;
        switch (d) {
            case Direction.DOWN:
                _y += SPEED;
                break;
            case Direction.UP:
                _y -= SPEED;
                break;
            case Direction.LEFT:
                _x -= SPEED;
                break;
            case Direction.RIGHT:
                _x += SPEED;
                break;
        }
        if (model.canTurn(_x, _y)) {
            //   System.out.println("can turn = " + this.direction + " x = " + x + " y = "+y + " =>" + direction);
            canGo = true;
            x = _x;
            y = _y;
        } else {
            // System.out.println("direction = " + direction + " x = " + x + ":" + _x + " y = "+y + ":" + _y);
            _x = x;
            _y = y;
        }

        return canGo;
    }

    private void calculateOpen() {
        if (openAngle == 280) {
            open = false;
        }
        if (openAngle == 360) {
            open = true;
        }

        if (open) {
            openAngle -= 5;
        } else {
            openAngle += 5;
        }
        if (direction == Direction.RIGHT) {
            openUp = (360 - openAngle) / 2;
        }
        if (direction == Direction.LEFT) {
            openUp = 180 + (360 - openAngle) / 2;
        }
        if (direction == Direction.DOWN) {
            openUp = 270 + (360 - openAngle) / 2;
        }
        if (direction == Direction.UP) {
            openUp = 90 + (360 - openAngle) / 2;
        }
    }

}

