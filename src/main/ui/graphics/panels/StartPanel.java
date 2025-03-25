package ui.graphics.panels;

import javax.swing.*;
import java.awt.*;

// Represents a panel for the start screen
public class StartPanel extends JPanel {
    private static final String LOGO_PATH = "data/logo.png";
    private ImageIcon image;
    private JLabel logoLabel;
    
    //EFFECTS: creates a new StartPanel
    public StartPanel() {
        this.image = new ImageIcon(LOGO_PATH);
        this.image = new ImageIcon(image.getImage().getScaledInstance(150, 130, Image.SCALE_SMOOTH));
        this.logoLabel = new JLabel("Movie List Application");
        this.logoLabel.setIcon(image);
        this.logoLabel.setHorizontalTextPosition(JLabel.CENTER);
        this.logoLabel.setVerticalTextPosition(JLabel.BOTTOM);
        add(logoLabel);
    }
}
