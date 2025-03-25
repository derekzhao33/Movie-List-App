package ui.graphics.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import model.*;

// Represents a panel to display movie info
public class DisplayInfoPanel extends DisplayPanel {
    private JTextArea infoArea;
    private JScrollPane scrollPane;

    // EFFECTS: creates a new DisplayInfoPanel
    public DisplayInfoPanel(MovieList movieList) {
        super(movieList);
        this.infoArea = new JTextArea(3, 7);
        this.scrollPane = new JScrollPane(this.infoArea);
        setupPanel();
    }

    // MODIFIES: this
    // EFFECTS: resets the info area
    public void resetInfoArea() {
        this.infoArea.setText("");
    }

    // MODIFIES: this
    // EFFECTS: sets up the panel
    @SuppressWarnings("methodlength")
    public void setupPanel() {
        super.getButton().addActionListener(this);
        super.getButton().setText("Display info");

        this.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.scrollPane.setViewportView(infoArea);

        this.infoArea.setEditable(false);

        super.getComboBox().setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Movies:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        add(super.getComboBox(), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0;
        add(super.getButton(), gbc);

        gbc.gridwidth = 0;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(this.scrollPane, gbc);
    }

    // MODIFIES: this
    // EFFECTS: handles actions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == super.getButton()) {
            if (super.getMovieList().getMovieList().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Empty movie list, cannot display info", 
                                            "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int selectedNum = getMovieNumber();
                Movie selectedMovie = super.getMovieList().searchName(selectedNum);
                this.infoArea.setText(selectedMovie.toString());
                updateComboBox(super.getMovieList());
            }
        } 
    }
}
