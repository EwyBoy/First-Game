package com.ewyboy.jgame.tiles.env;

import com.ewyboy.jgame.gfx.AssetsLoader;
import com.ewyboy.jgame.tiles.Tile;

public class RockTile extends Tile {

    public RockTile(int id) {
        super(AssetsLoader.rock, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
