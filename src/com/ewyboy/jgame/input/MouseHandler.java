package com.ewyboy.jgame.input;

import java.awt.event.*;

public class MouseHandler implements MouseListener, MouseMotionListener, MouseWheelListener {

    private boolean leftClickPressed,rightClickPressed;
    private int mousePosX, mousePosY;

    public MouseHandler() {}

    public boolean isLeftClickPressed() {
        return leftClickPressed;
    }

    public boolean isRightClickPressed() {
        return rightClickPressed;
    }

    public int getMousePosX() {
        return mousePosX;
    }

    public int getMousePosY() {
        return mousePosY;
    }

    public void tick() {

    }

    public void mouseButtonAction(MouseEvent event, boolean isPressed) {
        switch (event.getButton()) {
            case MouseEvent.BUTTON1:
                leftClickPressed = isPressed;
                break;
            case MouseEvent.BUTTON3:
                rightClickPressed = isPressed;
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent event) {
        mouseButtonAction(event, true);
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        mouseButtonAction(event, false);
    }

    @Override
    public void mouseClicked(MouseEvent event) {}

    @Override
    public void mouseEntered(MouseEvent event) {

    }

    @Override
    public void mouseExited(MouseEvent event) {

    }

    @Override
    public void mouseDragged(MouseEvent event) {

    }

    @Override
    public void mouseMoved(MouseEvent event) {
        mousePosX = event.getX();
        mousePosY = event.getY();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent event) {

    }
}
