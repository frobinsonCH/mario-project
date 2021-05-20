package com.tutorial.mario;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import com.tutorial.mario.entity.Entity;
import com.tutorial.mario.entity.Player;
import com.tutorial.mario.tile.Tile;
import com.tutorial.mario.tile.Wall;
import mario.game;


public class Handler {
    public LinkedList<Entity> entity = new LinkedList<Entity>();
    public LinkedList<Tile> tile = new LinkedList<Tile>();

    public Handler() {


    }

    public void render(Graphics g) {
        for (Entity en : entity) {
            en.render(g);
        }
        for (Tile ti : tile) {
            ti.render(g);
        }
    }


    public void tick() {
        for (Entity en : entity) {
            en.tick();
        }
        for (Tile ti : tile) {
            ti.tick();

        }
    }

    public void addEntity(Entity en) {
        entity.add(en);
    }

    public void removeEntity(Entity en) {
        entity.remove(en);
    }

    public void addTile(Tile ti) {
        tile.add(ti);
    }

    public void removeTile(Tile ti) {
        tile.remove(ti);
    }

    public void createLevel(BufferedImage level) {

        int width = level.getWidth();
        int height = level.getHeight();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = level.getRGB(x, y);
                int red = (pixel >> 16) & 0xFF;
                int green = (pixel >> 8) & 0xFF;
                int blue = (pixel) & 0xFF;

                if (red == 0 && green == 0 && blue == 0) addTile(new Wall(x * 64, y * 64, 64, 64, true, Id.wall, this));
                if (red == 0 && green == 0 && blue == 255)
                    addEntity(new Player(x * 64, y * 64, 64, 64, false, Id.player, this));


            }
        }

    }
}