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
    // EFFECTS: updates panels when movies are removed
    public void updatePanels() {
        update(movieList);
        UpdateHandler.getInstance().updatePanelsForRemoving(movieList);
    }

    // MODIFIES: this
    // EFFECTS: handles actions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == super.getButton()) {
            MovieList movies = super.getMovieList();

            if (movies.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Empty movie list, cannot remove movie", 
                                            "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int selectedNum = getMovieNumber();
                Object comboBoxItem = comboBox.getSelectedItem();
                String movieName = comboBoxItem.toString();

                movies.removeMovie(selectedNum);
                JOptionPane.showMessageDialog(this, movieName + " was removed", 
                                            "Remove", JOptionPane.INFORMATION_MESSAGE);

                updatePanels();
            }
        }
    }
    
    @Override
    public void update(MovieList movieList) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}
