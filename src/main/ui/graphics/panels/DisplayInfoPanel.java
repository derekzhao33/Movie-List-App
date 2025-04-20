package ui.graphics.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.*;

// Represents a panel that displays movie info
public class DisplayInfoPanel extends DisplayPanel {
    protected JTextArea infoArea;
    private JScrollPane scrollPane;

    // EFFECTS: creates a new DisplayInfoPanel
    public  DisplayInfoPanel(MovieList movieList) {
        super(movieList);
        infoArea = new JTextArea(3, 7);
        scrollPane = new JScrollPane(infoArea);
        setupPanel();
    }

    // MODIFIES: this
    // EFFECTS: resets the info area
    public void resetInfoArea() {
        infoArea.setText("");
    }

    // MODIFIES: this
    // EFFECTS: sets up the panel
    @Override
    public void setupPanel() {
        setupPanel("Display Info", "Movies:"); 
    }

    // MODIFIES: this
    // EFFECTS: sets up the panel
    @SuppressWarnings("methodlength")
    public void setupPanel(String buttonName, String labelName) {
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setViewportView(infoArea);

        infoArea.setEditable(false);

        comboBox.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel label = new JLabel(labelName);
        label.setFont(FONT_BOLD);
        add(label, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0;
        add(comboBox, gbc);

        actionButton.addActionListener(this);
        actionButton.setText(buttonName);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.5;
        add(actionButton, gbc);

        gbc.gridwidth = 0;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbc);
    }

    // MODIFIES: this
    // EFFECTS: handles actions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == actionButton) {
            MovieList movies = movieList;

            if (movies.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Empty movie list, cannot display info", 
                                            "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int selectedNum = getMovieNumber();
                Movie selectedMovie = movies.searchName(selectedNum);
                String infoText = selectedMovie.toString();
                infoArea.setText(infoText);
            }
        } 
    }
}
