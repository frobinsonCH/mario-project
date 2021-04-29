package mario;

import javax.swing.*;
import java.awt.*;


public class game extends Canvas {
    public static final int width = 270;
    public static final int height = (width / 14) * 10;
    public static final int scale = 4;
    public static final String title = "Mario";

    public game(){

        Dimension size = new Dimension(width*scale, height*scale);
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
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
    }
}
