package ui.graphics.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import model.*;

// Represents a panel to remove movies
public class RemovePanel extends DisplayPanel {
    // EFFECTS: creates a new RemovePanel
    public RemovePanel(MovieList movieList) {
        super(movieList);
        setupPanel();
    }

    // MODIFIES: this
    // EFFECTS: sets up the panel
    public void setupPanel() {
        super.getButton().addActionListener(this);
        super.getButton().setText("Remove movie");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Movies:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(super.getComboBox(), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(super.getButton(), gbc);
    }
    
    // MODIFIES: this
    // EFFECTS: handles actions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == super.getButton()) {
            if (super.getMovieList().getMovieList().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Empty movie list, cannot remove movie", 
                                            "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int selectedNum = getMovieNumber();
                super.getMovieList().removeMovie(selectedNum);
                JOptionPane.showMessageDialog(this, super.getComboBox().getSelectedItem().toString() + " was removed", 
                                            "Remove", JOptionPane.INFORMATION_MESSAGE);
                updateComboBox(super.getMovieList());
            }
        }
    }
}
