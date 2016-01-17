package com.company;

import javax.sound.midi.*;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Павел on 23.12.2015.
 */
public class PackmanSound {

    private MidiChannel[] channels;
    private Synthesizer player;
    private final Queue<String> queue;

    public static void main(String[] args) {
        PackmanSound mini = new PackmanSound();
        for (int i = 0; i < 5; i++) {
            mini.collectCoin();
            mini.musicPackmanGo();
        }
        mini.huntEatPackman();
        mini.winnerSong();
        sleep();
        mini.close();
    }

    private static void sleep() {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        player.close();
        queue.add("stop");
    }

    public PackmanSound() {
        try {
            player = MidiSystem.getSynthesizer();
            player.open();
            channels = player.getChannels();
            channels[0].programChange(55);
            channels[1].programChange(125);
            channels[2].programChange(98);
            channels[3].programChange(105);
        } catch (Exception e) {
            e.printStackTrace();
        }

        queue = new ArrayBlockingQueue<String>(100);
        new Thread() {
            @Override
            public void run() {
                String command;
                while ((command  = queue.poll()) != "stop") {
                    if (command == "coin") {
                        playSound(0, 50, 30, 80);
                    }
                    if (command == "go") {
                        playSound(1, 500, 280, 80);
                    }
                    if (command == "eat") {
                        playSound(2, 3000, 80, 80);
                    }
                    if (command == "winner") {
                        playSound(3, 3000, 80, 80);
                    }
                }
            }
        }.start();
    }


    public void collectCoin() {
       queue.add("coin");
    }

    public void musicPackmanGo() {
        queue.add("go");
    }

    public void huntEatPackman() {
        queue.add("eat");
    }

    public void winnerSong() {
        queue.add("winner");

    }

    public void playSound(int channel, int duration, int volume, int... notes) {
        for (int note : notes) {
            channels[channel].noteOn(note, volume);
        }
        try {
            Thread.sleep(duration);
        } catch (InterruptedException ex) {
        }
        for (int note : notes) {
            channels[channel].noteOff(note);
        }
    }
}




