package com.tutorial.mario.tile;

import com.tutorial.mario.Handler;
import com.tutorial.mario.Id;
import mario.game;

import java.awt.*;

public class Wall extends Tile{

    public Wall(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
    }

    public void render(Graphics g) {
        g.drawImage(game.grass.getBufferedImage(),x,y,width,height,null);

    }


    public void tick() {

    }
}
