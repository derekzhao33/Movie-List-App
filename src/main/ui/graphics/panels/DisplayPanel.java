package ui.graphics.panels;

import java.util.Map;

import model.Movie;
import model.MovieList;

public abstract class DisplayPanel extends MoviePanel {

    public DisplayPanel(MovieList movieList) {
        super(movieList);
    }

    // MODIFIES: this
    // EFFECTS: updates the combobox to match the current movie list
    public void updateComboBox(MovieList movieList) {
        super.getComboBox().removeAllItems();

        for (Map.Entry<Integer, Movie> entry : movieList.getMovieList().entrySet()) {
            String m = entry.getValue().getName();

            super.getComboBox().addItem(m);
        }
    }
}
