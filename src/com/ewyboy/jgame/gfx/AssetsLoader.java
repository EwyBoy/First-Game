package com.ewyboy.jgame.gfx;

import java.awt.image.BufferedImage;

public class AssetsLoader {

    public static BufferedImage player_idle_forward, player_idle_backward;
    public static BufferedImage grass, dirt ,sand, rock, water, lava;
    public static BufferedImage[] player_forward, player_backward, player_left, player_right;
    public static BufferedImage player_swimming;

    public static void init() {
        System.out.println("AssetsLoader Init Started");
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/sprite/test.png"));

        grass = sheet.getSprite(1,1);
        sand = sheet.getSprite(2,1);
        rock = sheet.getSprite(3,1);
        water = sheet.getSprite(4,1);
        dirt = sheet.getSprite(1, 2);
        lava = sheet.getSprite(2, 3);

        player_forward = new BufferedImage[2];
        player_backward = new BufferedImage[2];
        player_left = new BufferedImage[2];
        player_right = new BufferedImage[2];

        player_idle_forward = sheet.getSprite(2, 2);
        player_idle_backward = sheet.getSprite(3, 2);

        player_forward[0] = sheet.getSprite(3, 4);
        player_forward[1] = sheet.getSprite(4, 4);
        player_backward[0] = sheet.getSprite(1, 4);
        player_backward[1] = sheet.getSprite(2, 4);
        player_right[0] = sheet.getSprite(1, 5);
        player_right[1] = sheet.getSprite(2, 5);
        player_left[0] = sheet.getSprite(3, 5);
        player_left[1] = sheet.getSprite(4, 5);

        player_swimming = sheet.crop(16, 16, 16, 8);

        System.out.println("AssetsLoader Init Done");
    }

}
