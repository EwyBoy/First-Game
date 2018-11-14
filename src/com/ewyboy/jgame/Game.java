package com.ewyboy.jgame;

import com.ewyboy.jgame.display.Display;
import com.ewyboy.jgame.gfx.AssetsLoader;
import com.ewyboy.jgame.gfx.Camera;
import com.ewyboy.jgame.input.KeyHandler;
import com.ewyboy.jgame.input.MouseHandler;
import com.ewyboy.jgame.loader.TileLoader;
import com.ewyboy.jgame.registry.Registry;
import com.ewyboy.jgame.states.GameState;
import com.ewyboy.jgame.states.State;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game implements Runnable {

    private Display display;
    private Thread thread;
    public boolean isRunning = false;

    private BufferStrategy bs;
    private Graphics graphics;

    //Input
    private KeyHandler keyHandler;
    private MouseHandler mouseHandler;

    private Camera camera;
    private Handler handler;
    private GameState gameState;

    private int height, width;
    public String name;


    public Game(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;

        keyHandler = new KeyHandler();
        mouseHandler = new MouseHandler();
    }

    private void init() {
        System.out.println("init()");
        display = new Display(name, width, height);
        display.getFrame().addKeyListener(keyHandler);
        display.getFrame().addMouseListener(mouseHandler);
        display.getFrame().addMouseMotionListener(mouseHandler);
        display.getFrame().addMouseWheelListener(mouseHandler);
        AssetsLoader.init();
        TileLoader.init(Registry.Tiles.class);
        handler = new Handler(this);
        camera = new Camera(handler, 0,0);
        gameState = new GameState(handler);
        State.setState(gameState);
    }

    /** Main Game loop **/
    @Override
    public void run() {
        System.out.println("run()");
        init();

        int fps = 60;

        double timePerTick = 1000000000D / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while(isRunning) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }


            if (timer >= 1000000000) {
                System.out.println("Ticks & Frames: " + ticks);
                ticks = 0;
                timer = 0;
            }

        }
        stop();
    }

    public static Random random = new Random();
    public static float getRandomFloatInRange(float min, float max) {
        return (random.nextFloat() * (max - min)) + min;
    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        graphics = bs.getDrawGraphics();
        graphics.clearRect(0,0, width, height);

      /*  for (int x = 0; x < width; x += 35) {
            for (int y = 0; y < height; y += 35) {
                graphics.setColor(Color.getHSBColor(getRandomFloatInRange(0, 1), getRandomFloatInRange(0, 1), getRandomFloatInRange(0, 1)));
                graphics.fill3DRect(x, y, 30, 30, true);
            }
        }*/

        if (State.getState() != null) State.getState().render(graphics);

        bs.show();
        graphics.dispose();
    }

    private void tick() {
        keyHandler.tick();
        mouseHandler.tick();

        if (State.getState() != null) State.getState().tick();
    }


    public synchronized void start() {
        System.out.println("start()");
        if (isRunning) {
            return;
        }
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        System.out.println("stop()");

        if (!isRunning) {
            return;
        }

        isRunning = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Camera getCamera() {
        return camera;
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

    public MouseHandler getMouseHandler() {
        return mouseHandler;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
