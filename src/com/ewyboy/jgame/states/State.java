package com.ewyboy.jgame.states;

import com.ewyboy.jgame.Handler;

import java.awt.*;

public abstract class State {

    private static State currentState = null;

    public static State getState() {
        return currentState;
    }

    public static void setState(State state) {
        currentState = state;
    }

    protected Handler handler;

    public State(Handler handler) {
        this.handler = handler;
    }

    public abstract void tick();

    public abstract void render(Graphics graphics);

}
