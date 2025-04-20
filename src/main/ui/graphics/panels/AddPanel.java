package ui.graphics.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import model.*;

// Represents a panel for adding movies
public class AddPanel extends MovieListPanel {
    private JTextField nameField;
    private JTextField genreField;

    // EFFECTS: creates a new AddPanel
    public AddPanel(MovieList movieList) {
        super(movieList);   
        comboBox.addItem("Watched");
        comboBox.addItem("Currently Watching");
        comboBox.addItem("To-watch");
        nameField = new JTextField();
        genreField = new JTextField();
        setupPanel();
    }

    @SuppressWarnings("methodlength")
    // MODIFIES: this
    // EFFECTS: sets up the panel
    public void setupPanel() {
        actionButton.addActionListener(this);
        actionButton.setText("Add movie");

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        
    
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setFont(FONT_BOLD);
        add(statusLabel, gbc);
    
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(comboBox, gbc);
    
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(FONT_BOLD);
        add(nameLabel, gbc);
    
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.nameField.setPreferredSize(DIMENSION);
        this.nameField.setFont(FONT);
        add(nameField, gbc);
    
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel genreLabel = new JLabel("Genre:");
        genreLabel.setFont(FONT_BOLD);
        add(genreLabel, gbc);
    
        gbc.gridx = 1;
        gbc.gridy = 2;
        genreField.setPreferredSize(DIMENSION);
        genreField.setFont(FONT);
        add(genreField, gbc);
    
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(comboBox, gbc);
    }

    // MODIFIES: this
    // EFFECTS: updates panels when movies are added
    public void updatePanels(Movie movie) {
        movieList.addMovie(movie);
        UpdateHandler.getInstance().updatePanelsForAdding(movieList);
    }

    // MODIFIES: this
    // EFFECTS: handles actions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == actionButton) {
            Object comboBoxItem = comboBox.getSelectedItem();
            String longStatus = comboBoxItem.toString();
            String status = getMovieStatusShortenedString(longStatus);
            String name = nameField.getText();
            String genre = genreField.getText();

            if (name.equals("") || genre.equals("")) {
                JOptionPane.showMessageDialog(this, "Invalid name or genre entered", 
                                            "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Movie newMovie = new Movie(status, name, genre);

                updatePanels(newMovie);
                JOptionPane.showMessageDialog(this, name + " was added", "Add", JOptionPane.INFORMATION_MESSAGE);
                nameField.setText("");
                genreField.setText("");
            }
        }
    }
}
