package ui.graphics.panels;

import model.Movie;
import model.MovieList;

// Represents a class that handles panel updates
public class UpdateHandler {
    private RemovePanel removePanel;
    private DisplayInfoPanel displayInfoPanel;
    private FilterGenrePanel filterGenrePanel;

    // EFFECTS: creates a new UpdateHandler
    public UpdateHandler() {

    }

    // MODIFIES: this
    // EFFECTS: updates panels when adding movies
    public void updatePanelsForAdding(Movie movie, MovieList movieList) {
        this.removePanel.updateComboBox(movieList);
        this.displayInfoPanel.updateComboBox(movieList);
        this.filterGenrePanel.updateComboBox(movieList);
    }

    // MODIFIES: this
    // EFFECTS: updates panels when remove movies
    public void updatePanelsForRemoving(MovieList movieList) {
        this.displayInfoPanel.updateComboBox(movieList);
        this.filterGenrePanel.updateComboBox(movieList);
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
}
