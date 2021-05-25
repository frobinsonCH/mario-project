package com.tutorial.mario.entity.mob;

import com.tutorial.mario.Handler;
import com.tutorial.mario.Id;
import com.tutorial.mario.entity.Entity;
import com.tutorial.mario.tile.Tile;
import mario.game;

import java.awt.*;

public class Player extends Entity {





    public Player(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        super(x, y, width, height, solid, id, handler);




    }
    public void tick() {
        x += velX;
        y += velY;
        if (y + height >= 771) y = 771 - height;
        if (velX!=0) animate = true;
        else animate = false;
        for (Tile t : handler.tile) {
            if (!t.solid) break;
            if (t.getId() == Id.wall) {
                if (getBoundsTop().intersects(t.getBounds())) {
                    setVelY(0);
                    if (jumping) {
                        jumping = false;
                        gravity = 0.8;
                        falling = true;
                    }

                }
                if (getBoundsBottom().intersects(t.getBounds())) {
                    setVelY(0);
                    if (falling) falling = false;
                } else {
                    if (!falling && !jumping) {
                        gravity = 0.8;
                        falling = true;
                    }
                }
                if (getBoundsLeft().intersects(t.getBounds())) {
                    setVelX(0);
                    x = t.getX() + t.width;
                }
                if (getBoundsRight().intersects(t.getBounds())) {
                    setVelX(0);
                    x = t.getX() - t.height;
                }
            }
        }

        for(int i = 0 ; i<handler.entity.size();i++){
            Entity e = handler.entity.get(i);
            if(e.getId()==Id.mushroom){
                if(getBounds().intersects(e.getBounds())){
                    int tpX = getX();
                    int tpY = getY();
                    width *= 1.5;
                    height *=1.5;
                    setX(tpX-width);
                    setY(tpY - height);
                    e.die();

                }
            } else if (e.getId()==Id.goomba){
                if(getBoundsBottom().intersects(e.getBoundsTop())){
                    e.die();
                }
                else if(getBounds().intersects(e.getBounds())){
                    die();
                }
            }
        }
        if (jumping) {
            gravity -= 0.1;
            setVelY((int) -gravity);
            if (gravity <= 0.0) {
                jumping = false;
                falling = true;
            }
        }
        if (falling) {
            gravity += 0.1;
            setVelY((int) gravity);
        }
        if(animate){
        frameDelay++;
        if (frameDelay >= 3) {
            frame++;
            if (frame >= 3) {
                frame = 0;
            }


        }
        }



    }

    public void render(Graphics g) {
        g.drawImage(game.player[frame+1].getBufferedImage(),x,y,width,height,null);

    }


}