package com.ewyboy.jgame.tiles.base;

import com.ewyboy.jgame.tiles.Tile;

import java.awt.image.BufferedImage;

public class TileLiquid extends Tile {

    private boolean isSwimmable;

    public TileLiquid(BufferedImage texture, int id, boolean isSwimmable) {
        super(texture, id);
        this.isSwimmable = isSwimmable;
    }

    public boolean isSwimmable() {
        return isSwimmable;
    }

    public void setSwimmable(boolean swimmable) {
        isSwimmable = swimmable;
    }
}
