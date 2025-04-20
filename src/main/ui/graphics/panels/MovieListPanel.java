package ui.graphics.panels;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import model.MovieList;
import ui.graphics.panels.observer.Observer;
import ui.graphics.panels.observer.Subject;

// Represents a panel for a movie list
public abstract class MovieListPanel extends Subject implements ActionListener {
    protected static final Font FONT_BOLD = new Font(null, Font.BOLD, 15);
    protected static final Font FONT = new Font(null, Font.PLAIN, 15);
    protected static final Dimension DIMENSION = new Dimension(250, 32);
    protected JComboBox<String> comboBox;
    protected MovieList movieList;
    protected JButton actionButton;

    // EFFECTS: creates a new Subject
    public MovieListPanel(MovieList movieList) {
        movieList = movieList;
        comboBox = new JComboBox<>();
        actionButton = new JButton();
        comboBox.setPreferredSize(new Dimension(250, 40));
        actionButton.setPreferredSize(DIMENSION);
        comboBox.setFont(FONT);
        actionButton.setFont(FONT_BOLD);
        comboBox.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));
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
        return comboBox;
    }

    public MovieList getMovieList() {
        return movieList;
    }

    public JButton getButton() {
        return actionButton;
    }

    public void setMovieList(MovieList movieList) {
        this.movieList = movieList;
    }

    // MODIFIES: this
    // EFFECTS: sets up the panel
    public abstract void setupPanel();
}
