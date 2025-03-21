package ui;

import javax.swing.*;
import java.awt.Color;

public class MyFrame extends JFrame {

    //EFFECTS: creates a new frame
    public MyFrame() {

        this.setTitle("Movie List Application");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1000, 700); 
        this.setVisible(true);

        ImageIcon icon = new ImageIcon("data/logo.png");
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(Color.darkGray);
    }
}
