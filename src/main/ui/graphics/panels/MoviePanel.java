package ui.graphics.panels;

import java.awt.event.ActionListener;
import javax.swing.*;

import model.MovieList;

public abstract class MoviePanel extends JPanel implements ActionListener {
    private JComboBox<String> comboBox;
    private MovieList movieList;

    public MoviePanel(MovieList movieList) {
        this.movieList = movieList;
        this.comboBox = new JComboBox<>();
    }

    public JComboBox<String> getComboBox() {
        return this.comboBox;
    }

    public MovieList getMovieList() {
        return this.movieList;
    }

    public abstract void setupPanel();
}
