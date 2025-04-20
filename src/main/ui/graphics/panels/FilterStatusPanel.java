package ui.graphics.panels;

import javax.swing.*;
import java.awt.event.*;

import model.*;

// Represents a panel that filters by status
public class FilterStatusPanel extends DisplayInfoPanel {

    // EFFECTS: creates a new FilterStatusPanel
    public FilterStatusPanel(MovieList movieList) {
        super(movieList);
    }

    // MODIFIES: this
    // EFFECTS: sets up the panel
    @Override
    public void setupPanel() {
        comboBox.addItem("Watched");
        comboBox.addItem("Currently Watching");
        comboBox.addItem("To-watch");
        setupPanel("Filter Movies", "Status:");
    }

    //EFFECTS: handles the actions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == actionButton) {
            MovieList movies = movieList;

            if (movies.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Empty movie list, cannot display info", 
                                            "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Object comboBoxItem = comboBox.getSelectedItem();
                String longStatus = comboBoxItem.toString();

                String status = getMovieStatusShortenedString(longStatus);
                String filterText = movies.getAllNamesByStatus(status);
                infoArea.setText(filterText);
            }
        }
    }
}
