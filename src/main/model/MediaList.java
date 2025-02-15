package model;

import java.util.ArrayList;

// Represents a list of media (movies/shows)
public class MediaList {
    
    private ArrayList<Movie> mediaList;

    // EFFECTS: creates a a new MediaList with no media
    public MediaList() {
        mediaList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds media to the media list
    public void addMedia(Movie media) {
        // TODO
    }

    // REQUIRES: mediaNum >= 1 AND mediaNum <= mediaList.size() AND mediaList.size() > 0
    // MODIFIES: this
    // EFFECTS: removes the media that matches the given number
    public void removeMedia(int mediaNum) {
        // TODO       
    }

    // EFFECTS: gets the names of all media on the list
    @Override
    public String toString() {
        return ""; // stub
    }

    // EFFECTS: gets the names of all media on the list filered by status
    public String toStringByStatus(String status) {
        return ""; // stub
    }

    // EFFECTS: gets the names of all media on the list filtered by genre
    public String toStringByGenre(String genre) {
        return ""; // stub
    }

    // REQUIRES: type is one of: "Movie" or "Show"
    // EFFECTS: gets the names of all media on the list filtered by genre
    public String toStringByType(String type) {
        return ""; // stub
    }
    
    // EFFECTS: gets the movie with the given name, if none is found, then null
    public Movie searchName(String name) {
        return new Movie("Watched", "test", "test"); // stub
    }

    // REQUIRES: movie must be in mediaList AND newStatus must be one of "Watched", "Currently Watching", "To-watch"
    // MODIFIES: this
    // EFFECTS: changes the status of the specified movie in the lit
    public void changeStatus(Movie movie, String status) {
        // TODO
    }

    // REQUIRES: movie must be in mediaList AND newName length > 0
    // MODIFIES: this
    // EFFECTS: changes the name of the specified movie in the lit
    public void changeName(Movie movie, String name) {
        // TODO
    }

    // REQUIRES: movie must be in mediaList AND newGenre length > 0
    // MODIFIES: this
    // EFFECTS: changes the genre of the specified movie in the lit
    public void changeGenre(Movie movie, String genre) {
        // TODO
    }

    // REQUIRES: movie must be in mediaList AND newRating >- 1 AND newRating <= 5
    // MODIFIES: this
    // EFFECTS: changes the rating of the specified movie in the list
    public void changeRating(Movie movie, int rating) {
        // TODO
    }

    // REQUIRES: movie must be in mediaList AND watchTime > 0
    // MODIFIES: this
    // EFFECTS: adds watch time to the specified movie in the list
    public void addWatchTime(Movie movie, int watchTime) {
        // TODO
    }

    // REQUIRES: movie must be in mediaList AND newNote length > 0
    // MODIFIES: this
    // EFFECTS: adds a note to the specified movie in the lit
    public void addNote(Movie movie, String note) {
        // TODO
    }

    // REQUIRES: movie must be in mediaList
    // MODIFIES: this
    // EFFECTS: removes the note that matches the given number for the specified movie in the list
    public void removeNote(Movie movie, int noteNum) {
        // TODO
    }

    // REQUIRES: show must be in mediaList AND newEpisode > 0
    // MODIFIES: this
    // EFFECTS: changes the episode for the given show in mediaList
    public void changeEpisode(Show show, int episode) {
        // TODO
    }

    // REQUIRES: show must be in mediaList AND newSeason > 0
    // MODIFIES: this
    // EFFECTS: changes the season for the given show in mediaList
    public void changeSeason(Show show, int season) {
        // TODO
    }
}
