package ui.graphics.panels;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.MovieList;

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
    private ChangePanel changePanel;
    private JScrollPane changeScrollPane;

    // EFFECTS: creates a new CardPanel
    public CardPanel(MovieList movieList) {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(this.cardLayout);
        startPanel = new StartPanel();
        displayInfoPanel = new DisplayInfoPanel(movieList);
        filterStatusPanel = new FilterStatusPanel(movieList);
        filterGenrePanel = new FilterGenrePanel(movieList);
        removePanel = new RemovePanel(movieList);
        addPanel = new AddPanel(movieList);
        changePanel = new ChangePanel(movieList);
        changeScrollPane = new JScrollPane(changePanel);

        addObserverstoPanels();
        addPanels();
        cardLayout.show(mainPanel, "start");
        mainPanel.setPreferredSize(new Dimension(380, 200));
        add(mainPanel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: adds panels to the card layout
    public void addPanels() {
        mainPanel.add(startPanel, "start");
        mainPanel.add(addPanel, "add");
        mainPanel.add(removePanel, "remove");
        mainPanel.add(displayInfoPanel, "display");
        mainPanel.add(filterStatusPanel, "filter status");
        mainPanel.add(filterGenrePanel, "filter genre");
        mainPanel.add(changeScrollPane, "change");
    }

    // MODIFIES: this
    // EFFECTS: switches panels
    public void switchPanels(String panel, MovieList movieList) {
        displayInfoPanel.resetInfoArea();
        filterGenrePanel.resetInfoArea();
        filterStatusPanel.resetInfoArea();

        cardLayout.show(mainPanel, panel);
    }

    // MODIFIES: this   
    // EFFECTS: sets up combo boxes and movieLists
    public void initializePanels(MovieList movieList) {
        addPanel.setMovieList(movieList);
        removePanel.setMovieList(movieList);
        displayInfoPanel.setMovieList(movieList);
        filterStatusPanel.setMovieList(movieList);
        filterGenrePanel.setMovieList(movieList);
        
        removePanel.update(movieList);
        displayInfoPanel.update(movieList);
        filterGenrePanel.update(movieList);
        changePanel.update(movieList);
    }

    public void addObserverstoPanels() {
        removePanel.addObserver(removePanel);
        removePanel.addObserver(displayInfoPanel);
        removePanel.addObserver(filterGenrePanel);
        removePanel.addObserver(changePanel);

        addPanel.addObserver(removePanel);
        addPanel.addObserver(displayInfoPanel);
        addPanel.addObserver(filterGenrePanel);
        addPanel.addObserver(changePanel);

        changePanel.addObserver(displayInfoPanel);
        changePanel.addObserver(filterGenrePanel);
    }
}
