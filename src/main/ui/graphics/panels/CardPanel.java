package ui.graphics.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.*;

// Represents switchable panels for the movie list application
public class CardPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel contPanel;
    private JPanel startPanel;
    private AddPanel addPanel;
    private JPanel removePanel;
    private JPanel displayPanel;

    // EFFECTS: creates a new CardPanel
    public CardPanel(MovieList movieList) {
        this.cardLayout = new CardLayout();
        this.contPanel = new JPanel(cardLayout);
        this.startPanel = new JPanel();
        this.addPanel = new AddPanel(movieList);
        this.removePanel = new JPanel();
        this.displayPanel = new JPanel(); 

        addPanels();
        this.setLayout(new BorderLayout());
        this.add(contPanel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: adds panels to the card layout
    public void addPanels() {
        contPanel.add(startPanel, "start");
        contPanel.add(addPanel, "add");
        contPanel.add(removePanel, "remove");
        contPanel.add(displayPanel, "display");
        cardLayout.show(contPanel, "start");
    }

    // MODIFIES: this
    // EFFECTS: switches panels
    public void switchPanels(String panel, MovieList movieList) {
        if (panel.equals("add")) {
            cardLayout.show(contPanel, "add");
        } else if (panel.equals("remove")) {
            cardLayout.show(contPanel, "remove");
        } else if (panel.equals("display")) {
            cardLayout.show(contPanel, "display");
        }
    }
}
