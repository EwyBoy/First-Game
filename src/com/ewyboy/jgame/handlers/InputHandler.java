package com.ewyboy.jgame.handlers;

import com.ewyboy.jgame.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {

    public InputHandler(Game game) {
        game.addKeyListener(this);
    }

    public class Key {
        private int numTimesPressed = 0;
        private boolean pressed = false;

        public int getNumTimesPressed() {
            return numTimesPressed;
        }

        public boolean isPressed() {
            return pressed;
        }

        public void toggle(boolean isPressed) {
            pressed = isPressed;
            if (isPressed) numTimesPressed++;
        }
    }

    public Key up = new Key();
    public Key down = new Key();
    public Key left = new Key();
    public Key right = new Key();

    public Key sprint = new Key();

    public void keyPressed(KeyEvent e) {
        toggleKey(e.getKeyCode(), true);
    }

    public void keyReleased(KeyEvent e) {
        toggleKey(e.getKeyCode(), false);
    }

    public void keyTyped(KeyEvent e) {}

    public void toggleKey(int keyCode, boolean isPressed) {
        switch (keyCode) {
            case KeyEvent.VK_W : up.toggle(isPressed);
                break;
            case KeyEvent.VK_S : down.toggle(isPressed);
                break;
            case KeyEvent.VK_A : left.toggle(isPressed);
                break;
            case KeyEvent.VK_D : right.toggle(isPressed);
                break;
            case KeyEvent.VK_SHIFT : sprint.toggle(isPressed);
                break;
        }
    }
}
