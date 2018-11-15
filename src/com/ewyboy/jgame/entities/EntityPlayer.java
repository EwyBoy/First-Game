package com.ewyboy.jgame.entities;

import com.ewyboy.jgame.Handler;
import com.ewyboy.jgame.gfx.Animation;
import com.ewyboy.jgame.gfx.AssetsLoader;
import com.ewyboy.jgame.tiles.Tile;
import com.ewyboy.jgame.tiles.base.TileLiquid;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EntityPlayer extends EntityLiving {

    private Animation lastFacing;
    private Animation faceForward, faceBackward, faceLeft, faceRight;

    protected boolean isSwimming = false;
    protected boolean isRunning = false;

    private int default_animation_speed = 300;

    public EntityPlayer(Handler handler, float x, float y) {
        super(handler, x, y, EntityLiving.DEFAULT_LIVING_WIDTH, EntityLiving.DEFAULT_LIVING_HEIGHT);
        bounds.x = 22;
        bounds.y = 44;
        bounds.width = 19;
        bounds.height = 19;

        lastFacing = new Animation(default_animation_speed, AssetsLoader.player_forward);

        faceForward = new Animation(default_animation_speed, AssetsLoader.player_forward);
        faceBackward = new Animation(default_animation_speed, AssetsLoader.player_backward);
        faceLeft = new Animation(default_animation_speed, AssetsLoader.player_left);
        faceRight = new Animation(default_animation_speed, AssetsLoader.player_right);
    }

    private BufferedImage getIdleFacing(Animation facing) {
        if (facing == faceForward) {
            return isSwimming ? AssetsLoader.player_forward_swimming : AssetsLoader.player_idle_forward;
        } else if (facing == faceBackward) {
            return isSwimming ? AssetsLoader.player_backward_swimming : AssetsLoader.player_idle_backward;
        } else if (facing == faceLeft) {
            return isSwimming ? AssetsLoader.player_left_swimming : AssetsLoader.player_left[1];
        } else if (facing == faceRight) {
            return isSwimming ? AssetsLoader.player_right_swimming : AssetsLoader.player_right[0];
        } else {
            return AssetsLoader.player_idle_forward;
        }
    }

    private BufferedImage getCurrentAnimationFrame() {
        if(moveX < 0) {
            lastFacing = faceLeft;
            return isSwimming ? AssetsLoader.player_left_swimming : faceLeft.getCurrentFrame();
        } else if (moveX > 0) {
            lastFacing = faceRight;
            return isSwimming ? AssetsLoader.player_right_swimming : faceRight.getCurrentFrame();
        } else if (moveY < 0) {
            lastFacing = faceBackward;
            return isSwimming ? AssetsLoader.player_backward_swimming : faceBackward.getCurrentFrame();
        } else if (moveY > 0) {
            lastFacing = faceForward;
            return isSwimming ? AssetsLoader.player_forward_swimming : faceForward.getCurrentFrame();
        } else {
            //TODO not sure on this one yet
            return lastFacing.getCurrentFrame();
        }
    }

    private void getMovementInputs() {
        moveX = 0.0f; moveY = 0.0f;

        isRunning = handler.getKeyHandler().sprint;
        if (isSwimming) isRunning = false;

        setSpeed(isRunning ? 5.0f : DEFAULT_SPEED);

        if (handler.getKeyHandler().up)
            moveY = -speed;
        if (handler.getKeyHandler().down)
            moveY = speed;
        if (handler.getKeyHandler().left)
            moveX = -speed;
        if (handler.getKeyHandler().right)
            moveX = speed;
    }

    private boolean isPlayerInWater(int x, int y) {
        if (handler.getWorld().getTile(x, y) instanceof TileLiquid) {
            TileLiquid tileLiquid = (TileLiquid) handler.getWorld().getTile(x, y);
            return tileLiquid.isSwimmable();
        }
        return false;
    }

    @Override
    protected void die() {
        System.out.println("Sucks to suck, huh?");
    }

    private void facingTick() {
        faceForward.tick();
        faceBackward.tick();
        faceLeft.tick();
        faceRight.tick();
    }

    @Override
    public void tick() {
        facingTick();
        if (isPlayerInWater((int) x, (int) y)) {
            isSwimming = true;
        }
        getMovementInputs();
        move();
        handler.getCamera().centerOnEntity(this);
    }

    private void animationSpeed(Animation animation, boolean isRunning) {
        animation.setSpeed(isRunning ? 200 : default_animation_speed);
    }

    @Override
    public void render(Graphics graphics) {
        animationSpeed(faceForward, isRunning);
        animationSpeed(faceBackward, isRunning);
        animationSpeed(faceLeft, isRunning);
        animationSpeed(faceRight, isRunning);
        int spriteHeight = isSwimming ? height / 2 : getHeight();
        graphics.drawImage(moveX != 0 || moveY != 0 ? getCurrentAnimationFrame() : getIdleFacing(lastFacing), (int) (x - handler.getCamera().getCameraX()), (int) (y - handler.getCamera().getCameraY()), width, spriteHeight, null);
    }

    public void postRender(Graphics graphics) {}
}
