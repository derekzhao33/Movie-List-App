package ui.graphics.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.nio.file.DirectoryStream.Filter;

import model.*;

// Represents a panel to remove movies
public class RemovePanel extends DisplayPanel {
    private DisplayInfoPanel displayInfoPanel;
    private FilterGenrePanel filterGenrePanel;

    // EFFECTS: creates a new RemovePanel
    public RemovePanel(MovieList movieList, DisplayInfoPanel displayInfoPanel, FilterGenrePanel filterGenrePanel) {
        super(movieList);
        setupPanel();

        addObserver(this);
        addObserver(displayInfoPanel);
        addObserver(filterGenrePanel);
    }

    // MODIFIES: this
    // EFFECTS: sets up the panel
    public void setupPanel() {
        actionButton.addActionListener(this);
        actionButton.setText("Remove movie");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel label = new JLabel("Movies:");
        label.setFont(FONT_BOLD);
        add(label, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(comboBox, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(actionButton, gbc);
    }

    // MODIFIES: this
    // EFFECTS: handles actions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == actionButton) {

            if (movieList.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Empty movie list, cannot remove movie", 
                                            "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int selectedNum = getMovieNumber();
                Object comboBoxItem = comboBox.getSelectedItem();
                String movieName = comboBoxItem.toString();

                movieList.removeMovie(selectedNum);
                notifyObservers(movieList);
                JOptionPane.showMessageDialog(this, movieName + " was removed", 
                                            "Remove", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
