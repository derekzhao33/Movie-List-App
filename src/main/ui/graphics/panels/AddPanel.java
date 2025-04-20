package ui.graphics.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import model.*;

// Represents a panel for adding movies
public class AddPanel extends MovieListPanel {
    private JTextField nameField;
    private JTextField genreField;
    private RemovePanel removePanel;
    private DisplayInfoPanel displayInfoPanel;
    private FilterGenrePanel filterGenrePanel;

    // EFFECTS: creates a new AddPanel
    public AddPanel(MovieList movieList, RemovePanel removePanel, DisplayInfoPanel displayInfoPanel, FilterGenrePanel filterGenrePanel) {
        super(movieList);   
        comboBox.addItem("Watched");
        comboBox.addItem("Currently Watching");
        comboBox.addItem("To-watch");
        nameField = new JTextField();
        genreField = new JTextField();
        
        addObserver(removePanel);
        addObserver(displayInfoPanel);
        addObserver(filterGenrePanel);
        
        setupPanel();
    }

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
        nameField.setPreferredSize(DIMENSION);
        nameField.setFont(FONT);
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
        add(actionButton, gbc);
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

                movieList.addMovie(newMovie);
                notifyObservers(movieList);
                JOptionPane.showMessageDialog(this, name + " was added", "Add", JOptionPane.INFORMATION_MESSAGE);
                nameField.setText("");
                genreField.setText("");
            }
        } 
    }
}
