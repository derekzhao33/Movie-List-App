package ui.graphics.panels;

import java.util.Map;

import model.Movie;
import model.MovieList;

// Represents a panel for a display
public abstract class DisplayPanel extends MoviePanel {

    // EFFECTS: creates a new DisplayPanel
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

    public int getMovieNumber() {
        int selectedNum = -1;

        for (Map.Entry<Integer, Movie> entry : super.getMovieList().getMovieList().entrySet()) {
            int n = entry.getKey();
            Movie m = entry.getValue();

            if (m.getName().equals(super.getComboBox().getSelectedItem().toString())) {
                selectedNum = n;
            }
        }

        return selectedNum;
    }
}
