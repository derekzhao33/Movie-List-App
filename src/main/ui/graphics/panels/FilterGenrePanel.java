package ui.graphics.panels;

import java.awt.event.ActionEvent;
import java.util.LinkedHashSet;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import model.Movie;
import model.MovieList;

public class FilterGenrePanel extends DisplayInfoPanel {
    private LinkedHashSet<String> genres;

    // EFFECTS: creates a new FilterGenrePanel
    public FilterGenrePanel(MovieList movieList) {
        super(movieList);
        this.genres = new LinkedHashSet<>();
    }

    // MODIFIES: this
    // EFFECTS: sets up the panel
    @Override
    public void setupPanel() {
        setupPanel("Filter Movies", "Genre:");
    }

    // MODIFIES: this
    // EFFECTS: updates the genres from given movie list
    public void updateGenres(MovieList movieList) {
        LinkedHashMap<Integer, Movie> movies = movieList.getMovieList();

        for (Map.Entry<Integer, Movie> entry : movies.entrySet()) {
            Movie m = entry.getValue();
            String genre = m.getGenre();

            this.genres.add(genre);
        }
    }

    // MODIFIES: this
    // EFFECTS: updates the combo box with correct genres
    @Override
    public void updateComboBox(MovieList movieList) {
        updateGenres(movieList);

        for (String g : genres) {
            super.getComboBox().addItem(g);
        }
    }

    //EFFECTS: handles the actions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == super.getButton()) {
            MovieList movies = super.getMovieList();

            if (movies.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Empty movie list, cannot display info", 
                                            "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Object comboBoxItem = super.getComboBox().getSelectedItem();
                String genre = comboBoxItem.toString();

                String filterText = movies.getAllNamesByGenre(genre);
                super.getInfoArea().setText(filterText);
            }
        }
    }
}
