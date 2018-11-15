package com.ewyboy.jgame.world;

import com.ewyboy.jgame.Handler;
import com.ewyboy.jgame.entities.EntityHandler;
import com.ewyboy.jgame.entities.EntityPlayer;
import com.ewyboy.jgame.registry.Registry;
import com.ewyboy.jgame.tiles.Tile;
import com.ewyboy.jgame.utils.FileUtility;

import java.awt.*;
import java.util.Arrays;

public class World {

    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private int[][] tiles;

    private EntityHandler entityHandler;

    public World(Handler handler, String path) {
        this.handler = handler;
        entityHandler = new EntityHandler(handler, new EntityPlayer(handler, 150, 150));
        loadWorldFile(path);
        entityHandler.getPlayer().setX(spawnX);
        entityHandler.getPlayer().setY(spawnY);
    }

    public void tick() {
        entityHandler.tick();
    }

    private void loadWorldFile(String path) {
        System.out.println("loading in world from file");
        String file = FileUtility.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = FileUtility.parseInt(tokens[0]);
        height = FileUtility.parseInt(tokens[1]);
        spawnX = FileUtility.parseInt(tokens[2]);
        spawnY = FileUtility.parseInt(tokens[3]);

        tiles = new int[width][height];
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                tiles[x][y] = FileUtility.parseInt(tokens[(x + y * width) + 4]);
                //System.out.println("PosX: " + x + " PosY: " + y + " Tile ID: " + FileUtility.parseInt(tokens[(x + y * width)]));
            }
        }
    }

    public void render(Graphics graphics) {
        int xStart = (int) Math.max(0, handler.getCamera().getCameraX() / Tile.TILE_WIDTH);
        int xEnd = (int) Math.min(width, (handler.getCamera().getCameraX() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
        int yStart = (int) Math.max(0, handler.getCamera().getCameraY() / Tile.TILE_HEIGHT);
        int yEnd = (int) Math.min(height, (handler.getCamera().getCameraY() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);

        for(int y = yStart;y < yEnd;y++){
            for(int x = xStart;x < xEnd;x++){
                getTile(x, y).render(graphics, (int) (x * Tile.TILE_WIDTH - handler.getCamera().getCameraX()),
                        (int) (y * Tile.TILE_HEIGHT - handler.getCamera().getCameraY()));
            }
        }
        entityHandler.render(graphics);
    }

    public Tile getTile(int x, int y) {
        if(x < 0 || y < 0 || x >= width || y >= height) {
            return Registry.Tiles.GRASS;
        }

        Tile tile = Tile.tiles[tiles[x][y]];

        if(tile == null)
            return Registry.Tiles.WATER;
        return tile;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public EntityHandler getEntityHandler() {
        return entityHandler;
    }

    public void setEntityHandler(EntityHandler entityHandler) {
        this.entityHandler = entityHandler;
    }
}
