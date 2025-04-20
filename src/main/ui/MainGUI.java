package ui;

import javax.swing.UIManager;

import ui.graphics.MovieFrame;

// Runs the GUI for the movie list application
public class MainGUI {

    // EFFECTS: runs the GUI
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // TODO: handle exception
        }
        new MovieFrame();
    }
}

