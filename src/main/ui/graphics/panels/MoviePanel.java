package ui.graphics.panels;

import java.awt.event.ActionListener;
import javax.swing.*;

import model.MovieList;

// Represents a panel for a Movie
public abstract class MoviePanel extends JPanel implements ActionListener {
    private JComboBox<String> comboBox;
    private MovieList movieList;
    private JButton actionButton;

    // EFFECTS: creates a new MoviePanel
    public MoviePanel(MovieList movieList) {
        this.movieList = movieList;
        this.comboBox = new JComboBox<>();
        this.actionButton = new JButton();
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
