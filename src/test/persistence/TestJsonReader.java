package persistence;

import model.Movie;
import model.MovieList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.*;

import javax.swing.plaf.multi.MultiViewportUI;

import org.junit.jupiter.api.Assertions.*;

public class TestJsonReader {
    
    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            MovieList ml = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyMovieList.json");
        try {
            MovieList ml = reader.read();
            assertEquals(new LinkedHashMap<Integer, Movie>(), ml.getMovieList());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            MovieList ml = reader.read();
            MovieList testML = new MovieList();
            Movie testMovieOne = new Movie("w", "testOne", "genreOne");
            Movie testMovieTwo = new Movie("c", "testTwo", "genreTwo");
            testMovieTwo.addNote("one");
            testMovieTwo.setRating(1);
            testMovieTwo.addWatchTime(5);
            Movie testMovieThree = new Movie("t", "testThree", "genreThree");
            testMovieThree.addNote("one");
            testMovieThree.addNote("two");
            testMovieThree.setRating(2);
            testMovieThree.addWatchTime(10);
            testML.addMovie(testMovieOne);
            testML.addMovie(testMovieTwo);
            testML.addMovie(testMovieThree);
            assertEquals(testMovieTwo, testMovieThree);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
