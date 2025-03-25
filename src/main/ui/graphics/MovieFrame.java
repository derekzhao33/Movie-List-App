package ui.graphics;

import model.Movie;
import model.MovieList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JTextArea textArea;
    private MenuHandler menuHandler;
    private JLabel label;

    // EFFECTS: creates a new frame
    public MovieFrame() {
        this.image = new ImageIcon("data/logo.png");
        this.image = new ImageIcon(image.getImage().getScaledInstance(500, 500, 1));
        this.movieList = new MovieList();
        this.jsonWriter = new JsonWriter(JSON_STORE);
        this.jsonReader = new JsonReader(JSON_STORE);
        this.movieList = new MovieList();
        this.menuHandler = new MenuHandler(this);
        this.label = new JLabel(image);

        initializeFrame();

        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initialized the frame
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

    // EFFECTS: handles the actions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuHandler.getLoadMenuItem()) {
            loadMovies();
        } else if (e.getSource() == menuHandler.getSaveMenuItem()) {
            saveMovies();
        } else if (e.getSource() == menuHandler.getAddMenuItem()) {
            this.cardPanel.switchPanels("add", this.movieList);
        } else if (e.getSource() == menuHandler.getRemoveMenuItem()) {
            this.cardPanel.switchPanels("remove", this.movieList);
        } else if (e.getSource() == menuHandler.getDisplayMenuItem()) {
            this.cardPanel.switchPanels("display", this.movieList);
        } else if (e.getSource() == menuHandler.getFilterStatusMenuItem()) {
            this.cardPanel.switchPanels("filter status", this.movieList);
        }
    }

    // EFFECTS: loads the movie list from file
    private void loadMovieList() {
        try {
            this.movieList = jsonReader.read();
            JOptionPane.showMessageDialog(this, "Loaded movie list from: " + JSON_STORE, 
                                        "Load", JOptionPane.INFORMATION_MESSAGE);
            this.cardPanel.updatePanels(this.movieList);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unable to load movies", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // EFFECTS: saves the movie list to file
    private void saveMovieList() {
        try {
            this.jsonWriter.open();
            this.jsonWriter.write(movieList);
            this.jsonWriter.close();
            JOptionPane.showMessageDialog(this, "Saved movie list to: " + JSON_STORE, 
                                        "Save", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException e) {
            textArea.setText("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a new movie to the list
    private void addMovie() {
        String status = JOptionPane.showInputDialog("Enter the status of the new movie (w, c, t):");
        String name = JOptionPane.showInputDialog("Enter the name of the new movie:");
        String genre = JOptionPane.showInputDialog("Enter the genre of the new movie:");

        if (status != null && name != null && genre != null) {
            movieList.addMovie(new Movie(status, name, genre));
            textArea.setText("Added movie: " + name);
        } else {
            textArea.setText("Movie addition cancelled.");
        }
    }

    // MODIFIES: this
    // EFFECTS: removes a movie from the list
    private void removeMovie() {
        String input = JOptionPane.showInputDialog("Enter the number of the movie to be removed:");
        if (input != null) {
            int movieNum = Integer.parseInt(input);
            movieList.removeMovie(movieNum);
            textArea.setText("Removed movie number: " + movieNum);
        } else {
            textArea.setText("Movie removal cancelled.");
        }
    }

    // EFFECTS: displays all movies in the list
    private void displayMovies() {
        textArea.setText(movieList.getAllNames());
    }
}