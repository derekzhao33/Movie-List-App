package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.*;

// Represents a movie. Each movie's status is: Watched (w), Currently Watching (c), or To-watch (t).
// Each movie has a genre, a total watch time in minutes, notes, and a review. 
public class Movie implements Writable {

    private String status;
    private String name;
    private String genre;
    private LinkedHashMap<Integer, String> notes;
    private int rating;
    private int watchTime;

    // REQUIRES: length of name and genre > 0 AND status must be one of: "w", "c", or "t"
    // EFFECTS: creates a Movie with status, name, genre, empty notes, no rating, and zero minutes watchTime
    public Movie(String status, String name, String genre) {
        this.status = status;
        this.name = name;
        this.genre = genre;
        notes = new LinkedHashMap<>();
        rating = 0;
        watchTime = 0;
    }

    // REQUIRES: length of newNote > 0
    // MODIFIES: this
    // EFFECTS: adds a new note to the list of notes
    public void addNote(String newNote) {
        notes.put(notes.size() + 1, newNote);
    }

    // REQUIRES: noteNum >= 1 AND noteNum <= notes.size() AND notes.size() > 0
    // MODIFIES: this
    // EFFECTS: removes the note that matches the given number and reasigns the keys to match the new list
    public void removeNote(int noteNum) {
        notes.remove(noteNum);

        LinkedHashMap<Integer, String> lhmNew = new LinkedHashMap<>();
        int i = 1;

        for (Map.Entry<Integer, String> mapElement : notes.entrySet()) {
            String v = mapElement.getValue();

            lhmNew.put(i, v);

            i++;
        }

        notes = lhmNew;
    }

    // REQUIRES: toAdd > 0
    // MODIFIES: this
    // EFFECTS: adds more watch time to the current watch time
    public void addWatchTime(int toAdd) {
        watchTime += toAdd;
    }

    public String getStatus() {
        return status;
    }
    
    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    // EFFECTS: gets the all notes in string form 
    public String getNotes() {
        String result = "";

        for (Map.Entry<Integer, String> mapElement : notes.entrySet()) {
            Integer k = mapElement.getKey();

            String v = mapElement.getValue();

            result = result + "\n" + k + ": " + v;
        }

        return result;
    }

    public int getRating() {
        return rating; 
    }

    public int getWatchTime() {
        return watchTime;
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
    
    // EFFECTS: returns true if this is equal compared
    @Override
    public boolean equals(Object compared) {
        if (this == compared) {
            return true;
        }

        if (!(compared instanceof Movie)) {
            return false;
        }

        Movie comparedMovie = (Movie) compared;

        if (status.equals(comparedMovie.status)
                && name.equals(comparedMovie.name)
                && genre.equals(comparedMovie.genre)
                && notes.equals(comparedMovie.notes)
                && rating == comparedMovie.rating
                && watchTime == comparedMovie.watchTime) {
            return true;
        }

        return false;
    }

    // EFFECTS: returns this as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONObject notesJson = new JSONObject();
    
        for (Map.Entry<Integer, String> entry : notes.entrySet()) {
            notesJson.put(entry.getKey().toString(), entry.getValue());
        }
    
        json.put("status", status);
        json.put("name", name);
        json.put("genre", genre);
        json.put("notes", notesJson);
        json.put("rating", rating);
        json.put("watchTime", watchTime);
    
        return json;
    }

    // EFFECTS: returns the movie represented as a string, and adds an event 
    @Override
    public String toString() {
        EventLog.getInstance().logEvent(new Event("Displayed information for " + name));

        if (status.equals("w")) {
            return "Status: Watched\nName: " + name + "\nGenre: " + genre 
                        + "\nNotes: " + getNotes() + "\nRating: " + rating + "\nWatch Time: " + watchTime; 
        } else if (status.equals("c")) {
            return "Status: Currently Watching\nName: " + name + "\nGenre: " 
                        + genre + "\nNotes: " + getNotes() + "\nRating: " 
                        + rating + "\nWatch Time: " + watchTime; 
        } else {
            return "Status: To-watch\nName: " + name + "\nGenre: " 
                        + genre + "\nNotes:\n" + getNotes() + "\nRating: " 
                        + rating + "\nWatch Time: " + watchTime; 
        }
    }
}
