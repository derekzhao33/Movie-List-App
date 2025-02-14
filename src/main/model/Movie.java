package model;

import java.util.ArrayList;

// Represents a movie. Each movie's status is: "Watched", "Currently Watching", or "To-watch".
// Each movie has a genre, a total watch time in minutes, notes, and a review. 
public class Movie {

    private String status;
    private String name;
    private String genre;
    private ArrayList<String> notes;
    private int rating;
    private int watchTime;

    // REQUIRES: length of name and genre > 0 AND status must be one of: "Watched", "Currently Watching", or "To-watch"
    // EFFECTS: creates a MediaItem with status, name, genre, empty notes, no rating, and zero minutes watchTime
    public Movie(String status, String name, String genre) {
        // TODO
    }

    // REQUIRES: length of newNote > 0
    // MODIFIES: this
    // EFFECTS: adds a new note to the list of notes
    public void addNote(String newNote) {
        // TODO
    }

    // REQUIRES: noteNum >= 1 AND noteNum <= notes.size() AND notes.size() > 0
    // MODIFIES: this
    // EFFECTS: removes the note that matches the given number
    public void removeNote(int noteNum) {
        // TODO
    }

    // REQUIRES: toAdd > 0
    // MODIFIES: this
    // EFFECTS: adds more watch time to the current watch time
    public void addWatchTime(int toAdd) {
        // TODO
    }

    public String getStatus() {
        return ""; // stub
    }
    
    public String getName() {
        return ""; // stub
    }

    public String getGenre() {
        return ""; // stub
    }

    public ArrayList<String> getNotes() {
        return new ArrayList<String>(); // stub 
    }

    public int getRating() {
        return -1; // stub
    }

    public int getWatchTime() {
        return -1; // stub
    }

    // REQUIRES: status must be one of: "Watched", "Currently Watching", or "To-watch"
    public void setStatus(String status) {
        // TODO
    }

    // REQUIRES: length of name > 0
    public void setName(String name) {
        // TODO
    }

    // REQUIRES: length of genre > 0
    public void setGenre(String genre) {
        // TODO
    }

    // REQUIRES: rating >= 0 AND rating <= 5
    public void setRating(int rating) {
        // TODO
    }
}
