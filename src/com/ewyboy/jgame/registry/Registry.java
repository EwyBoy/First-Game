package com.ewyboy.jgame.registry;

import com.ewyboy.jgame.tiles.Tile;
import com.ewyboy.jgame.tiles.env.GrassTile;
import com.ewyboy.jgame.tiles.env.RockTile;
import com.ewyboy.jgame.tiles.env.SandTile;
import com.ewyboy.jgame.tiles.env.WaterTile;

public class Registry {

    public static final class Tiles {
        public static Tile GRASS = new GrassTile();
        public static Tile SAND = new SandTile();
        public static Tile ROCK = new RockTile();
        public static Tile WATER = new WaterTile();
        public static Tile DIRT = new GrassTile();
    }

}
