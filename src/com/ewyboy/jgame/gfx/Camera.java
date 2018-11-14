package com.ewyboy.jgame.gfx;

import com.ewyboy.jgame.Handler;
import com.ewyboy.jgame.entities.Entity;
import com.ewyboy.jgame.tiles.Tile;

public class Camera {

    private Handler handler;
    private float cameraX, cameraY;

    public Camera(Handler handler, float cameraX, float cameraY) {
        this.handler = handler;
        this.cameraX = cameraX;
        this.cameraY = cameraY;
    }

    public void checkBlankSpace(){
        if(cameraX < 0) {
            cameraX = 0;
        } else if(cameraX > handler.getWorld().getWidth() * Tile.TILE_WIDTH - handler.getWidth()){
            cameraX = handler.getWorld().getWidth() * Tile.TILE_WIDTH - handler.getWidth();
        }

        if(cameraY < 0) {
            cameraY = 0;
        } else if(cameraY > handler.getWorld().getHeight() * Tile.TILE_HEIGHT - handler.getHeight()){
            cameraY = handler.getWorld().getHeight() * Tile.TILE_HEIGHT - handler.getHeight();
        }
    }

    public void centerOnEntity(Entity entity) {
        cameraX = entity.getX() - handler.getWidth() / 2 + entity.getWidth() / 2;
        cameraY = entity.getY() - handler.getHeight() / 2 + entity.getHeight() / 2;
        checkBlankSpace();
    }

    public void move(float moveX, float moveY) {
        cameraX += moveX;
        cameraY += moveY;
        checkBlankSpace();
    }

    public float getCameraX() {
        return cameraX;
    }

    public void setCameraX(float cameraX) {
        this.cameraX = cameraX;
    }

    public float getCameraY() {
        return cameraY;
    }

    public void setCameraY(float cameraY) {
        this.cameraY = cameraY;
    }
}
