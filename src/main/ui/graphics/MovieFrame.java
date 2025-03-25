package ui.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

import model.*;
import persistence.*;
import ui.graphics.panels.CardPanel;

// Represents a frame for the movie list application
public class MovieFrame extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/movieList.json";
    private static final String LOGO_PATH = "data/logo.png";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private MenuHandler menuHandler;
    private CardPanel cardPanel;
    private MovieList movieList;

    //EFFECTS: creates a new frame
    public MovieFrame() {
        this.jsonWriter = new JsonWriter(JSON_STORE);
        this.jsonReader = new JsonReader(JSON_STORE);
        this.movieList = new MovieList();
        this.menuHandler = new MenuHandler(this);
        this.cardPanel = new CardPanel(this.movieList);

        initializeFrame();

        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes the frame
    public void initializeFrame() {
        setTitle("Movie List Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(250, 240); 
        ImageIcon image = new ImageIcon(LOGO_PATH);
        image = new ImageIcon(image.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT));
        setIconImage(image.getImage());
        setLayout(new BorderLayout());
        setJMenuBar(menuHandler.getMenuBar());
        add(cardPanel);
    }

    // MODIFIES: this
    // EFFECTS: handles actions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuHandler.getLoadMenuItem()) {
            loadMovies();
        } else if (e.getSource() == menuHandler.getSaveMenuItem()) {
            saveMovies();
        } else if (e.getSource() == menuHandler.getAddMenuItem()) {
            cardPanel.switchPanels("add", this.movieList);
        } else if (e.getSource() == menuHandler.getRemoveMenuItem()) {
            cardPanel.switchPanels("remove", this.movieList);
        } else if (e.getSource() == menuHandler.getDisplayMenuItem()) {
            cardPanel.switchPanels("display", this.movieList);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads movies
    public void loadMovies() {
        try {
            movieList = jsonReader.read();
            JOptionPane.showMessageDialog(this, "Loaded movie list from: " + JSON_STORE, 
                                        "Load", JOptionPane.INFORMATION_MESSAGE);
            this.cardPanel.updatePanels(this.movieList);
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
            JOptionPane.showMessageDialog(this, "Saved movie list to: " + JSON_STORE, 
                                        "Save", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Unable to save movies", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
