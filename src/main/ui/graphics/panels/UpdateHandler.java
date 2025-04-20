package ui.graphics.panels;

import model.Movie;
import model.MovieList;
import ui.graphics.panels.observer.Observer;

// Represents a class that handles panel updates
public class UpdateHandler implements Observer {
    private static UpdateHandler singleton;
    private RemovePanel removePanel;
    private DisplayInfoPanel displayInfoPanel;
    private FilterGenrePanel filterGenrePanel;

    // EFFECTS: creates a new UpdateHandler
    private UpdateHandler() {

    }

    public static UpdateHandler getInstance() {
        if (singleton == null) {
            singleton = new UpdateHandler();
        }

        return singleton;
    }


    // MODIFIES: this
    // EFFECTS: updates panels when adding movies
    public void updatePanelsForAdding(MovieList movieList) {
        removePanel.update(movieList);
        displayInfoPanel.update(movieList);
        filterGenrePanel.update(movieList);
    }

    // MODIFIES: this
    // EFFECTS: updates panels when remove movies
    public void updatePanelsForRemoving(MovieList movieList) {
        displayInfoPanel.update(movieList);
        filterGenrePanel.update(movieList);
    }

    public void setRemovePanel(RemovePanel removePanel) {
        this.removePanel = removePanel;
    }

    public void setDisplayInfoPanel(DisplayInfoPanel displayInfoPanel) {
        this.displayInfoPanel = displayInfoPanel;
    }

    public void setFilterGenrePanel(FilterGenrePanel filterGenrePanel) {
        this.filterGenrePanel = filterGenrePanel;
    }

    @Override
    public void update(MovieList movieList) {
        
    }
}
