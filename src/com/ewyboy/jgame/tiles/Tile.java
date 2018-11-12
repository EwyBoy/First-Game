package com.ewyboy.jgame.tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    public static Tile[] tiles = new Tile[256];

    public static final int TILE_WIDTH = 16, TILE_HEIGHT = 16;

    protected BufferedImage texture;

    protected int id;
    protected String name;

    public Tile(BufferedImage texture) {
        this.texture = texture;
        tiles[id] = this;
    }

    public void tick() {}

    public void render(Graphics graphics, int x, int y) {
        graphics.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    public boolean isSolid() {
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
