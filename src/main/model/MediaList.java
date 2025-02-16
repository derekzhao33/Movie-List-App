package model;

import java.util.*;

// Represents a list of media (movies/shows)
public class MediaList {
    
    private LinkedHashMap<Integer, Movie> mediaList;

    // EFFECTS: creates a a new MediaList with no media
    public MediaList() {
        mediaList = new LinkedHashMap<>();
    }

    // REQUIRES: every media item must have a different name
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

            lhmNew.put(i, v);

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
        int i = 1;

        for (Map.Entry<Integer, Movie> mapEntry : this.mediaList.entrySet()) {
            Movie v = mapEntry.getValue();

            if (v.getStatus().equals(status)) {
                result = result + i + ": " + v.getName() + "\n"; 
                i++;
            }
        }

        return result;
    }

    // EFFECTS: gets the names of all media on the list filtered by genre
    public String getAllNamesByGenre(String genre) {
        String result = "";
        int i = 1;

        for (Map.Entry<Integer, Movie> mapEntry : this.mediaList.entrySet()) {
            Movie v = mapEntry.getValue();

            if (v.getGenre().equals(genre)) {
                result = result + i + ": " + v.getName() + "\n"; 
                i++;
            }
        }

        return result;
    }

    // REQUIRES: type is one of: "m" or "s"
    // EFFECTS: gets the names of all media on the list filtered by genre
    public String getAllNamesByType(String type) {
        String result = "";
        int i = 1;

        if (type.equals("s")) {
            for (Map.Entry<Integer, Movie> mapEntry : this.mediaList.entrySet()) {
                Movie v = mapEntry.getValue();
                
                if (isShow(v)) {
                    result = result + i + ": " + v.getName() + "\n";
                    i++;
                }
            }

        } else {
            for (Map.Entry<Integer, Movie> mapEntry : this.mediaList.entrySet()) {
                Movie v = mapEntry.getValue();
                
                if (isMovie(v)) {
                    result = result + i + ": " + v.getName() + "\n";
                    i++;
                }
            }
        }

        return result;
    }
    
    // EFFECTS: gets the movie with the given name, if none is found, then null
    public Movie searchName(int num) {
        return mediaList.get(num);
    }

    // REQUIRES: media is Movie or Show
    // EFFECTS: checks if the given object is a Show
    public boolean isShow(Movie media) {
        Show compared = new Show("w", "placeholder", "placeholder");
        
        return media.getClass().equals(compared.getClass());
    }

    // REQUIRES: media is Movie or show
    // EFFECTS: checks if the given object is a Movie
    public boolean isMovie(Movie media) {
        Movie compared = new Movie("w", "placeholder", "placeholder");

        return media.getClass().equals(compared.getClass());
    }

    public LinkedHashMap<Integer, Movie> getMediaList() {
        return mediaList;
    }
}
