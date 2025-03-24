package ui.graphics;

import model.Movie;
import model.MovieList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents a frame for the movie list app
public class MovieFrame extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/movieList.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private ImageIcon image;
    private MovieList movieList;
    private JTextArea textArea;
    private MenuHandler menuHandler;

    // EFFECTS: creates a new frame
    public MovieFrame() {
        this.image = new ImageIcon("data/logo.png");
        this.image = new ImageIcon(image.getImage().getScaledInstance(500, 500, 1));
        this.movieList = new MovieList();
        this.jsonWriter = new JsonWriter(JSON_STORE);
        this.jsonReader = new JsonReader(JSON_STORE);
        this.textArea = new JTextArea();
        this.textArea.setBounds(50, 50, 400, 300);
        this.menuHandler = new MenuHandler(this);

        initializeFrame();

        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initialized the frame
    public void initializeFrame() {
        this.setTitle("Movie List Application");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(500, 500);
        this.setIconImage(image.getImage());
        this.setLayout(null);
        this.setJMenuBar(menuHandler.getMenuBar());
        this.add(textArea);
    }

    // EFFECTS: handles the actions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuHandler.getLoadMenuItem()) { // Load
            loadMovieList();
        }

        if (e.getSource() == menuHandler.getSaveMenuItem()) { // Save
            saveMovieList();
        }

        if (e.getSource() == menuHandler.getAddMenuItem()) { // Add
            addMovie();
        }

        if (e.getSource() == menuHandler.getRemoveMenuItem()) { // Remove
            removeMovie();
        }

        if (e.getSource() == menuHandler.getDisplayMenuItem()) { // Display
            displayMovies();
        }
    }

    // EFFECTS: loads the movie list from file
    private void loadMovieList() {
        try {
            movieList = jsonReader.read();
            textArea.setText("Loaded movie list from " + JSON_STORE);
        } catch (IOException e) {
            textArea.setText("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: saves the movie list to file
    private void saveMovieList() {
        try {
            jsonWriter.open();
            jsonWriter.write(movieList);
            jsonWriter.close();
            textArea.setText("Saved movie list to " + JSON_STORE);
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