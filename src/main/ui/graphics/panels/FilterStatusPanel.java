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
        super.getComboBox().addItem("Watched");
        super.getComboBox().addItem("Currently Watching");
        super.getComboBox().addItem("To-watch");
        setupPanel("Filter Movies", "Status:");
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
                String longStatus = comboBoxItem.toString();

                String status = super.getMovieStatusShortenedString(longStatus);
                String filterText = movies.getAllNamesByStatus(status);
                super.getInfoArea().setText(filterText);
            }
        }
    }
}
