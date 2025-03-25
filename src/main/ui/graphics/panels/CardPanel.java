package ui.graphics.panels;

import javax.swing.*;
import java.awt.*;

import model.*;

// Represents switchable panels for the movie list application
public class CardPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private StartPanel startPanel;
    private AddPanel addPanel;
    private RemovePanel removePanel;
    private JPanel displayInfoPanel;

    // EFFECTS: creates a new CardPanel
    public CardPanel(MovieList movieList) {
        this.cardLayout = new CardLayout();
        this.mainPanel = new JPanel(cardLayout);
        this.startPanel = new StartPanel();
        this.removePanel = new RemovePanel(movieList);
        this.addPanel = new AddPanel(movieList, removePanel);
        this.displayInfoPanel = new JPanel(); 

        addPanels();
        cardLayout.show(mainPanel, "start");
        this.add(mainPanel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: adds panels to the card layout
    public void addPanels() {
        mainPanel.add(startPanel, "start");
        mainPanel.add(addPanel, "add");
        mainPanel.add(removePanel, "remove");
        mainPanel.add(displayInfoPanel, "display");
    }

    // MODIFIES: this
    // EFFECTS: switches panels
    public void switchPanels(String panel, MovieList movieList) {
        if (panel.equals("add")) {
            cardLayout.show(mainPanel, "add");
        } else if (panel.equals("remove")) {
            cardLayout.show(mainPanel, "remove");
        } else if (panel.equals("display")) {
            cardLayout.show(mainPanel, "display");
        } else {
            cardLayout.show(mainPanel, "start");
        }
    }

    public void updatePanels(MovieList movieList) {
        this.removePanel.updateComboBox(movieList);
    }
}
