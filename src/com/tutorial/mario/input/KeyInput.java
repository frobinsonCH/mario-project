package com.tutorial.mario.input;

import com.tutorial.mario.entity.Entity;
import mario.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {
    public void keyTyped(KeyEvent arg0) {
        //not using
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for (Entity en : game.handler.entity) {
            switch (key) {
                case KeyEvent.VK_W:
                    en.setVelY(-5);
                    break;
                case KeyEvent.VK_S:
                    en.setVelY(5);
                    break;
                case KeyEvent.VK_A:
                    en.setVelX(-5);
                    break;
                case KeyEvent.VK_D:
                    en.setVelX(5);
                    break;
            }
        }

    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for (Entity en : game.handler.entity) {
            switch (key) {
                case KeyEvent.VK_W:
                    if(!en.jumping){
                        en.jumping = true;
                        en.gravity = 10.0;
                    }
                    break;
                case KeyEvent.VK_A:
                    en.setVelX(0);
                    break;
                case KeyEvent.VK_D:
                    en.setVelX(0);
                    break;

            }
        }
    }
}
