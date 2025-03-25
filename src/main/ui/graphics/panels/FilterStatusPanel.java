package ui.graphics.panels;

import javax.swing.*;
import java.awt.event.*;
import java.util.LinkedHashMap;

import model.*;

// Represents a panel that filters by status
// TODO: add one for genre
public class FilterStatusPanel extends DisplayInfoPanel {

    // EFFECTS: creates a new FilterPanel
    public FilterStatusPanel(MovieList movieList) {
        super(movieList);
    }

    // MODIFIES: this
    // EFFECTS: sets up the panel
    @Override
    public void setupPanel() {
        super.getComboBox().addItem("Watched");
        super.getComboBox().addItem("Currently Watching");
        super.getComboBox().addItem("To-watch");
        setupPanel("Filter Movies", "Filter by:");
    }

    //EFFECTS: handles the actions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == super.getButton()) {
            LinkedHashMap<Integer, Movie> movies = super.getMovieList().getMovieList();

            if (movies.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Empty movie list, cannot display info", 
                                            "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Object comboBoxItem = super.getComboBox().getSelectedItem();
                String itemName = comboBoxItem.toString();

                String status = super.getMovieStatusShortenedString(itemName);
                String filterText = this.getMovieList().getAllNamesByGenre(status);
                super.getInfoArea().setText(filterText);
            }
        }
    }
}
