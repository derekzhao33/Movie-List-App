package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.List;

public class TestShow {

    private Show testShow;
    private Movie testMovie;

    @BeforeEach
    public void setup() {
        testShow = new Show("w", "test", "genre");
        testMovie = new Movie("w", "one", "1");
    }

    @Test
    public void testConstructor() {
        assertEquals("w", testShow.getStatus());
        assertEquals("test", testShow.getName());
        assertEquals("genre", testShow.getGenre());
        assertEquals("", testShow.getNotes());
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
}
