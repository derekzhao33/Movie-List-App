package model;

import java.util.*;

import javax.naming.LinkException;

// Represents a movie. Each movie's status is: Watched (w), Currently Watching (c), or To-watch (t).
// Each movie has a genre, a total watch time in minutes, notes, and a review. 
public class Movie {

    private String status;
    private String name;
    private String genre;
    private LinkedHashMap<Integer, String> notes;
    private int rating;
    private int watchTime;

    // REQUIRES: length of name and genre > 0 AND status must be one of: "w", "c", or "t"
    // EFFECTS: creates a MediaItem with status, name, genre, empty notes, no rating, and zero minutes watchTime
    public Movie(String status, String name, String genre) {
        this.status = status;
        this.name = name;
        this.genre = genre;
        this.notes = new LinkedHashMap<>();
        this.rating = 0;
        this.watchTime = 0;
    }

    // REQUIRES: length of newNote > 0
    // MODIFIES: this
    // EFFECTS: adds a new note to the list of notes
    public void addNote(String newNote) {
        this.notes.put(this.notes.size() + 1, newNote);
    }

    // REQUIRES: noteNum >= 1 AND noteNum <= notes.size() AND notes.size() > 0
    // MODIFIES: this
    // EFFECTS: removes the note that matches the given number and reasigns the keys to match the new list
    public void removeNote(int noteNum) {
        this.notes.remove(noteNum);

        LinkedHashMap<Integer, String> lhmNew = new LinkedHashMap<>();
        int i = 1;

        for (Map.Entry<Integer, String> mapElement : this.notes.entrySet()) {
            String v = mapElement.getValue();

            lhmNew.put(i, v);

            i++;
        }

        this.notes = lhmNew;
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

        for (Map.Entry<Integer, String> mapElement : this.notes.entrySet()) {
            Integer k = mapElement.getKey();

            String v = mapElement.getValue();

            result = result + k + ": " + v + "\n";
        }

        return result;
    }

    public int getRating() {
        return this.rating; // stub
    }

    public int getWatchTime() {
        return this.watchTime; // stub
    }

    // REQUIRES: status must be one of: "w", "c", or "t"
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

    // EFFECTS: clones the movie
    @Override
    public Movie clone() {

    }
}
