package com.ewyboy.jgame.entities;

import com.ewyboy.jgame.Handler;
import com.ewyboy.jgame.gfx.Animation;
import com.ewyboy.jgame.gfx.AssetsLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EntityPlayer extends EntityLiving {

    private Animation lastFacing;
    private Animation faceForward, faceBackward, faceLeft, faceRight;

    public EntityPlayer(Handler handler, float x, float y) {
        super(handler, x, y, EntityLiving.DEFAULT_LIVING_WIDTH, EntityLiving.DEFAULT_LIVING_HEIGHT);
        bounds.x = 22;
        bounds.y = 44;
        bounds.width = 19;
        bounds.height = 19;

        lastFacing = new Animation(300, AssetsLoader.player_forward);

        faceForward = new Animation(300, AssetsLoader.player_forward);
        faceBackward = new Animation(300, AssetsLoader.player_backward);
        faceLeft = new Animation(300, AssetsLoader.player_left);
        faceRight = new Animation(300, AssetsLoader.player_right);
    }

    private boolean isSwimming = false;

    private BufferedImage getIdleFacing(Animation facing) {
        if (facing == faceForward) {
            isSwimming = true;
            return AssetsLoader.player_swimming;
        } else if (facing == faceBackward) {
            isSwimming = false;
            return AssetsLoader.player_idle_backward;
        } else if (facing == faceLeft) {
            isSwimming = false;
            return AssetsLoader.player_left[1];
        } else if (facing == faceRight) {
            isSwimming = false;
            return AssetsLoader.player_right[0];
        } else {
            isSwimming = false;
            return AssetsLoader.player_idle_forward;
        }
    }

    private BufferedImage getCurrentAnimationFrame() {
        if(moveX < 0){
            lastFacing = faceLeft;
            return faceLeft.getCurrentFrame();
        } else if(moveX > 0){
            lastFacing = faceRight;
            return faceRight.getCurrentFrame();
        } else if(moveY < 0){
            lastFacing = faceBackward;
            return faceBackward.getCurrentFrame();
        } else if (moveY > 0) {
            lastFacing = faceForward;
            return faceForward.getCurrentFrame();
        } else {
            return lastFacing.getCurrentFrame();
        }
    }

    private void getMovementInputs() {
        moveX = 0.0f; moveY = 0.0f;

        if (handler.getKeyHandler().up)
            moveY = -speed;
        if (handler.getKeyHandler().down)
            moveY = speed;
        if (handler.getKeyHandler().left)
            moveX = -speed;
        if (handler.getKeyHandler().right)
            moveX = speed;
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
        getMovementInputs();
        move();
        handler.getCamera().centerOnEntity(this);
    }

    @Override
    public void render(Graphics graphics) {
        if (moveX != 0 || moveY != 0) {
            graphics.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getCamera().getCameraX()), (int) (y - handler.getCamera().getCameraY()), width, height, null);
        } else {
            if (isSwimming ) {
                graphics.drawImage(getIdleFacing(lastFacing), (int) (x - handler.getCamera().getCameraX()), (int) (y - handler.getCamera().getCameraY()), width, height / 2, null);
            } else {
                graphics.drawImage(getIdleFacing(lastFacing), (int) (x - handler.getCamera().getCameraX()), (int) (y - handler.getCamera().getCameraY()), width, height, null);
            }
        }
    }

    public void postRender(Graphics graphics) {}
}
