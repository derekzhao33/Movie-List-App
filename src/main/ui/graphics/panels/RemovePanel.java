package ui.graphics.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;

import model.*;

// Represents a panel to remove movies
public class RemovePanel extends DisplayPanel {
    private JButton removeMovieButton;

    // EFFECTS: creates a new RemovePanel
    public RemovePanel(MovieList movieList) {
        super(movieList);
        this.removeMovieButton = new JButton("Remove Movie");
        setupPanel();
    }

    // MODIFIES: this
    // EFFECTS: sets up all the movie options
    public void setupPanel() {
        this.removeMovieButton.addActionListener(this);
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
        add(removeMovieButton, gbc);
    }
    
    // MODIFIES: this
    // EFFECTS: handles actions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.removeMovieButton) {
            System.out.println(super.getMovieList().getAllNames());

            if (super.getMovieList().getMovieList().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Empty movie list, cannot remove movie", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int selectedNum = -1;

                for (Map.Entry<Integer, Movie> entry : super.getMovieList().getMovieList().entrySet()) {
                    int n = entry.getKey();
                    Movie m = entry.getValue();

                    if (m.getName().equals(super.getComboBox().getSelectedItem().toString())) {
                        selectedNum = n;
                    }
                }

                super.getMovieList().removeMovie(selectedNum);
                JOptionPane.showMessageDialog(this, super.getComboBox().getSelectedItem().toString() + " was removed", "Remove", JOptionPane.INFORMATION_MESSAGE);
                updateComboBox(super.getMovieList());
            }
        }
    }
}
