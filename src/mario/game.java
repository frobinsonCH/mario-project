package mario;


import com.tutorial.mario.Handler;
import com.tutorial.mario.Id;
import com.tutorial.mario.entity.Player;
import com.tutorial.mario.input.KeyInput;

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

    private void init(){
        handler = new Handler();
        addKeyListener(new KeyInput());
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
        g.setColor(Color.MAGENTA);
        g.fillRect(0,0,getWidth(),getHeight());
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
