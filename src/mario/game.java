package mario;


import com.tutorial.mario.Handler;
import com.tutorial.mario.Id;
import com.tutorial.mario.entity.Entity;
import com.tutorial.mario.entity.Player;
import com.tutorial.mario.gfx.Sprite;
import com.tutorial.mario.gfx.SpriteSheet;
import com.tutorial.mario.input.KeyInput;
import com.tutorial.mario.tile.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.BufferedInputStream;



public class game extends Canvas implements Runnable {
    public static final int width = 270;
    public static final int height = width / 14 * 10;
    public static final int scale = 4;
    public static final String title = "Mario";

    private Thread thread;
    private boolean running = false;
    public static Handler handler;
    public static Camera cam;
    public static SpriteSheet sheet;
    public static Sprite grass;
    public static Sprite player[] = new Sprite [10];

    private void init(){
        handler = new Handler();
        cam = new Camera();
        sheet = new SpriteSheet("/SpriteSheet.png");
        addKeyListener(new KeyInput());
        grass = new Sprite(sheet,1,1);
        for(int i=0;i<player.length;i++){
            player[i] = new Sprite(sheet,i+1,1);
        }
        handler.addEntity(new Player(50,80,64,64,true, Id.player,handler));


    }

    private synchronized void start(){
      if(running)return;
      running = true;
      thread = new Thread(this, "thread");
      thread.start();

    }
    private synchronized void stop(){
        if(!running)return;
        running = false;
        try{
            thread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }

    }
    public void run(){
        init();
        requestFocus();
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double delta = 0.0;
        double ns = 1000000000.0/60.0;
        int frames = 0;
        int ticks = 0;
      while(running){
          long now = System.nanoTime();
          delta += (now-lastTime)/ns;
          lastTime = now;
          while(delta >=1){
              tick();
              ticks++;
              delta--;
          }
          render();
          frames ++;
          if(System.currentTimeMillis()- timer > 1000){
              timer += 1000;
              System.out.println(frames + " Frames per Second " + ticks + "Updates per Second");
              frames = 0;
              ticks= 0;
          }
      }
      stop();

    }
    public void render(){
        BufferStrategy bs = getBufferStrategy();
        if(bs==null){
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0,0,getWidth(),getHeight());
        g.translate(cam.getX(), cam.getY());
        handler.render(g);
        g.dispose();
        bs.show();

    }

    public game(){

        Dimension size = new Dimension(width*scale, height*scale);
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
    }

    public void tick(){
        handler.tick();
        for (Entity e:handler.entity){
            if(e.getId()==Id.player){
                cam.tick(e);
            }
        }

    }
    public static int getFrameWidth(){
        return WIDTH*scale;
    }
    public static int getFrameHeight(){
        return HEIGHT*scale;
    }

    public static void main(String[] args) {
        game game = new game();
        JFrame frame = new JFrame(title);
        frame.add(game);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        game.start();
    }
}
