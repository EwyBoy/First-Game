package com.ewyboy.jgame.entities;

import com.ewyboy.jgame.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class EntityHandler {

    private Handler handler;
    private EntityPlayer player;
    private ArrayList<Entity> entities;

    private Comparator<Entity> renderPriority = (entityA, entityB) -> {
        if (entityA.getY() + entityA.getHeight() < entityB.getY() + entityB.getHeight()) {
            return -1;
        }
        return 1;
    };

    public EntityHandler(Handler handler, EntityPlayer player) {
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<>();
        addEntity(player);
    }

    public void tick() {
       Iterator<Entity> iterator = entities.iterator();
       while (iterator.hasNext()) {
           Entity entity = iterator.next();
           entity.tick();
           if (!entity.isActive) {
               iterator.remove();
           }
       }
       entities.sort(renderPriority);
    }

    public void render(Graphics graphics) {
        for (Entity entity : entities) {
            entity.render(graphics);
        }
        player.postRender(graphics);
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public EntityPlayer getPlayer() {
        return player;
    }

    public void setPlayer(EntityPlayer player) {
        this.player = player;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }
}
