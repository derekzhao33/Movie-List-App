package ui.graphics.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import model.MovieList;
import ui.graphics.panels.observer.Observer;

public class ChangePanel extends DisplayPanel {

    public ChangePanel(MovieList movieList) {
        super(movieList);
        setupPanel();
    }

    @Override
    public void setupPanel() {
        actionButton.addActionListener(this);
        actionButton.setText("Change movie");
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

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
