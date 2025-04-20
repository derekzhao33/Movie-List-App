package ui.graphics.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import model.*;

// Represents a panel to remove movies
public class RemovePanel extends DisplayPanel {
    private DisplayInfoPanel displayInfoPanel;
    private FilterGenrePanel filterGenrePanel;

    // EFFECTS: creates a new RemovePanel
    public RemovePanel(MovieList movieList) {
        super(movieList);
        setupPanel();
    }

    // MODIFIES: this
    // EFFECTS: sets up the panel
    @Override
    public void setupPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel label = new JLabel("Movies:");
        label.setFont(FONT_BOLD);
        add(label, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        comboBox.setPreferredSize(DIMENSION);
        add(comboBox, gbc);

        actionButton.addActionListener(this);
        actionButton.setText("Remove movie");
        gbc.gridx = 1;
        gbc.gridy = 1;
        actionButton.setPreferredSize(DIMENSION);
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
