package model;

import java.util.*;

// Represents a list of media (movies/shows)
public class MediaList {
    
    private LinkedHashMap<Integer, Movie> mediaList;

    // EFFECTS: creates a a new MediaList with no media
    public MediaList() {
        mediaList = new LinkedHashMap<>();
    }

    // MODIFIES: this
    // EFFECTS: adds media to the media list
    public void addMedia(Movie media) {
        this.mediaList.put(mediaList.size() + 1, media);
    }

    // REQUIRES: mediaNum >= 1 AND mediaNum <= mediaList.size() AND mediaList.size() > 0
    // MODIFIES: this
    // EFFECTS: removes the media that matches the given number
    public void removeMedia(int mediaNum) {
        this.mediaList.remove(mediaNum);
        
        LinkedHashMap<Integer, Movie> lhmNew = new LinkedHashMap<>();
        int i = 1;

        for (Map.Entry<Integer, Movie> mapEntry : this.mediaList.entrySet()) {
            Movie v = mapEntry.getValue();

            lhmNew.put(1, v);

            i++;
        }

        mediaList = lhmNew;
    }

    // EFFECTS: gets the names of all media on the list
    public String getAllNames() {
        String result = "";

        for (Map.Entry<Integer, Movie> mapEntry : this.mediaList.entrySet()) {
            Integer k = mapEntry.getKey();
            Movie v = mapEntry.getValue();

            result = result + k + ": " + v.getName() + "\n"; 
        }

        return result;
    }

    // REQUIRES: status is one of: "w", "c", "t"
    // EFFECTS: gets the names of all media on the list filered by status
    public String getAllNamesByStatus(String status) {
        String result = "";

        for (Map.Entry<Integer, Movie> mapEntry : this.mediaList.entrySet()) {
            Integer k = mapEntry.getKey();
            Movie v = mapEntry.getValue();

            if (v.getStatus().equals(status)) {
                result = result + k + ": " + v.getName() + "\n"; 
            }
        }

        return result;
    }

    // EFFECTS: gets the names of all media on the list filtered by genre
    public String getAllNamesByGenre(String genre) {
        String result = "";

        for (Map.Entry<Integer, Movie> mapEntry : this.mediaList.entrySet()) {
            Integer k = mapEntry.getKey();
            Movie v = mapEntry.getValue();

            if (v.getGenre().equals(genre)) {
                result = result + k + ": " + v.getName() + "\n"; 
            }
        }

        return result;
    }

    // REQUIRES: type is one of: "m" or "s"
    // EFFECTS: gets the names of all media on the list filtered by genre
    public String getAllNamesByType(String type) {
        String result = "";



        for (Map.Entry<Integer, Movie> mapEntry : this.mediaList.entrySet()) {
            Integer k = mapEntry.getKey();
            Movie v = mapEntry.getValue();

            if (type.equals("s")) {

            } else if (type.equals("m")) {

            }
        }

        return result;
    }
    
    // EFFECTS: gets the movie with the given name, if none is found, then null
    public Movie searchName(String name) {
        return new Movie("Watched", "test", "test"); // stub
    }

    // REQUIRES: movie must be in mediaList AND status must be one of "w", "c", "t"
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

    // EFFECTS: checks if the given object is a Show
    public boolean isShow(Movie media) {

    }

    // EFFECTS: checks if the given object is a Movie
    public boolean isMovie(Movie media) {

    }
}
