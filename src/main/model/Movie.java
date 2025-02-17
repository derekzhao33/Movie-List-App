package model;

import java.util.*;

import javax.naming.LinkException;

// Represents a movie. Each movie's status is: Watched (w), Currently Watching (c), or To-watch (t).
// Each movie has a genre, a total watch time in minutes, notes, and a review. 
public class Movie extends MediaItem {

    // REQUIRES: length of name and genre > 0 AND status must be one of: "w", "c", or "t"
    // EFFECTS: creates a Movie with status, name, genre, empty notes, no rating, and zero minutes watchTime
    public Movie(String status, String name, String genre) {
        super(status, name, genre);
    }
}
