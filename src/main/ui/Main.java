package ui;

import java.io.FileNotFoundException;

// Runs the application
public class Main {

    // EFFECTS: runs the application
    public static void main(String[] args) {
        try {
            new MovieListApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
