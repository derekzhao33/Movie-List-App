package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class TestMovieList {

    private MovieList testList;
    private MovieList testListTwo;
    private Movie testMovieOne;
    private Movie testMovieTwo;
    private Movie testMovieThree;

    @BeforeEach
    public void setup() {
        testList = new MovieList();
        testListTwo = new MovieList();
        testMovieOne = new Movie("w", "One", "1");
        testMovieTwo = new Movie("c", "Two", "2");
        testMovieThree = new Movie("t", "Three", "3");
    }

    @Test
    public void testConstructor() {
        assertEquals("", testList.getAllNames());
    }

    @Test
    public void testAddMovie() {
        testList.addMovie(testMovieOne);
        assertEquals("1: One\n", testList.getAllNames());
        testList.addMovie(testMovieTwo);
        assertEquals("1: One\n2: Two\n", testList.getAllNames());
        testList.addMovie(testMovieThree);
        assertEquals("1: One\n2: Two\n3: Three\n", testList.getAllNames());
    }

    @Test
    public void testRemoveMovie() {
        testList.addMovie(testMovieOne);
        testList.removeMovie(1);
        assertEquals("", testList.getAllNames());
    }

    @Test
    public void testRemoveMultipleMovies() {
        testList.addMovie(testMovieOne);
        testList.addMovie(testMovieTwo);
        testList.addMovie(testMovieThree);
        testList.removeMovie(2);
        assertEquals("1: One\n2: Three\n", testList.getAllNames());
        testList.removeMovie(1);
        assertEquals("1: Three\n", testList.getAllNames());
    }

    @Test
    public void testGetAllNames() {
        testList.addMovie(testMovieOne);
        testList.addMovie(testMovieTwo);
        testList.addMovie(testMovieThree);
        assertEquals("1: One\n2: Two\n3: Three\n", testList.getAllNames());
    }

    @Test
    public void testGetAllNamesByStatus() {
        testList.addMovie(testMovieOne);
        testList.addMovie(testMovieTwo);
        testList.addMovie(testMovieThree);
        assertEquals("1: One\n", testList.getAllNamesByStatus("w"));
        assertEquals("1: Two\n", testList.getAllNamesByStatus("c"));
    }

    @Test
    public void testGetAllNamesByGenre() {
        testList.addMovie(testMovieThree);
        testList.addMovie(testMovieTwo);
        assertEquals("1: Three\n", testList.getAllNamesByGenre("3"));
        assertEquals("1: Two\n", testList.getAllNamesByGenre("2"));
    }

    @Test 
    public void testSearchName() {
        testList.addMovie(testMovieTwo);
        testList.addMovie(testMovieOne);
        assertEquals(testMovieOne, testList.searchName(2));
    }

    @Test 
    public void testGetMovieList() {
        LinkedHashMap<Integer, Movie> testMap = new LinkedHashMap<>();
        testMap.put(1, testMovieOne);
        testMap.put(2, testMovieThree);
        testMap.put(3, testMovieTwo);
        testList.addMovie(testMovieOne);
        testList.addMovie(testMovieThree);
        testList.addMovie(testMovieTwo);
        assertEquals(testMap, testList.getMovieList());
    }

    @Test
    public void testEqualsSameObject() {
        assertTrue(testList.equals(testList));
    }

    @Test
    public void testEqualsDifferenType() {
        assertFalse(testList.equals(testMovieOne));
    }

    @Test
    public void testEqualsDifferentMovies() {
        testList.addMovie(testMovieOne);
        testListTwo.addMovie(testMovieTwo);
        assertFalse(testList.equals(testListTwo));
    }

    @Test
    public void testEqualsSameMovies() {
        testList.addMovie(testMovieOne);
        testListTwo.addMovie(testMovieOne);
        assertTrue(testList.equals(testListTwo));
    }

    @Test
    public void testEqualsDifferentSize() {
        testList.addMovie(testMovieOne);
        testList.addMovie(testMovieTwo);
        testListTwo.addMovie(testMovieOne);
        assertFalse(testList.equals(testListTwo));
    }

    @Test
    public void testToJson() {
        testList.addMovie(testMovieOne);
        testList.addMovie(testMovieTwo);
        testList.addMovie(testMovieThree);
        JSONObject testJson = new JSONObject();
        testJson.put("1", testMovieOne.toJson());
        testJson.put("2", testMovieTwo.toJson());
        testJson.put("3", testMovieThree.toJson());
        assertEquals(testJson.toString(), testList.toJson().toString());
    }

    @Test
    public void testToJsonEmpty() {
        JSONObject testJson = new JSONObject();
        assertEquals(testJson.toString(), testList.toJson().toString());
    }
}
