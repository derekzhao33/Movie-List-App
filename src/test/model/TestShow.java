package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class TestShow {

    private Show testShow;

    @BeforeEach
    public void setup() {
        testShow = new Show("Watched", "test", "genre");
    }

    @Test
    public void testConstructor() {
        assertEquals("Watched", testShow.getStatus());
        assertEquals("test", testShow.getName());
        assertEquals("genre", testShow.getGenre());
        assertEquals(List.of(), testShow.getNotes());
        assertEquals(0, testShow.getRating());
        assertEquals(0, testShow.getWatchTime());
        assertEquals(0, testShow.getEpisode());
        assertEquals(0, testShow.getSeason());
    }

    @Test
    public void testSetSeasonOnce() {
        testShow.setSeason(1);
        assertEquals(1, testShow.getSeason());
    }

    @Test
    public void testSetSeasonMultipleTimes() {
        testShow.setSeason(2);
        assertEquals(2, testShow.getSeason());
        testShow.setSeason(3);
        assertEquals(3, testShow.getSeason());
    }

    @Test
    public void testSetEpisodeOnce() {
        testShow.setEpisode(1);
        assertEquals(1, testShow.getEpisode());
    }

    @Test
    public void testSetEpisodeMultipleTime() {
        testShow.setEpisode(2);
        assertEquals(2, testShow.getEpisode());
        testShow.setEpisode(3);
        assertEquals(3, testShow.getEpisode());
    }

    @Test
    public void testEqualsSameFields() {
        assertTrue(testShow.equals(new Show("Watched", "test", "genre")));
    }

    @Test
    public void testEqualsSameType() {
        assertTrue(testShow.equals(new Show("To-watch", "Spiderman", "Action")));
    }

    @Test
    public void testEqualsNotSameType() {
        assertFalse(testShow.equals(new Movie("Watched", "test", "genre")));
    }
}
