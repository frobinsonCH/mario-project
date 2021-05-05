package com.tutorial.mario.tile;

import com.tutorial.mario.Handler;
import com.tutorial.mario.Id;

import java.awt.*;

public class Wall extends Tile{

    public Wall(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
    }

    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(x,y,width,height);

    }


    public void tick() {

    }
}
