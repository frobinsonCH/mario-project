package com.tutorial.mario.tile;

import java.awt.*;

public class tile {
    public class tile {
        public int x, y;
        public int width, height;
        public boolean solid;
        public int velX, velY;

        public tile(int x, int y, int width, int height, boolean solid){
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.solid = solid;
        }

        public void render(Graphics g){

        }
        public void tick(){
            x+= velX;
            y+= velY;

        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public boolean isSolid() {
            return solid;
        }

        public void setSolid(boolean solid) {
            this.solid = solid;
        }

        public void setVelX(int velX) {
            this.velX = velX;
        }

        public void setVelY(int velY) {
            this.velY = velY;
        }
    }

}
