package ui.graphics.panels;

import java.awt.event.ActionListener;
import javax.swing.*;

import model.MovieList;

// Represents a panel for a movie list
public abstract class MovieListPanel extends JPanel implements ActionListener {
    private JComboBox<String> comboBox;
    private MovieList movieList;
    private JButton actionButton;

    // EFFECTS: creates a new MoviePanel
    public MovieListPanel(MovieList movieList) {
        this.movieList = movieList;
        this.comboBox = new JComboBox<>();
        this.actionButton = new JButton();
    }

    // REQUIRES: status is one of: "Watched", "Currently Watching", "To-watch"
    // MODIFIES: this
    // EFFECTS: returns the shortened status    
    public String getMovieStatusShortenedString(String status) {
        if (status.equals("Watched")) {
            return "w";
        } else if (status.equals("Currently Watching")) {
            return "c";
        } else if (status.equals("To-watch")) {
            return "t";
        } 

        return "";
    }

    public JComboBox<String> getComboBox() {
        return this.comboBox;
    }

    public MovieList getMovieList() {
        return this.movieList;
    }

    public JButton getButton() {
        return this.actionButton;
    }

    public void setMovieList(MovieList movieList) {
        this.movieList = movieList;
    }

    // MODIFIES: this
    // EFFECTS: sets up the panel
    public abstract void setupPanel();
}
