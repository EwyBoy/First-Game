package com.ewyboy.jgame.tiles.env;

import com.ewyboy.jgame.gfx.AssetsLoader;
import com.ewyboy.jgame.tiles.base.TileLiquid;

public class WaterTile extends TileLiquid {

    public WaterTile(int id) {
        super(AssetsLoader.water, id, true);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
