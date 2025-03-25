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
    private DisplayInfoPanel displayInfoPanel;
    private FilterStatusPanel filterStatusPanel;
    private FilterGenrePanel filterGenrePanel;

    // EFFECTS: creates a new CardPanel
    public CardPanel(MovieList movieList) {
        this.cardLayout = new CardLayout();
        this.mainPanel = new JPanel(cardLayout);
        this.startPanel = new StartPanel();
        this.removePanel = new RemovePanel(movieList);
        this.displayInfoPanel = new DisplayInfoPanel(movieList);
        this.filterStatusPanel = new FilterStatusPanel(movieList);
        this.filterGenrePanel = new FilterGenrePanel(movieList);
        this.addPanel = new AddPanel(movieList, this.removePanel, this.displayInfoPanel,
                                        this.filterGenrePanel);

        addPanels();
        cardLayout.show(mainPanel, "start");
        this.add(mainPanel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: adds panels to the card layout
    public void addPanels() {
        this.mainPanel.add(this.startPanel, "start");
        this.mainPanel.add(this.addPanel, "add");
        this.mainPanel.add(this.removePanel, "remove");
        this.mainPanel.add(this.displayInfoPanel, "display");
        this.mainPanel.add(this.filterStatusPanel, "filter status");
        this.mainPanel.add(this.filterGenrePanel, "filter genre");
    }

    // MODIFIES: this
    // EFFECTS: switches panels
    public void switchPanels(String panel, MovieList movieList) {
        this.displayInfoPanel.resetInfoArea();
        this.filterGenrePanel.resetInfoArea();
        this.filterStatusPanel.resetInfoArea();
        this.cardLayout.show(mainPanel, panel);
    }

    public void updatePanels(MovieList movieList) {
        this.removePanel.setMovieList(movieList);
        this.removePanel.updateComboBox(movieList);
        this.displayInfoPanel.setMovieList(movieList);
        this.displayInfoPanel.updateComboBox(movieList);
        this.addPanel.setMovieList(movieList);
        this.filterStatusPanel.setMovieList(movieList);
        this.filterGenrePanel.setMovieList(movieList);
        this.filterGenrePanel.updateComboBox(movieList);
    }
}
