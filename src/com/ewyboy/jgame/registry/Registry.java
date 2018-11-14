package com.ewyboy.jgame.registry;

import com.ewyboy.jgame.tiles.Tile;
import com.ewyboy.jgame.tiles.env.GrassTile;
import com.ewyboy.jgame.tiles.env.RockTile;
import com.ewyboy.jgame.tiles.env.SandTile;
import com.ewyboy.jgame.tiles.env.WaterTile;

public class Registry {

    public static final class Tiles {
        public static Tile GRASS = new GrassTile(0);
        public static Tile DIRT = new WaterTile(1);
        public static Tile SAND = new SandTile(2);
        public static Tile ROCK = new RockTile(3);
        public static Tile WATER = new WaterTile(4);
        public static Tile LAVA = new WaterTile(5);
    }

}
