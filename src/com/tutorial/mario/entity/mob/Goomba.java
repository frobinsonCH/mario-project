package com.tutorial.mario.entity.mob;

import com.tutorial.mario.Handler;
import com.tutorial.mario.Id;
import com.tutorial.mario.entity.Entity;
import com.tutorial.mario.tile.Tile;
import mario.game;

import java.awt.*;
import java.util.Random;

public class Goomba extends Entity {
    private Random random = new Random();


    public Goomba(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
        int dir = random.nextInt(2);
        switch(dir){
            case 0:
                setVelX(2);
                break;
            case 1:
                setVelX(-2);
                break;
        }
    }


    public void render(Graphics g) {
        g.drawImage(game.goomba[frame + 1].getBufferedImage(), x, y, width, height, null);

    }


    public void tick() {
        x += velX;
        y += velY;

        for (Tile t : handler.tile) {
            if (!t.solid) break;
            if (t.getId() == Id.wall) {
                if (getBoundsBottom().intersects(t.getBounds())) {
                    setVelY(0);
                    if (falling) falling = false;
                } else {
                    if (!falling) {
                        gravity = 0.8;
                        falling = true;
                    }
                }
                if (getBoundsLeft().intersects(t.getBounds())) {
                    setVelX(2);


                }
                if (getBoundsRight().intersects(t.getBounds())) {
                    setVelX(-2);

                }
            }
        }
        if(falling){
            gravity+=0.1;
            setVelY((int) gravity);
        }


    }
}