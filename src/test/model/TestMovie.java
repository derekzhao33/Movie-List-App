package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;

public class TestMovie {

    private Movie testMovieWatched;
    private Movie testMovieCurrent;
    private Movie testMovieToWatch;
    
    @BeforeEach
    public void setup() {
        testMovieWatched = new Movie("w", "testOne", "genreOne");
        testMovieCurrent = new Movie("c", "testTwo", "genreTwo");
        testMovieToWatch = new Movie("t", "testThree", "genreThree");
    }

    @Test
    public void testConstructor() {
        assertEquals("w", testMovieWatched.getStatus());
        assertEquals("c", testMovieCurrent.getStatus());
        assertEquals("t", testMovieToWatch.getStatus());
        assertEquals("testOne", testMovieWatched.getName());
        assertEquals("testTwo", testMovieCurrent.getName());
        assertEquals("genreOne", testMovieWatched.getGenre());
        assertEquals("genreTwo", testMovieCurrent.getGenre());
        assertEquals("", testMovieCurrent.getNotes());
        assertEquals(0, testMovieCurrent.getRating());
        assertEquals(0, testMovieCurrent.getWatchTime());
    }

    @Test
    public void testAddNoteMultipleTimes() {
        testMovieCurrent.addNote("1");
        assertEquals("1: 1\n", testMovieCurrent.getNotes());
        testMovieCurrent.addNote("2");
        assertEquals("1: 1\n2: 2\n", testMovieCurrent.getNotes());
        testMovieCurrent.addNote("3");
        assertEquals("1: 1\n2: 2\n3: 3\n", testMovieCurrent.getNotes());
    }

    @Test
    public void testRemoveNoteMultipleTimes() {
        testMovieCurrent.addNote("1");
        testMovieCurrent.addNote("2");
        testMovieCurrent.addNote("3");
        testMovieCurrent.removeNote(1);
        assertEquals("1: 2\n2: 3\n", testMovieCurrent.getNotes());
        testMovieCurrent.removeNote(1);
        assertEquals("1: 3\n", testMovieCurrent.getNotes());
        testMovieCurrent.removeNote(1);
        assertEquals("", testMovieCurrent.getNotes());
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
        assertEquals(5, testMovieCurrent.getWatchTime());
    }

    @Test
    public void testSetStatus() {
        testMovieCurrent.setStatus("w");
        assertEquals("w", testMovieCurrent.getStatus());
        testMovieCurrent.setStatus("w");
        assertEquals("w", testMovieCurrent.getStatus());
        testMovieCurrent.setStatus("t");
        assertEquals("t", testMovieCurrent.getStatus());
        testMovieCurrent.setStatus("c");
        assertEquals("c", testMovieCurrent.getStatus());

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

    @Test
    public void testEqualsSameObject() {
        assertTrue(testMovieCurrent.equals(testMovieCurrent));
    }

    @Test
    public void testEqualsDifferentObject() {
        assertFalse(testMovieCurrent.equals(testMovieWatched));
    }

    @Test
    public void testEqualsSlightlyDifferent() {
        Movie testMovieCurrentCopy = new Movie("c", "testTwo", "genreTwo");
        assertTrue(testMovieCurrent.equals(testMovieCurrentCopy));
    }

    @Test
    public void testEqualsDifferentType() {
        assertFalse(testMovieCurrent.equals("test"));
    }

    @Test
    public void testEqualsDifferentObjectSameInfo() {
        Movie testMovieCurrentCopy = new Movie("c", "testTwo", "genreTwo");
        assertTrue(testMovieCurrent.equals(testMovieCurrentCopy));
    }

    @Test
    public void testEqualsDifferentRating() {
        Movie testMovieCurrentCopy = new Movie("c", "testTwo", "genreTwo");
        testMovieCurrentCopy.setRating(2);
        assertFalse(testMovieCurrent.equals(testMovieCurrentCopy));
    }

    @Test
    public void testEqualsDifferentWatchTime() {
        Movie testMovieCurrentCopy = new Movie("c", "testTwo", "genreTwo");
        testMovieCurrentCopy.addWatchTime(10);
        assertFalse(testMovieCurrent.equals(testMovieCurrentCopy));
    }

    @Test
    public void testEqualsDifferentGenre() {
        Movie testMovieCurrentCopy = new Movie("c", "testTwo", "genreThree");
        assertFalse(testMovieCurrent.equals(testMovieCurrentCopy));
    }

    @Test
    public void testEqualsDifferentNotes() {
        Movie testMovieCurrentCopy = new Movie("c", "testTwo", "genreTwo");
        testMovieCurrentCopy.addNote("different note");
        assertFalse(testMovieCurrent.equals(testMovieCurrentCopy));
    }

    @Test
    public void testEqualsDifferentStatus() {
        Movie testMovieCurrentCopy = new Movie("w", "testTwo", "genreTwo");
        assertFalse(testMovieCurrent.equals(testMovieCurrentCopy));
    }

    @Test
    public void testEqualsDifferentName() {
        Movie testMovieCurrentCopy = new Movie("c", "testThree", "genreTwo");
        assertFalse(testMovieCurrent.equals(testMovieCurrentCopy));
    }

    @Test
    public void testToJson() {
        testMovieCurrent.addNote("one");
        testMovieCurrent.setRating(1);
        testMovieCurrent.addWatchTime(5);
        JSONObject testJson = new JSONObject();
        testJson.put("status", "c");
        testJson.put("name", "testTwo");
        testJson.put("genre", "genreTwo");
        JSONObject notesJson = new JSONObject();
        notesJson.put("1", "one");
        testJson.put("notes", notesJson);
        testJson.put("rating", 1);
        testJson.put("watchTime", 5);
        assertEquals(testJson.toString(), testMovieCurrent.toJson().toString());
    }
}
