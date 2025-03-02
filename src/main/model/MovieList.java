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
    // EFFECTS: adds movie to the movie list
    public void addMovie(Movie movie) {
        this.movieList.put(movieList.size() + 1, movie);
    }

    // REQUIRES: movieNum >= 1 AND movieNum <= movieList.size() AND movieList.size() > 0
    // MODIFIES: this
    // EFFECTS: removes the movie that matches the given number
    public void removeMovie(int movieNum) {
        this.movieList.remove(movieNum);
        
        LinkedHashMap<Integer, Movie> lhmNew = new LinkedHashMap<>();
        int i = 1;

        for (Map.Entry<Integer, Movie> mapEntry : this.movieList.entrySet()) {
            Movie v = mapEntry.getValue();

            lhmNew.put(i, v);

            i++;
        }

        movieList = lhmNew;
    }
    
    // EFFECTS: gets the names of all movies on the list
    public String getAllNames() {
        String result = "";

        for (Map.Entry<Integer, Movie> mapEntry : this.movieList.entrySet()) {
            Integer k = mapEntry.getKey();
            Movie v = mapEntry.getValue();

            result = result + k + ": " + v.getName() + "\n"; 
        }

        return result;
    }

    // REQUIRES: status is one of: "w", "c", "t"
    // EFFECTS: gets the names of all movies on the list filered by status
    public String getAllNamesByStatus(String status) {
        String result = "";
        int i = 1;

        for (Map.Entry<Integer, Movie> mapEntry : this.movieList.entrySet()) {
            Movie v = mapEntry.getValue();

            if (v.getStatus().equals(status)) {
                result = result + i + ": " + v.getName() + "\n"; 
                i++;
            }
        }

        return result;
    }

    // EFFECTS: gets the names of all movies on the list filtered by genre
    public String getAllNamesByGenre(String genre) {
        String result = "";
        int i = 1;

        for (Map.Entry<Integer, Movie> mapEntry : this.movieList.entrySet()) {
            Movie v = mapEntry.getValue();

            if (v.getGenre().equals(genre)) {
                result = result + i + ": " + v.getName() + "\n"; 
                i++;
            }
        }

        return result;
    }
    
    // EFFECTS: gets the movie with the given name, if none is found, then null
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

        if (this.movieList.size() != comparedMovieList.getMovieList().size()) {
            return false;
        }

        for (Map.Entry<Integer, Movie> mapElement : this.movieList.entrySet()) {
            Integer k = mapElement.getKey();
            Movie v = mapElement.getValue();

            if (!v.equals(comparedMovieList.searchName(k))) {
                return false;
            }
        }

        return true;
    }

    @Override
    // EFFECTS: returns the movie list as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        for (Map.Entry<Integer, Movie> mapElement : this.movieList.entrySet()) {
            Integer k = mapElement.getKey();
            Movie v = mapElement.getValue();

            json.put(k.toString(), v.toJson());
        }

        return json;
    }

    // EFFECTS: returns the number of movies in the list
    public int getSize() {
        return this.movieList.size();
    }

    public LinkedHashMap<Integer, Movie> getMovies() {
        return new LinkedHashMap<>(); // stub
    }
}
