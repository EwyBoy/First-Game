package com.ewyboy.jgame.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage sheet;

    public SpriteSheet(BufferedImage sheet) {
        this.sheet = sheet;
    }

    public BufferedImage crop(int x, int y, int width, int height) {
        return sheet.getSubimage(x, y, width, height);
    }

    public BufferedImage getSprite(int x, int y) {
        return sheet.getSubimage((16 * x) - 16, (16 * y) - 16, 16, 16);
    }

}
