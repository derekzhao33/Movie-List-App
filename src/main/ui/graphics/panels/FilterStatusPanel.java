package ui.graphics.panels;

import javax.swing.*;
import java.awt.event.*;
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
        String filterText = "";

        if (e.getSource() == super.getButton()) {
            if (super.getMovieList().getMovieList().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Empty movie list, cannot display info", 
                                            "Error", JOptionPane.ERROR_MESSAGE);
            } else if (super.getComboBox().getSelectedItem().equals("Watched")) {
                filterText = super.getMovieList().getAllNamesByGenre("w");
                super.getInfoArea().setText(filterText);
            } else if (super.getComboBox().getSelectedItem().equals("Currently Watching")) {
                filterText = super.getMovieList().getAllNamesByGenre("c");
                super.getInfoArea().setText(filterText);
            } else if (super.getComboBox().getSelectedItem().equals("To-watch")) {
                filterText =  super.getMovieList().getAllNamesByGenre("t");
                super.getInfoArea().setText(filterText);
            }
        }
    }
}
