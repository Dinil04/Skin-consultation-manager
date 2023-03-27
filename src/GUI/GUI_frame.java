package GUI;

import javax.swing.*;
import java.awt.*;

public abstract class GUI_frame extends JFrame {
    public abstract void button();
    public void window(String name,int width,int height){

        setSize(width,height);
        setTitle(name);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // DISPOSE_ON_CLOSE or 3
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        setVisible(true);
        ImageIcon img = new ImageIcon("src/GUI/sk.jpg");
        setIconImage(img.getImage());

    }

}
