package ui.graphics.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import model.*;

// Represents a panel to remove movies
public class RemovePanel extends JPanel implements ActionListener {
    private JComboBox<String> comboBox;
    private JButton removeMovieButton;
    private MovieList movieList;

    // EFFECTS: creates a new RemovePanel
    public RemovePanel(MovieList movieList) {
        this.movieList = movieList;
        this.removeMovieButton = new JButton("Remove Movie");

        this.comboBox = new JComboBox<>();

        setupRemovePanel();
    }

    // MODIFIES: this
    // EFFECTS: sets up all the movie options
    public void setupRemovePanel() {
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
        add(this.comboBox, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(removeMovieButton, gbc);
    }

    // MODIFIES: this
    // EFFECTS: updates the combobox to match the current movie list
    public void updateComboBox() {
        this.comboBox.removeAllItems();

        for (Map.Entry<Integer, Movie> entry : this.movieList.getMovieList().entrySet()) {
            String m = entry.getValue().getName();

            this.comboBox.addItem(m);
        }
    }
    
    // MODIFIES: this
    // EFFECTS: handles actions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.removeMovieButton) {
            if (this.movieList.getMovieList().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Empty movie list, cannot remove movie", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int selectedNum = -1;

                for (Map.Entry<Integer, Movie> entry : this.movieList.getMovieList().entrySet()) {
                    int n = entry.getKey();
                    Movie m = entry.getValue();

                    if (m.getName().equals(this.comboBox.getSelectedItem().toString())) {
                        selectedNum = n;
                    }
                }

                this.movieList.removeMovie(selectedNum);
                JOptionPane.showMessageDialog(this, this.comboBox.getSelectedItem().toString() + " was removed", "Remove", JOptionPane.INFORMATION_MESSAGE);
                updateComboBox();
            }
        }
    }
}
