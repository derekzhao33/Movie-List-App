package ui.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.*;

import model.*;
import persistence.*;

// Represents a frame
// TODO: combo boxes and menus
public class MovieFrame extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/movieList.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private ImageIcon image;
    private MenuHandler menuHandler;
    private MovieList movieList;

    //EFFECTS: creates a new frame
    public MovieFrame() {
        this.jsonWriter = new JsonWriter(JSON_STORE);
        this.jsonReader = new JsonReader(JSON_STORE);
        this.movieList = new MovieList();
        this.image = new ImageIcon("data/logo.png");
        this.image = new ImageIcon(image.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT));
        this.menuHandler = new MenuHandler(this);

        initializeFrame();

        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes the frame
    public void initializeFrame() {
        this.setTitle("Movie List Application");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(500, 500); 
        this.setIconImage(image.getImage());
        this.setLayout(null);
        this.setJMenuBar(menuHandler.getMenuBar());
    }

    // MODIFIES: this
    // EFFECTS: handles actions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuHandler.getLoadMenuItem()) {
            loadMovies();
        } 

        if (e.getSource() == menuHandler.getSaveMenuItem()) {
            saveMovies();
        } 
        
        if (e.getSource() == menuHandler.getAddMenuItem()) {
            // TODO: add a method to add movies in the frame
        } 
        
        if (e.getSource() == menuHandler.getRemoveMenuItem()) {
            // TODO: add a method to remove movies in the frame
        } 
        
        if (e.getSource() == menuHandler.getDisplayMenuItem()) {
            // TODO: add a method to display movies in the frame
        }
    }

    // MODIFIES: this
    // EFFECTS: loads movies
    public void loadMovies() {
        try {
            movieList = jsonReader.read();
            JOptionPane.showMessageDialog(this, "Loaded movie list from: " + JSON_STORE, "Load", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unable to load movies", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: saves movies
    public void saveMovies() {
        try {
            jsonWriter.open();
            jsonWriter.write(movieList);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this, "Saved movie list to: " + JSON_STORE, "Save", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Unable to save movies", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
