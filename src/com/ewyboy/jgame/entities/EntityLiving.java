package com.ewyboy.jgame.entities;

import com.ewyboy.jgame.Handler;
import com.ewyboy.jgame.tiles.Tile;

public abstract class EntityLiving extends Entity {

    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_HEALTH = 10;
    public static final int DEFAULT_LIVING_WIDTH = 64, DEFAULT_LIVING_HEIGHT = 64;

    protected int health;
    protected float speed;
    protected boolean alive = true;
    protected float moveX, moveY;

    public EntityLiving(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        this.handler = handler;
        speed = DEFAULT_SPEED;
        moveX = 0;
        moveY = 0;
        health = DEFAULT_HEALTH;
    }

    public void hurt(int dmg) {
        health -= dmg;
        if (health <= 0) {
            alive = false;
            die();
        }
    }

    protected abstract void die();

    public void move() {
        if (!checkEntityCollisions(moveX, 0.0f)) {
            moveX();
        }
        if (!checkEntityCollisions(0.0f, moveY)) {
            moveY();
        }
    }

    protected boolean collisionWithTile(int x, int y) {
        return handler.getWorld().getTile(x, y).isSolid();
    }

    public void moveX() {
        if(moveX > 0) {
            int tileX = (int) (x + moveX + bounds.x + bounds.width) / Tile.TILE_WIDTH;
            if(!collisionWithTile(tileX, (int) (y + bounds.y) / Tile.TILE_HEIGHT) &&
                    !collisionWithTile(tileX, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) {
                x += moveX;
            } else {
                x = tileX * Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
            }

        } else if(moveX < 0) {
            int tileX = (int) (x + moveX + bounds.x) / Tile.TILE_WIDTH;
            if(!collisionWithTile(tileX, (int) (y + bounds.y) / Tile.TILE_HEIGHT) &&
                    !collisionWithTile(tileX, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) {
                x += moveX;
            } else {
                x = tileX * Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.x;
            }
        }
    }

    public void moveY() {
        if(moveY < 0) {
            int tileY = (int) (y + moveY + bounds.y) / Tile.TILE_HEIGHT;
            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, tileY) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, tileY)){
                y += moveY;
            } else {
                y = tileY * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
            }

        } else if(moveY > 0) {
            int tileY = (int) (y + moveY + bounds.y + bounds.height) / Tile.TILE_HEIGHT;
            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, tileY) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, tileY)){
                y += moveY;
            } else {
                y = tileY * Tile.TILE_HEIGHT - bounds.y - bounds.height - 1;
            }
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public float getMoveX() {
        return moveX;
    }

    public void setMoveX(float moveX) {
        this.moveX = moveX;
    }

    public float getMoveY() {
        return moveY;
    }

    public void setMoveY(float moveY) {
        this.moveY = moveY;
    }
}
