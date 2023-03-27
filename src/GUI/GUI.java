package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends GUI_frame implements ActionListener {
    private JButton button1;
    private JButton button2;

    public GUI(){
        //Adding an image for the home page
        ImageIcon img = new ImageIcon("src/GUI/home.png");
        JLabel label2 = new JLabel();
        //Adding the Topic
        label2.setText("WESTMINSTER SKIN CONSULTATION");
        label2.setIcon(img);
        label2.setHorizontalTextPosition(JLabel.CENTER);
        label2.setVerticalTextPosition(JLabel.BOTTOM);
        label2.setBounds(10,2,400,450);
        label2.setFont(new Font("SansSerif", Font.BOLD, 15));
        label2.setBackground(new Color(230,230,230));
        label2.setOpaque(true);

        JLabel label1 = new JLabel();
        label1.setBounds(0,0,800,500);
        label1.setBackground(new Color(237,192,158));
        label1.setOpaque(true);


        button();
        this.add(label2);
        this.add(label1);
        //window name and sizes
        window("Westminster_SkinConsultation_Manager",800,500);
    }
    //creating two button
    public void button_set(JButton but,String name , int y) {
        but.setBounds(420,y,350,40);
        but.setText(name);
        but.setFocusable(false);
        but.setBackground(new Color(212,197,180));
        but.addActionListener(this);
        this.add(but);
}
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==button1) {
            this.dispose();
            new Frame1();
        } else if (e.getSource()==button2) {
            this.dispose();
            new Frame2(true);
        }
    }

    @Override
    public void button() {
        button1 = new JButton();
        button_set(button1,"CONSULTATION",160);
        button2 = new JButton();
        button_set(button2,"All DOCTORS DETAILS",280);
    }
}
