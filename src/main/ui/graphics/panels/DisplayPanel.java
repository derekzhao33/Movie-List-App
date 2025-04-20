package ui.graphics.panels;

import java.util.LinkedHashMap;
import java.util.Map;

import model.Movie;
import model.MovieList;
import ui.graphics.panels.observer.Observer;

// Represents a panel that has a combo box for all movies
public abstract class DisplayPanel extends MovieListPanel implements Observer {

    // EFFECTS: creates a new DisplayPanel
    public DisplayPanel(MovieList movieList) {
        super(movieList);
    }

    // MODIFIES: this
    // EFFECTS: updates the combobox to match the current movie list
    public void updateComboBox(MovieList movieList) {
        comboBox.removeAllItems();

        for (Map.Entry<Integer, Movie> entry : movieList.getMovieList().entrySet()) {
            String m = entry.getValue().getName();

            comboBox.addItem(m);
        }
    }

    // EFFECTS: returns the number of the selected movie
    public int getMovieNumber() {
        int selectedNum = -1;

        LinkedHashMap<Integer, Movie> movies = movieList.getMovieList();

        for (Map.Entry<Integer, Movie> entry : movies.entrySet()) {
            int n = entry.getKey();
            Movie m = entry.getValue();
            String movieName = m.getName();

            Object comboBoxItem = comboBox.getSelectedItem();
            String itemName = comboBoxItem.toString();

            if (movieName.equals(itemName)) {
                selectedNum = n;
            }
        }

        return selectedNum;
    }
}
