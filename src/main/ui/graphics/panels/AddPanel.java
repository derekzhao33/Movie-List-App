package ui.graphics.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import model.*;
import ui.graphics.panels.observer.Subject;

// Represents a panel for adding movies
public class AddPanel extends Subject {
    private JTextField nameField;
    private JTextField genreField;

    // EFFECTS: creates a new AddPanel
    public AddPanel(MovieList movieList) {
        super(movieList);   
        super.getComboBox().addItem("Watched");
        super.getComboBox().addItem("Currently Watching");
        super.getComboBox().addItem("To-watch");
        this.nameField = new JTextField();
        this.genreField = new JTextField();
        setupPanel();
    }

    @SuppressWarnings("methodlength")
    // MODIFIES: this
    // EFFECTS: sets up the panel
    public void setupPanel() {
        super.getButton().addActionListener(this);
        super.getButton().setText("Add movie");

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
        add(super.getComboBox(), gbc);
    
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(FONT_BOLD);
        add(nameLabel, gbc);
    
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.nameField.setPreferredSize(DIMENSION);
        this.nameField.setFont(FONT);
        add(this.nameField, gbc);
    
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel genreLabel = new JLabel("Genre:");
        genreLabel.setFont(FONT_BOLD);
        add(genreLabel, gbc);
    
        gbc.gridx = 1;
        gbc.gridy = 2;
        this.genreField.setPreferredSize(DIMENSION);
        this.genreField.setFont(FONT);
        add(this.genreField, gbc);
    
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(super.getButton(), gbc);
    }

    // MODIFIES: this
    // EFFECTS: updates panels when movies are added
    public void updatePanels(Movie movie) {
        super.getMovieList().addMovie(movie);
        UpdateHandler.getInstance().updatePanelsForAdding(movie, super.getMovieList());
    }

    // MODIFIES: this
    // EFFECTS: handles actions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == super.getButton()) {
            Object comboBoxItem = super.getComboBox().getSelectedItem();
            String longStatus = comboBoxItem.toString();
            String status = super.getMovieStatusShortenedString(longStatus);
            String name = this.nameField.getText();
            String genre = this.genreField.getText();

            if (name.equals("") || genre.equals("")) {
                JOptionPane.showMessageDialog(this, "Invalid name or genre entered", 
                                            "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Movie newMovie = new Movie(status, name, genre);

                updatePanels(newMovie);
                JOptionPane.showMessageDialog(this, name + " was added", "Add", JOptionPane.INFORMATION_MESSAGE);
                this.nameField.setText("");
                this.genreField.setText("");
            }
        }
    }
}
