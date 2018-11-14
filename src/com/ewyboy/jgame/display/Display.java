package com.ewyboy.jgame.display;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Display {

    private JFrame frame;
    private Canvas canvas;

    private int width, height;
    private String name;

    public Display(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;

        createDisplay();
    }

    private void createDisplay() {
        createDisplayFrame();
        createDisplayCanvas();
        mergeFrameWithCanvas();
    }

    private void mergeFrameWithCanvas() {
        System.out.println("mergeFrameWithCanvas()");
        frame.add(canvas);
        frame.pack();
    }

    private void createDisplayFrame() {
        frame = new JFrame(name);
        frame.setSize(JOptionPane.showConfirmDialog(this.getCanvas(), "Big?") == 0 ? new Dimension(width, height) : new Dimension(720, 540));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if (JOptionPane.showConfirmDialog(this.getCanvas(), "Fullscreen?") == 0) {
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setUndecorated(true);
        }
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void createDisplayCanvas() {
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
        canvas.setMaximumSize(new Dimension(frame.getWidth(), frame.getHeight()));
        canvas.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
        canvas.setFocusable(false);
    }

    public JFrame getFrame() {
        return frame;
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
