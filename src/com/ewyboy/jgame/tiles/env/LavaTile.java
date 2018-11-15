package com.ewyboy.jgame.tiles.env;

import com.ewyboy.jgame.gfx.AssetsLoader;
import com.ewyboy.jgame.tiles.base.TileLiquid;

public class LavaTile extends TileLiquid {

    public LavaTile(int id) {
        super(AssetsLoader.lava, id, false);
    }

}
