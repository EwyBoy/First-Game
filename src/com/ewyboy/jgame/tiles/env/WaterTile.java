package com.ewyboy.jgame.tiles.env;

import com.ewyboy.jgame.gfx.AssetsLoader;
import com.ewyboy.jgame.tiles.Tile;

public class WaterTile extends Tile {

    public WaterTile(int id) {
        super(AssetsLoader.water, id);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
