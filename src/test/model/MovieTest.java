package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class MovieTest {

    private Movie testMovieWatched;
    private Movie testMovieCurrent;
    private Movie testMovieToWatch;
    
    @BeforeEach
    public void setup() {
        testMovieWatched = new Movie("Watched", "testOne", "genreOne");
        testMovieCurrent = new Movie("Currently Watching", "testTwo", "genreTwo");
        testMovieToWatch = new Movie("To-watch", "testThree", "genreThree");
    }

    @Test
    public void testConstructor() {
        assertEquals("Watched", testMovieWatched.getStatus());
        assertEquals("Currently Watching", testMovieCurrent.getStatus());
        assertEquals("To-watch", testMovieToWatch.getClass());
        assertEquals("testOne", testMovieWatched.getName());
        assertEquals("testTwo", testMovieCurrent.getName());
        assertEquals("genreOne", testMovieWatched.getGenre());
        assertEquals("genreTwo", testMovieCurrent.getGenre());
        assertEquals(List.of(), testMovieCurrent.getNotes());
        assertEquals(0, testMovieCurrent.getRating());
        assertEquals(0, testMovieCurrent.getWatchTime());
    }

    @Test
    public void testAddNoteOnce() {
        testMovieWatched.addNote("1");
        assertEquals(List.of("1"), testMovieCurrent.getNotes());
    }

    @Test
    public void testAddNoteMultipleTimes() {
        testMovieCurrent.addNote("1");
        assertEquals(List.of("1"), testMovieCurrent.getNotes());
        testMovieCurrent.addNote("2");
        assertEquals(List.of("1", "2"), testMovieCurrent.getNotes());
        testMovieCurrent.addNote("3");
        assertEquals(List.of("1", "2", "3"), testMovieCurrent.getNotes());
    }

    @Test
    public void testRemoveNoteOnce() {
        testMovieCurrent.addNote("1");
        testMovieCurrent.removeNote(1);
    }

    @Test
    public void testRemoveNoteMultipleTimes() {
        testMovieCurrent.addNote("1");
        testMovieCurrent.addNote("2");
        testMovieCurrent.addNote("3");
        testMovieCurrent.removeNote(1);
        assertEquals(List.of("2","3"), testMovieCurrent.getNotes());
        testMovieCurrent.removeNote(1);
        assertEquals(List.of("3"), testMovieCurrent.getNotes());
        testMovieCurrent.removeNote(1);
        assertEquals(List.of(), testMovieCurrent.getNotes());
    }

    @Test 
    public void testAddWatchTimeOnce() {
        testMovieCurrent.addWatchTime(1);
        assertEquals(1, testMovieCurrent.getWatchTime());
    }

    @Test
    public void testAddWatchTimeMultipleTimes() {
        testMovieCurrent.addWatchTime(2);
        assertEquals(2, testMovieCurrent.getWatchTime());
        testMovieCurrent.addWatchTime(3);
        assertEquals(3, testMovieCurrent.getWatchTime());
    }

    @Test
    public void testSetStatus() {
        testMovieCurrent.setStatus("Watching");
        assertEquals("Watching", testMovieCurrent.getClass());
        testMovieCurrent.setStatus("Watching");
        assertEquals("Watching", testMovieCurrent.getClass());
        testMovieCurrent.setStatus("To-watch");
        assertEquals("To-watch", testMovieCurrent.getClass());
        testMovieCurrent.setStatus("Currently Watching");
        assertEquals("Currently Watching", testMovieCurrent.getClass());

    }

    @Test 
    public void testSetName() {
        testMovieCurrent.setName("test");
        assertEquals("test", testMovieCurrent.getName());
        testMovieCurrent.setName("Batman");
        assertEquals("Batman", testMovieCurrent.getName());
    }

    @Test
    public void testSetGenre() {
        testMovieCurrent.setGenre("genre");
        assertEquals("genre", testMovieCurrent.getGenre());
        testMovieCurrent.setGenre("sci-fi");
        assertEquals("sci-fi", testMovieCurrent.getGenre());
    }

    @Test
    public void testSetRating() {
        testMovieCurrent.setRating(1);
        assertEquals(1, testMovieCurrent.getRating());
        testMovieCurrent.setRating(5);
        assertEquals(5, testMovieCurrent.getRating());
    }
}
