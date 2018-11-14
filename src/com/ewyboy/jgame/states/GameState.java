package com.ewyboy.jgame.states;

import com.ewyboy.jgame.Handler;
import com.ewyboy.jgame.world.World;

import java.awt.*;

public class GameState extends State {

    private World world;

    public GameState(Handler handler) {
        super(handler);
        world = new World(handler,"resources/worlds/world.txt");
        handler.setWorld(world);
    }

    @Override
    public void tick() {
        world.tick();
    }

    @Override
    public void render(Graphics graphics) {
        world.render(graphics);
    }
}
