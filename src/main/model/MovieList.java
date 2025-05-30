package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.*;

// Represents a list of movies
public class MovieList implements Writable {
    private LinkedHashMap<Integer, Movie> movieList;

    // EFFECTS: creates a a new MovieList with no movies
    public MovieList() {
        movieList = new LinkedHashMap<>();
    }

    // REQUIRES: every movie must have a different name
    // MODIFIES: this
    // EFFECTS: adds movie to the movie list, and adds an event 
    public void addMovie(Movie movie) {
        movieList.put(movieList.size() + 1, movie); 

        EventLog.getInstance().logEvent(new Event("\"" + movie.getName() + "\" was added"));
    }

    // REQUIRES: movieNum >= 1 AND movieNum <= movieList.size() AND movieList.size() > 0
    // MODIFIES: this­­­­­
    // EFFECTS: removes the movie that matches the given number, and adds an event 
    public void removeMovie(int movieNum) {
        Movie toBeRemoved = movieList.get(movieNum);
        movieList.remove(movieNum);
        
        EventLog.getInstance().logEvent(new Event("\"" + toBeRemoved.getName() + "\" was removed"));
        
        LinkedHashMap<Integer, Movie> lhmNew = new LinkedHashMap<>();
        int i = 1;

        for (Map.Entry<Integer, Movie> mapEntry : movieList.entrySet()) {
            Movie m = mapEntry.getValue();

            lhmNew.put(i, m);

            i++;
        }

        movieList = lhmNew;
    }
    
    // EFFECTS: gets the names of all movies on the list
    public String getAllNames() {
        String result = "";

        for (Map.Entry<Integer, Movie> mapEntry : movieList.entrySet()) {
            int n = mapEntry.getKey();
            Movie m = mapEntry.getValue();

            result = result + n + ": " + m.getName() + "\n"; 
        }

        return result;
    }

    // REQUIRES: status is one of: "w", "c", "t"
    // EFFECTS: gets the names of all movies on the list filered by status, and adds an event 
    public String getAllNamesByStatus(String status) {
        String result = "";
        int i = 1;

        for (Map.Entry<Integer, Movie> mapEntry : movieList.entrySet()) {
            Movie m = mapEntry.getValue();

            if (m.getStatus().equals(status)) {
                result = result + i + ": " + m.getName() + "\n"; 
                i++;
            }
        }

        EventLog.getInstance().logEvent(new Event("Filtered by status " + status));

        return result;
    }

    // EFFECTS: gets the names of all movies on the list filtered by genre, and adds an event 
    public String getAllNamesByGenre(String genre) {
        String result = "";
        int i = 1;

        for (Map.Entry<Integer, Movie> mapEntry : movieList.entrySet()) {
            Movie m = mapEntry.getValue();

            if (m.getGenre().equals(genre)) {
                result = result + i + ": " + m.getName() + "\n"; 
                i++;
            }
        }

        EventLog.getInstance().logEvent(new Event("Filtered by genre " + genre));

        return result;
    }
    
    // EFFECTS: gets the movie with the given number, if none is found, then null
    public Movie searchName(int num) {
        return movieList.get(num);
    }

    public LinkedHashMap<Integer, Movie> getMovieList() {
        return movieList;
    }

    @Override
    // EFFECTS: returns true if the given object is a MovieList with the same movies
    public boolean equals(Object compared) {
        if (this == compared) {
            return true;
        }

        if (!(compared instanceof MovieList)) {
            return false;
        }

        MovieList comparedMovieList = (MovieList) compared;

        if (movieList.size() != comparedMovieList.getMovieList().size()) {
            return false;
        }

        for (Map.Entry<Integer, Movie> mapElement : movieList.entrySet()) {
            Integer n = mapElement.getKey();
            Movie m = mapElement.getValue();

            if (!m.equals(comparedMovieList.searchName(n))) {
                return false;
            }
        }

        return true;
    }

    @Override
    // EFFECTS: returns the movie list as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONObject moviesJson = new JSONObject();

        for (Map.Entry<Integer, Movie> entry : movieList.entrySet()) {
            moviesJson.put(entry.getKey().toString(), entry.getValue().toJson());
        }

        json.put("movieList", moviesJson);
        return json;
    }

    // EFFECTS: returns the number of movies in the list
    public int getSize() {
        return movieList.size();
    }

    // EFFECTS: returns if the movie list is empty
    public boolean isEmpty() {
        return movieList.isEmpty();
    }
}
