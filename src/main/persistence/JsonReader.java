package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads a movie list from JSON data stored in file
// Code was partially based on the JsonSerializationDemo project
// URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads movielist from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MovieList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMovieList(jsonObject);
    }

   // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }   

        return contentBuilder.toString();
    }

    // EFFECTS: parses movielist from JSON object and returns it
    private MovieList parseMovieList(JSONObject jsonObject) {
        MovieList ml = new MovieList();
        addMovies(ml, jsonObject);
        return ml;
    }

    // MODIFIES: ml
    // EFFECTS: parses movies from JSON object and adds them to movielist
    private void addMovies(MovieList ml, JSONObject jsonObject) {
        JSONObject jsonObj = jsonObject.getJSONObject("movieList");

        for (String key : jsonObj.keySet()) {
            JSONObject nextMovie = jsonObj.getJSONObject(key);
            addMovie(ml, nextMovie);
        }
    }

    // MODIFIES: ml
    // EFFECTS: parses movie from JSON object and adds it to movielist
    private void addMovie(MovieList ml, JSONObject jsonObject) {
        String status = jsonObject.getString("status");
        String name = jsonObject.getString("name");
        String genre = jsonObject.getString("genre");
        JSONObject notesObj = jsonObject.getJSONObject("notes");
        Map<String, String> notes = new HashMap<>();

        for (String noteKey : notesObj.keySet()) {
            notes.put(noteKey, notesObj.getString(noteKey));
        }

        int rating = jsonObject.getInt("rating");
        int watchTime = jsonObject.getInt("watchTime");

        Movie movie = new Movie(status, name, genre);
        movie.setRating(rating);
        movie.addWatchTime(watchTime);

        for (Map.Entry<String, String> entry : notes.entrySet()) {
            movie.addNote(entry.getValue());
        }
        
        ml.addMovie(movie);
    }
}
