package com.ewyboy.jgame;

import com.ewyboy.jgame.gfx.Camera;
import com.ewyboy.jgame.input.KeyHandler;
import com.ewyboy.jgame.input.MouseHandler;
import com.ewyboy.jgame.world.World;

public class Handler {

    private Game game;
    private World world;

    public Handler(Game game) {
        this.game = game;
    }

    public int getWidth() {
        return game.getWidth();
    }

    public int getHeight() {
        return game.getHeight();
    }

    public Game getGame() {
        return game;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Camera getCamera() {
        return game.getCamera();
    }

    public KeyHandler getKeyHandler() {
        return game.getKeyHandler();
    }

    public MouseHandler getMouseHandler() {
        return game.getMouseHandler();
    }

}
