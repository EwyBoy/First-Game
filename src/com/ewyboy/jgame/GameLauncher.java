package com.ewyboy.jgame;

import com.ewyboy.jgame.handlers.WindowHandler;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;

public class GameLauncher extends Applet {

    private static Game game = new Game();
    public static final Boolean DEBUG = false;

    public void init() {
        setLayout(new BorderLayout());
        add(game, BorderLayout.CENTER);
        setMaximumSize(Game.DIMENSION);
        setMinimumSize(Game.DIMENSION);
        setPreferredSize(Game.DIMENSION);
        game.isApplet = true;
    }

    @Override
    public void start() {
        game.start();
    }

    @Override
    public void stop() {
        game.stop();
    }

    public static void main(String[] args) {
        game.setMinimumSize(Game.DIMENSION);
        game.setMaximumSize(Game.DIMENSION);
        game.setPreferredSize(Game.DIMENSION);

        game.frame = new JFrame(Game.NAME);

        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLayout(new BorderLayout());
        game.frame.add(game, BorderLayout.CENTER);
        game.frame.pack();

        game.frame.setResizable(true);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

        game.windowHandler = new WindowHandler(game);

        game.start();
    }

}
