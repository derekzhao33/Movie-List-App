package ui.graphics.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Movie;
import model.MovieList;

public class ChangePanel extends DisplayPanel {
    private JTextField nameField;
    private JTextField genreField;
    private JTextField noteField;
    private JTextField ratingField;
    private JTextField watchTimeField;
    private JComboBox<String> statusComboBox; 

    public ChangePanel(MovieList movieList) {
        super(movieList);
        nameField = new JTextField();
        genreField = new JTextField();
        noteField = new JTextField();
        ratingField = new JTextField();
        watchTimeField = new JTextField();
        statusComboBox = new JComboBox<>();

        statusComboBox.addItem("Watched");
        statusComboBox.addItem("Currently Watching");
        statusComboBox.addItem("To-watch");

        setupPanel();
    }

    @Override
    public void setupPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.weighty = 1;

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel moviesLabel = new JLabel("Movie:");
        moviesLabel.setFont(FONT_BOLD);
        add(moviesLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(comboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel statusLabel = new JLabel("New status:");
        statusLabel.setFont(FONT_BOLD);
        add(statusLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        statusComboBox.setFont(FONT);
        statusComboBox.setPreferredSize(DIMENSION);
        add(statusComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel nameLabel = new JLabel("New name:");
        nameLabel.setFont(FONT_BOLD);
        add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        nameField.setPreferredSize(DIMENSION);
        nameField.setFont(FONT);
        add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel genreLabel = new JLabel("New genre:");
        genreLabel.setFont(FONT_BOLD);
        add(genreLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        genreField.setPreferredSize(DIMENSION);
        genreField.setFont(FONT);
        add(genreField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel noteLabel = new JLabel("Add note:");
        noteLabel.setFont(FONT_BOLD);
        add(noteLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        noteField.setPreferredSize(DIMENSION);
        noteField.setFont(FONT);
        add(noteField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel ratingLabel = new JLabel("New rating:");
        ratingLabel.setFont(FONT_BOLD);
        add(ratingLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        ratingField.setPreferredSize(DIMENSION);
        ratingField.setFont(FONT);
        add(ratingField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        JLabel watchTimeLabel = new JLabel("Watch time:");
        watchTimeLabel.setFont(FONT_BOLD);
        add(watchTimeLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        watchTimeField.setPreferredSize(DIMENSION);
        watchTimeField.setFont(FONT);
        add(watchTimeField, gbc);
        
        actionButton.addActionListener(this);
        actionButton.setText("Change movie");
        gbc.gridx = 1;
        gbc.gridy = 7;
        add(actionButton, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == actionButton) {
            Object comboBoxItem = statusComboBox.getSelectedItem();
            String longStatus = comboBoxItem.toString();
            String newStatus = getMovieStatusShortenedString(longStatus);
            String newName = nameField.getText();
            String newGenre = genreField.getText();
            String newNote = noteField.getText();
            String newRating = ratingField.getText();
            String newWatchTime = watchTimeField.getText();

            if (movieList.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Empty movie list, cannot change movie", 
                                            "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int selectedNum = getMovieNumber();
                Movie toBeChanged = movieList.searchName(selectedNum);
                String movieName = comboBox.getSelectedItem().toString();

                toBeChanged.setStatus(newStatus);
                
                if (!newName.isEmpty()) {
                    toBeChanged.setName(newName);
                }

                if (!newGenre.isEmpty()) {
                    toBeChanged.setGenre(newGenre);
                }

                if (!newNote.isEmpty()) {
                    toBeChanged.addNote(newNote);
                }

                if (!newRating.matches("(-?\\d+(\\.\\d+)?)?")) {
                    JOptionPane.showMessageDialog(this, "Invalid rating entered, please enter a value between 1-5", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!newRating.isEmpty()) {
                    toBeChanged.setRating(Integer.valueOf(newRating));
                }

                if (!newWatchTime.matches("(-?\\d+(\\.\\d+)?)?")) {
                    JOptionPane.showMessageDialog(this, "Invalid watch time entered, please enter a value between 1-5", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!newWatchTime.isEmpty()) {
                    toBeChanged.setRating(Integer.valueOf(newWatchTime));
                }

                resetPanel();
                notifyObservers(movieList);
                update(movieList);
                JOptionPane.showMessageDialog(this, movieName + " was changed", "Change", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public void resetPanel() {
        nameField.setText("");
        genreField.setText("");
        noteField.setText("");
        ratingField.setText("");
        watchTimeField.setText("");
    }
}
