package mario;

import com.tutorial.mario.entity.Entity;

public class Camera {
    public int x;
    public int y;
    public void tick(Entity player){
        setX(-player.getX() + game.width*2);
        setY(-player.getY() + game.height*2);
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
}
