package com.ewyboy.jgame;

import com.ewyboy.jgame.handlers.WindowHandler;
import javafx.stage.Screen;

import javax.swing.*;
import java.awt.*;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = -2284879212465893870L;

    public static final int WIDTH = 640;
    public static final int HEIGHT = (WIDTH / 16) * 9;
    public static final Dimension DIMENSION = new Dimension();
    public static final String NAME = "Test Java Game";
    public static Game game;

    public WindowHandler windowHandler;

    public JFrame frame;
    private Thread thread;

    public boolean running = false;

    private Screen screen;
    public boolean isApplet = false;

    public Game() {
        super();
    }

    public synchronized void start() {
        thread = new Thread();
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        System.out.println("Run");
        long lastTime = System.nanoTime();
        double amountOfTick = 60.0D;
        double ns = 1000000000 / amountOfTick;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while (running) {
            long now = System.nanoTime();
            delta += ((now - lastTime));
            lastTime = now;

            while (delta >= 1) {
                tick();
                delta--;
            }

            if (running) {
                render();
            }

            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println(frames + " FPS");
                frames = 0;
            }
        }
        stop();
    }

    private void render() {}
    private void tick() {}

}
