package ui.graphics.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

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
        statusComboBox.setPreferredSize(COMBO_BOX_DIMENSION);
        statusComboBox.setFont(FONT);
        add(statusComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel nameLabel = new JLabel("New name:");
        nameLabel.setFont(FONT_BOLD);
        add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        nameField.setPreferredSize(DIMENSION);
        add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel genreLabel = new JLabel("New genre:");
        genreLabel.setFont(FONT_BOLD);
        add(genreLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        genreField.setPreferredSize(DIMENSION);
        add(genreField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel noteLabel = new JLabel("Add note:");
        noteLabel.setFont(FONT_BOLD);
        add(noteLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        noteField.setPreferredSize(DIMENSION);
        add(noteField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel ratingLabel = new JLabel("New rating:");
        ratingLabel.setFont(FONT_BOLD);
        add(ratingLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        ratingField.setPreferredSize(DIMENSION);
        add(ratingField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        JLabel watchTimeLabel = new JLabel("Watch time:");
        watchTimeLabel.setFont(FONT_BOLD);
        add(watchTimeLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        watchTimeField.setPreferredSize(DIMENSION);
        add(watchTimeField, gbc);
        
        actionButton.addActionListener(this);
        actionButton.setText("Change movie");
        gbc.gridx = 1;
        gbc.gridy = 7;
        add(actionButton, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
