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

    @BeforeEach
    public void setup() {
        testList = new MediaList();
        testMovieOne = new Movie("w", "One", "1");
        testMovieTwo = new Movie("c", "Two", "2");
        testMovieThree = new Movie("t", "Three", "3");
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
        testList.addMedia(testMovieThree);
        assertEquals("1: One\n2: Two\n3: Three\n", testList.getAllNames());
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
        testList.addMedia(testMovieThree);
        testList.removeMedia(2);
        assertEquals("1: One\n2: Three\n", testList.getAllNames());
        testList.removeMedia(1);
        assertEquals("1: Three\n", testList.getAllNames());
    }

    @Test
    public void testGetAllNames() {
        testList.addMedia(testMovieOne);
        testList.addMedia(testMovieTwo);
        testList.addMedia(testMovieThree);
        assertEquals("1: One\n2: Two\n3: Three\n", testList.getAllNames());
    }

    @Test
    public void testGetAllNamesByStatus() {
        testList.addMedia(testMovieOne);
        testList.addMedia(testMovieTwo);
        testList.addMedia(testMovieThree);
        assertEquals("1: One\n", testList.getAllNamesByStatus("w"));
        assertEquals("1: Two\n", testList.getAllNamesByStatus("c"));
    }

    @Test
    public void testGetAllNamesByGenre() {
        testList.addMedia(testMovieThree);
        testList.addMedia(testMovieTwo);
        assertEquals("1: Three\n", testList.getAllNamesByGenre("3"));
        assertEquals("1: Two\n", testList.getAllNamesByGenre("2"));
    }

    @Test 
    public void testSearchName() {
        testList.addMedia(testMovieTwo);
        testList.addMedia(testMovieOne);
        assertEquals(testMovieOne, testList.searchName(2));
    }

    @Test 
    public void testGetMediaList() {
        LinkedHashMap<Integer, Movie> testMap = new LinkedHashMap<>();
        testMap.put(1, testMovieOne);
        testMap.put(2, testMovieThree);
        testMap.put(3, testMovieTwo);
        testList.addMedia(testMovieOne);
        testList.addMedia(testMovieThree);
        testList.addMedia(testMovieTwo);
        assertEquals(testMap, testList.getMediaList());
    }
}
