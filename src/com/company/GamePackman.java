package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Павел on 31.12.2015.
 */
public class GamePackman extends JFrame {
    PacmanPanel panel;

    public static void main(String[] args) {
        GamePackman game = new GamePackman();
        game.launcher();
    }

    public void launcher() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final PackmanSound sound = new PackmanSound();
        final TurnModel model = new TurnModel(sound);

        final Packman packman = new Packman(model);

        final PackmanHunt pickman = new PackmanHunt(model, packman);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                        packman.turn(Direction.UP);
                        break;
                    case KeyEvent.VK_S:
                        packman.turn(Direction.DOWN);
                        break;
                    case KeyEvent.VK_A:
                        packman.turn(Direction.LEFT);
                        break;
                    case KeyEvent.VK_D:
                        packman.turn(Direction.RIGHT);
                        break;
                }
            }
        });

        panel = new PacmanPanel(packman, pickman, sound, model);

        packman.reset();

        pickman.reset();

        model.reset();

        getContentPane().add(panel);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                panel.repaint();
            }
        });

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    runGame();
                    String message;
                    if (model.coinsSize() == 0) {
                        sound.winnerSong();
                        message = "You win!";
                    } else {
                        sound.huntEatPackman();
                        message = "You loose!";
                    }
                    int selected = JOptionPane.showConfirmDialog(panel, "Continue?", message, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    if (selected != JOptionPane.OK_OPTION) {
                        System.exit(0);
                    } else {
                        packman.reset();

                        pickman.reset();

                        model.reset();
                    }
                }
            }

            private void runGame() {
                boolean gameOver = false;
                while (!gameOver) {

                    packman.go();
                    pickman.go();
                    if ((packman.x == pickman.x) && (packman.y == pickman.y) || model.coinsSize() == 0) {
                        gameOver = true;
                    }

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            panel.repaint();
                        }
                    });
                    try {
                        sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        setSize(1920, 1080);
        setFocusable(true);
        setVisible(true);

        Player.main(new String[]{});
    }

    class PacmanPanel extends JPanel {
        private final PackmanSound sound;
        private final TurnModel model;

        ArrayList<Rect> rects = new ArrayList<>();
        Packman packman;
        PackmanHunt pickman;

        public PacmanPanel(Packman packman, PackmanHunt pickman, PackmanSound sound, TurnModel model) {

            this.packman = packman;
            this.pickman = pickman;
            this.sound = sound;
            this.model = model;

            for (int i = 0; i < model.rects.length; i++) {
                Rect rect = model.rects[i];
                addRect(rect);
            }

        }

        @Override
        protected void paintComponent(Graphics g) {
            g.setColor(Color.white);
            g.fillRect(0, 0, 1900, 1000);
            for (Iterator<Rect> iterator = rects.iterator(); iterator.hasNext(); ) {
                Rect next = iterator.next();
                next.paint(g);
            }
            for (Iterator<Coin> iterator = model.coins.iterator(); iterator.hasNext(); ) {
                Coin next = iterator.next();
                next.paint(g);
            }
            packman.paint(g);
            pickman.paint(g);
        }

        void addRect(Rect r) {
            rects.add(r);
        }

    }

}
