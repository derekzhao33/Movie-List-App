package ui.graphics.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import model.*;

// Represents a panel for adding movies
public class AddPanel extends MoviePanel {
    private JTextField nameField;
    private JTextField genreField;
    private RemovePanel removePanel;
    private DisplayInfoPanel displayInfoPanel;

    // EFFECTS: creates a new AddPanel
    public AddPanel(MovieList movieList, RemovePanel removePanel, DisplayInfoPanel displayInfoPanel) {
        super(movieList);   
        super.getComboBox().addItem("Watched");
        super.getComboBox().addItem("Currently Watching");
        super.getComboBox().addItem("To-watch");
        this.nameField = new JTextField();
        this.genreField = new JTextField();
        this.removePanel = removePanel;
        this.displayInfoPanel = displayInfoPanel;
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
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Status:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(super.getComboBox(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Name:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(this.nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Genre:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(this.genreField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(super.getButton(), gbc);
    }

    // MODIFIES: this
    // EFFECTS: updates the removePanel when movies are added
    public void updatePanels(Movie movie) {
        super.getMovieList().addMovie(movie);
        this.removePanel.updateComboBox(super.getMovieList());
        this.displayInfoPanel.updateComboBox(super.getMovieList());
    }
    
    // REQUIRES: status is one of: "Watched", "Currently Watching", "To-watch"
    // MODIFIES: this
    // EFFECTS: returns the shortened status    
    public String getMovieStatusShortenedString(String status) {
        if (status.equals("Watched")) {
            return "w";
        } else if (status.equals("Currently Watching")) {
            return "c";
        } else if (status.equals("To-watch")) {
            return "t";
        } 

        return "";
    }

    // MODIFIES: this
    // EFFECTS: handles actions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == super.getButton()) {
            if (this.nameField.getText().equals("") || this.genreField.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Invalid name or genre entered", 
                                            "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String status = getMovieStatusShortenedString(super.getComboBox().getSelectedItem().toString());
                String name = this.nameField.getText();
                String genre = this.genreField.getText();
                Movie newMovie = new Movie(status, name, genre);

                updatePanels(newMovie);
                JOptionPane.showMessageDialog(this, name + " was added", "Add", JOptionPane.INFORMATION_MESSAGE);
                this.nameField.setText("");
                this.genreField.setText("");
            }
        }
    }
}
