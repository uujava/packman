package com.company;

import java.util.ArrayList;
import java.util.Iterator;

import static com.company.Direction.*;

/**
 * Created by Павел on 31.12.2015.
 */
public class TurnModel {

    private final PackmanSound sound;
    Rect[] rects = new Rect[]{
            new Rect(100, 100, 300, 300),
            new Rect(400, 100, 700, 300),
            new Rect(800, 100, 1000, 300),
            new Rect(1100, 100, 1400, 200),
            new Rect(1500, 100, 1800, 200),
            new Rect(1100, 300, 1200, 900),
            new Rect(1300, 300, 1800, 400),
            new Rect(100, 400, 500, 500),
            new Rect(600, 400, 800, 900),
            new Rect(900, 400, 1000, 500),
            new Rect(1300, 500, 1500, 600),
            new Rect(1600, 500, 1800, 600),
            new Rect(100, 600, 200, 900),
            new Rect(300, 600, 500, 700),
            new Rect(900, 600, 1000, 700),
            new Rect(1300, 700, 1500, 900),
            new Rect(1600, 700, 1800, 900),
            new Rect(300, 800, 500, 900),
            new Rect(900, 800, 1000, 900),
            new Borders(0,0,1900,1000)

    };

    ArrayList<Coin> coins = new ArrayList<>();

    public TurnModel(PackmanSound sound) {
        this.sound = sound;
    }

    public boolean canTurn(int _x, int _y) {
        for (int i = 0; i < rects.length; i++) {
            if (rects[i].isInside(_x, _y)){
                return false;
            }
        }
        return true;
    }

    public void reset(){
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 10; j++) {
                int x = 50 + i * 100;
                int y = 50 + j * 100;
                if (canTurn(x, y)) {
                    addCoin(new Coin(x, y));
                }
            }
        }
    }

    void addCoin(Coin c) {
        coins.add(c);
    }

    int coinsSize() {
        return coins.size();
    }

    public void eat(int x, int y) {
        for (Iterator<Coin> iterator = coins.iterator(); iterator.hasNext(); ) {
            Coin next = iterator.next();
            if (x == next.x && y == next.y) {
                iterator.remove();
                sound.collectCoin();
            }
        }
    }
}

