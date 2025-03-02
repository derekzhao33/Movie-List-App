package persistence;

import model.Movie;
import model.MovieList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;


public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {

    }

    // EFFECTS: reads movielist from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MovieList read() throws IOException {
        return new MovieList(); // stub
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        return ""; // stub
     }

    // EFFECTS: parses movielist from JSON object and returns it
    private void parseMovieList(JSONObject jsonObject) {
        // TODO
    }

    // MODIFIES: ml
    // EFFECTS: parses movies from JSON object and adds them to movielist
    private void addMovies(MovieList ml, JSONObject jsonObject) {
        // TODO
    }

    // MODIFIES: ml
    // EFFECTS: parses movie from JSON object and adds it to movielist
    private void addMovie(MovieList ml, JSONObject jsonObject) {
        // TODO
    }
}
