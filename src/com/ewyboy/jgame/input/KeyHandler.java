package com.ewyboy.jgame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean [] keys, justPressed, cantPress;
    public boolean up, down, left, right;

    public KeyHandler() {
        keys = new boolean[256];
        justPressed = new boolean[keys.length];
        cantPress = new boolean[keys.length];
    }

    public void tick() {
        for (int i = 0; i < keys.length; i++) {
            if (cantPress[i] && !keys[i]) {
                cantPress[i] = false;
            } else if (justPressed[i]) {
                cantPress[i] = true;
                justPressed[i] = false;
            }
            if (!cantPress[i] && keys[i]) {
                justPressed[i] = true;
            }
        }

        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
    }

    public boolean keyJustPressed(int keyCode) {
        if (keyCode < 0 || keyCode >= keys.length) {
            return false;
        }
        return justPressed[keyCode];
    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() < 0 || event.getKeyCode() >= keys.length) {
            return;
        }
        keys[event.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent event) {
        if (event.getKeyCode() < 0 || event.getKeyCode() >= keys.length) {
            return;
        }
        keys[event.getKeyCode()] = true;
    }

    @Override
    public void keyTyped(KeyEvent event) {
       System.out.println(event.getKeyChar());
    }

}
