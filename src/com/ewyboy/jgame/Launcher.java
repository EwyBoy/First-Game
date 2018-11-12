package com.ewyboy.jgame;

public class Launcher {

    public static final String name = "Test Game";
    public static final int WIDTH = 720;
    public static final int HEIGHT = (WIDTH / 16) * 9;

    public static void main(String[] args) {
        System.out.println("main()");
        Game game = new Game(name, WIDTH, HEIGHT);
        game.start();
    }

}
