package model;

import java.util.*;

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
        this.status = status;
        this.name = name;
        this.genre = genre;
        this.notes = new ArrayList<>();
        this.rating = 0;
        this.watchTime = 0;
    }

    // REQUIRES: length of newNote > 0
    // MODIFIES: this
    // EFFECTS: adds a new note to the list of notes
    public void addNote(String newNote) {
        this.notes.add(newNote);
    }

    // REQUIRES: noteNum >= 1 AND noteNum <= notes.size() AND notes.size() > 0
    // MODIFIES: this
    // EFFECTS: removes the note that matches the given number
    public void removeNote(int noteNum) {
        this.notes.remove(noteNum - 1);
    }

    // REQUIRES: toAdd > 0
    // MODIFIES: this
    // EFFECTS: adds more watch time to the current watch time
    public void addWatchTime(int toAdd) {
        this.watchTime += toAdd;
    }

    public String getStatus() {
        return this.status;
    }
    
    public String getName() {
        return this.name;
    }

    public String getGenre() {
        return this.genre;
    }

    public String getNotes() {
        String result = "";
        int count = 

        for (String s : this.notes) {
            result = result + s + "\n";
        }

        return result;
    }

    public int getRating() {
        return this.rating; // stub
    }

    public int getWatchTime() {
        return this.watchTime; // stub
    }

    // REQUIRES: status must be one of: "Watched", "Currently Watching", or "To-watch"
    public void setStatus(String status) {
        this.status = status;
    }

    // REQUIRES: length of name > 0
    public void setName(String name) {
        this.name = name;
    }

    // REQUIRES: length of genre > 0
    public void setGenre(String genre) {
        this.genre = genre;
    }

    // REQUIRES: rating >= 0 AND rating <= 5
    public void setRating(int rating) {
        this.rating = rating;
    }
}
