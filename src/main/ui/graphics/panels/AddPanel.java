package ui.graphics.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.*;

// Represents a panel for adding movies
public class AddPanel extends JPanel implements ActionListener {
    private JComboBox comboBox;
    private JTextField nameField;
    private JTextField genreField;
    private JButton addMovieButton;
    private MovieList movieList;

    // EFFECTS: creates a new AddPanel
    public AddPanel(MovieList movieList) {
        String[] options = {"Watched", "Currently Watching", "To-watch"};

        this.comboBox = new JComboBox(options);
        this.nameField = new JTextField();
        this.genreField = new JTextField();
        this.addMovieButton = new JButton("Add Movie");
        this.movieList = movieList;
        setupAddPanel();
    }

    @SuppressWarnings("methodlength")
    // MODIFIES: this
    // EFFECTS: sets up the add panel
    public void setupAddPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Status:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(comboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Name:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Genre:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(genreField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(addMovieButton, gbc);
    }

    // MODIFIES: this
    // EFFECTS: handles actions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addMovieButton) {
            Movie newMovie = new Movie(comboBox.getSelectedItem().toString(), nameField.getText(), genreField.getText());
            JOptionPane.showMessageDialog(this, "Movie added", "Add", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
