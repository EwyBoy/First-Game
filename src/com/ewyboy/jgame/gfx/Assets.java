package com.ewyboy.jgame.gfx;

import java.awt.image.BufferedImage;

public class Assets {

    private static final int width = 16, height = 16;

    public static BufferedImage grass, sand, rock, water;

    public static void init() {
        System.out.println("Assets Init Started");
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/sprite/test.png"));

        grass = sheet.crop(0,0, width, height);
        sand = sheet.crop(width,0, width, height);
        rock = sheet.crop(width * 2,0, width, height);
        water = sheet.crop(width * 3,0, width, height);

        System.out.println("Assets Init Done");
    }

}
