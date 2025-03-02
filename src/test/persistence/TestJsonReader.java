package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestJsonReader {
    
    @Test
    public void testWriterInvalidFile() {
        try {
            MovieList ml = new MovieList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    public void testWriterEmptyMovieList() {
        try {
            MovieList ml = new MovieList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyMovieList.json");
            writer.open();
            writer.write(ml);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyMovieList.json");
            ml = reader.read();
            assertEquals(0, ml.getMovies().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriterGeneralMovieList() {
        try {
            MovieList ml = new MovieList();
            Movie testMovieOne = new Movie("t", "Star Wars", "sci-fi");
            Movie testMovieTwo = new Movie("c", "Batman", "action");
            testMovieTwo.addNote("yes");
            testMovieTwo.setRating(5);
            testMovieTwo.addWatchTime(3);
            Movie testMovieThree = new Movie("w", "Scream", "horror");
            testMovieThree.addNote("yes");
            testMovieThree.addNote("no");
            testMovieThree.setRating(4);
            testMovieThree.addWatchTime(2);
            ml.addMovie(testMovieOne);
            ml.addMovie(testMovieTwo);
            ml.addMovie(testMovieThree);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMovieList.json");
            writer.open();
            writer.write(ml);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralMovieList.json");
            ml = reader.read();
            LinkedHashMap<Integer, Movie> movies = ml.getMovies();
            assertEquals(3, movies.size());
            assertEquals(testMovieOne, movies.get(1));
            assertEquals(testMovieTwo, movies.get(2));
            assertEquals(testMovieThree, movies.get(3));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
