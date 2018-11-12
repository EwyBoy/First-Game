package com.ewyboy.jgame.loader;

import com.ewyboy.jgame.tiles.Tile;

import java.lang.reflect.Field;
import java.util.HashMap;

public class TileLoader {

    private static int tileID;
    public static final HashMap<String, Tile> TILES = new HashMap<>();


    private static int getTileID() {
        return tileID++;
    }

    public static void init(Class tileRegister) {
        try {
            for (Field field : tileRegister.getDeclaredFields()) {
                Object object = field.get(null);
                if (object instanceof Tile) {
                    Tile tile = (Tile) object;
                    String name = "tile" + field.getName().toLowerCase();
                    int ID = getTileID();
                    tile.setName(name);
                    tile.setId(ID);
                    TILES.put(name, tile);
                    System.out.println("[Tile] " + name + " has been registered with the ID: " + ID);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
