package model;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.List;

public class TestMediaList {

    private MediaList testList;
    private Movie testMovieOne;
    private Movie testMovieTwo;
    private Movie testMovieThree;
    private Show testShowOne;
    private Show testShowTwo;
    private Show testShowThree;

    @BeforeEach
    public void setup() {
        testList = new MediaList();
        testMovieOne = new Movie("w", "One", "1");
        testMovieTwo = new Movie("c", "Two", "2");
        testMovieThree = new Movie("t", "Three", "3");
        testShowOne = new Show("w", "A", "1");
        testShowTwo = new Show("c", "B", "2");
        testShowThree = new Show("t", "C", "3");
    }

    @Test
    public void testConstructor() {
        assertEquals("", testList.getAllNames());
    }

    @Test
    public void testAddMedia() {
        testList.addMedia(testMovieOne);
        assertEquals("1: One\n", testList.getAllNames());
        testList.addMedia(testMovieTwo);
        assertEquals("1: One\n2: Two\n", testList.getAllNames());
        testList.addMedia(testShowOne);
        assertEquals("1: One\n2: Two\n3: A\n", testList.getAllNames());
    }

    @Test
    public void testRemoveMedia() {
        testList.addMedia(testMovieOne);
        testList.removeMedia(1);
        assertEquals("", testList.getAllNames());
    }

    @Test
    public void testRemoveMultipleMedia() {
        testList.addMedia(testMovieOne);
        testList.addMedia(testMovieTwo);
        testList.addMedia(testShowOne);
        testList.removeMedia(2);
        assertEquals("1: One\n2: A\n", testList.getAllNames());
        testList.removeMedia(1);
        assertEquals("1: A\n", testList.getAllNames());
    }

    @Test
    public void testGetAllNames() {
        testList.addMedia(testMovieOne);
        testList.addMedia(testMovieTwo);
        testList.addMedia(testShowOne);
        assertEquals("1: One\n2: Two\n3: A\n", testList.getAllNames());
    }

    @Test
    public void testGetAllNamesByStatus() {
        testList.addMedia(testMovieOne);
        testList.addMedia(testMovieTwo);
        testList.addMedia(testShowOne);
        assertEquals("1: One\n2: A\n", testList.getAllNamesByStatus("w"));
        assertEquals("1: Two\n", testList.getAllNamesByStatus("c"));
    }

    @Test
    public void testGetAllNamesByGenre() {
        testList.addMedia(testMovieThree);
        testList.addMedia(testMovieTwo);
        testList.addMedia(testShowThree);
        testList.addMedia(testShowTwo);
        assertEquals("1: Three\n2: C\n", testList.getAllNamesByGenre("3"));
        assertEquals("1: Two\n2: B\n", testList.getAllNamesByGenre("2"));
    }

    @Test
    public void testGetAllNamesByType() {
        testList.addMedia(testMovieTwo);
        testList.addMedia(testShowOne);
        testList.addMedia(testMovieOne);
        testList.addMedia(testShowThree);
        assertEquals("1: Two\n2: One\n", testList.getAllNamesByType("m"));
        assertEquals("1: A\n2: C\n", testList.getAllNamesByType("s"));
    }

    @Test 
    public void testSearchName() {
        testList.addMedia(testMovieTwo);
        testList.addMedia(testShowOne);
        testList.addMedia(testMovieOne);
        assertEquals(testShowOne, testList.searchName(2));
    }

    @Test
    public void testChangeStatus() {
        testList.addMedia(testMovieOne);
        testList.changeStatus(testMovieOne, "t");
        assertEquals("t", testMovieOne.getStatus());
    }

    @Test
    public void testChangeName() {
        testList.addMedia(testMovieOne);
        testList.changeName(testMovieOne, "Batman");
        assertEquals("Batman", testMovieOne.getName());
    }

    @Test
    public void testChangeGenre() {
        testList.addMedia(testMovieOne);
        testList.changeGenre(testMovieOne, "sci-fi");
        assertEquals("sci-fi", testMovieOne.getGenre());
    }

    @Test
    public void testChangeRating() {
        testList.addMedia(testMovieOne);
        testList.changeRating(testMovieOne, 5);
        assertEquals(5, testMovieOne.getRating());
    }

    @Test
    public void testAddWatchTime() {
        testList.addMedia(testMovieOne);
        testList.addWatchTime(testMovieOne, 3);
        assertEquals(3, testMovieOne.getWatchTime());
        testList.addMedia(testMovieOne);
        testList.addWatchTime(testMovieOne, 7);
        assertEquals(10, testMovieOne.getWatchTime());
    }

    @Test
    public void testAddNote() {
        testList.addMedia(testMovieOne);
        testList.addNote(testMovieOne, "note");
        assertEquals("1: note\n", testMovieOne.getNotes());
        testList.addNote(testMovieOne, "other note");
        assertEquals("1: note\n2: other note\n", testMovieOne.getNotes());
    }

    @Test
    public void testRemoveNote() {
        testList.addMedia(testMovieOne);
        testList.addNote(testMovieOne, "note");
        testList.addNote(testMovieOne, "other note");
        testList.removeNote(testMovieOne, 2);
        assertEquals("1: note\n", testMovieOne.getNotes());
    }

    @Test
    public void testChangeEpisode() {
        testList.addMedia(testShowOne);
        testList.changeEpisode(testShowOne, 2);
        assertEquals(2, testShowOne.getEpisode());
    }

    @Test
    public void testChangeSeason() {
        testList.addMedia(testShowOne);
        testList.changeSeason(testShowOne, 2);
        assertEquals(2, testShowOne.getSeason());
    }

    @Test
    public void testIsShowTrue() {
        assertTrue(testList.isShow(testShowOne));
    }

    @Test
    public void testIsShowFalse() {
        assertFalse(testList.isShow(testMovieOne));
    }

    @Test
    public void testIsMovieTrue() {
        assertTrue(testList.isMovie(testMovieOne));
    }

    @Test
    public void testIsMovieFalse() {
        assertFalse(testList.isMovie(testShowOne));
    }

    @Test 
    public void testGetMediaList() {
        LinkedHashMap<Integer, Movie> testMap = new LinkedHashMap<>();
        testMap.put(1, testMovieOne);
        testMap.put(2, testShowOne);
        testMap.put(3, testMovieTwo);
        testList.addMedia(testMovieOne);
        testList.addMedia(testShowOne);
        testList.addMedia(testMovieTwo);
        assertEquals(testMap, testList.getMediaList());
    }
}
