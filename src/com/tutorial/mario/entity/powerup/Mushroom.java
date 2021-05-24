package com.tutorial.mario.entity.powerup;

import com.tutorial.mario.Handler;
import com.tutorial.mario.Id;
import com.tutorial.mario.entity.Entity;
import mario.game;

import java.awt.*;

public class Mushroom extends Entity {

    public Mushroom(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
    }

    public void render(Graphics g) {
        g.drawImage(game.mushroom.getBufferedImage(),x,y,width,height,null);

    }


    public void tick() {

    }
}
