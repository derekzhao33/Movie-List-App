package ui;

import model.*;
import persistence.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

// Represents a movie list application
public class MovieListApp {
    private static final String JSON_STORE = "./data/movieList.json";
    private MovieList movieList;
    private Scanner scanner;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    
    // EFFECTS: creates a new movie list application
    public MovieListApp() throws FileNotFoundException {
        this.movieList = new MovieList();
        this.scanner = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        start();
    }

    // MODIFIES: this
    // EFFECTS: starts the application
    public void start() {
        printCommands();

        while (true) {
            System.out.println("Enter a command:");
            String input = scanner.nextLine();

            if (input.equals("q")) {
                break;
            } else {
                processCommand(input);
            }
        }

        System.out.println("Exiting app...");
        System.exit(0);
    }

    // EFFECTS: prints the commands for the app
    public void printCommands() {
        System.out.println("Commands:");
        System.out.println("a - add movie");
        System.out.println("r - remove movie");
        System.out.println("p - print all movies");
        System.out.println("f - filter movies");
        System.out.println("i - display information");
        System.out.println("c - change existing movie");
        System.out.println("s - save movie list");
        System.out.println("l - load movie list");
        System.out.println("q - quit app");
    }

    // EFFECTS: saves the movie list to file
    public void saveMovieList() {
        try {
            jsonWriter.open();
            jsonWriter.write(movieList);
            jsonWriter.close();
            System.out.println("Saved movie list to " + " to: " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the movie list from file
    public void loadMovieList() {
        try {
            movieList = jsonReader.read();
            System.out.println("Loaded movie list from: " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    @SuppressWarnings("methodlength")
    // EFFECTS: processes the inputs from the app
    public void processCommand(String input) {
        switch (input) {
            case "a":
                addMovie();
                break;
            case "r":
                removeMovie();
                break;
            case "p":
                printMovie();
                break;
            case "f":
                filterMovie();
                break;
            case "i":
                displayMovieInfo();
                break;
            case "c":
                changeMovie();
                break;
            case "s":
                saveMovieList();
                break;
            case "l":
                loadMovieList();
                break;
            default:
                System.out.println("Invalid command, please try again.");
                break;
        }
    }

    // EFFECTS: handles input for the status of a new movie 
    public String addMovieHandleInputStatus() {
        System.out.println("Enter the status of the new movie:");
        printStatusChoice();
        String status = scanner.nextLine();
        return status;
    }

    // EFFECTS: handles input for the name of a new movie
    public String addMovieHandleInputName() {
        System.out.println("Enter the name of the new movie:");
        String name = scanner.nextLine();
        return name;
    }

    // EFFECTS: handles input for the genre of a new movie
    public String addMovieHandleInputGenre() {
        System.out.println("Enter the genre of the new movie:");
        String genre = scanner.nextLine();
        return genre;
    }

    // MODIFIES: this
    // EFFECTS: controls adding a movie to the list
    public void addMovie() {
        while (true) {
            String status = addMovieHandleInputStatus();
            
            if (!(status.equals("w") || status.equals("c") || status.equals("t"))) {
                System.out.println("Invalid command, please try again.");
                continue;
            }

            String name = addMovieHandleInputName();
            String genre = addMovieHandleInputGenre();       
            movieList.addMovie(new Movie(status, name, genre));

            System.out.println("Add another movie?");
            printYesNo();
            String repeat = scanner.nextLine();
            
            if (repeat.equals("n")) {
                break;
            } else if (repeat.equals("y")) {
                continue;
            } else {
                System.out.println("Invalid command, please try again.");
                continue;
            }
        }

        start();
    }

    // MODIFIES: this
    // REQUIRES: at least one movie is on the list
    // EFFECTS: removes a movie from the list
    public void removeMovie() {
        while (true) {
            System.out.println("Enter the number of the movie to be removed:");
            System.out.print(movieList.getAllNames());
            int selectedNum = Integer.valueOf(scanner.nextLine());

            movieList.removeMovie(selectedNum);

            System.out.println("Remove another movie?");
            printYesNo();
            String repeat = scanner.nextLine();

            if (repeat.equals("y")) {
                continue;
            } else if (repeat.equals("n")) {
                break;
            } else {
                System.out.println("Invalid command, please try again.");
                continue;
            }
        }

        start();
    }

    // EFFECTS: prints the names of all movie
    public void printMovie() {
        System.out.println("Printing all movies:");
        System.out.println(movieList.getAllNames());
        start();
    }

    // EFFECTS: prints the prompt for filtering a movie
    public void filterByPrint() {
        System.out.println("Enter an option to filter by:");
        System.out.println("g - genre");
        System.out.println("s - status");
    }
    
    // EFFECTS: prints the filtering by genre 
    public void filterMovieInputIsGenre() {
        System.out.println("Enter the name of the genre to filter by:");
        String genre = scanner.nextLine();
        System.out.println(movieList.getAllNamesByGenre(genre));
    }

    // EFFECTS: handles the input for status when filtering
    public String filterMovieHandleInputStatus() {
        System.out.println("Enter the status to filter by:");
        printStatusChoice();
        String status = scanner.nextLine();
        return status;
    }

    // REQUIRES: a movie with the given genre/status must exist
    // EFFECTS: filters the movie by the input
    @SuppressWarnings("methodlength")
    public void filterMovie() {
        while (true) {
            filterByPrint();
            String filterBy = scanner.nextLine();

            if (filterBy.equals("g")) {
                filterMovieInputIsGenre();
            } else if (filterBy.equals("s")) {
                String status = filterMovieHandleInputStatus();
                
                if (!(status.equals("w") || status.equals("c") || status.equals("t"))) {
                    System.out.println("Invalid command, please try again.");
                    continue;
                }

                System.out.println(movieList.getAllNamesByStatus(status));
            } else {
                System.out.println("Invalid command, please try again.");
                continue;
            }

            System.out.println("Filter again?");
            printYesNo();
            String repeat = scanner.nextLine();

            if (repeat.equals("n")) {
                break;
            } else if (repeat.equals("y")) {
                continue;
            } else {
                System.out.println("Invalid command, please try again.");
                continue;
            }
        }
        
        start();
    }

    // EFFECTS: handles the input for number when displaying movies
    public int displayMovieInfoHandleInputNumber() {
        System.out.println("Enter the number of the movie to display info for:");
        System.out.print(movieList.getAllNames());
        int selectedNum = Integer.valueOf(scanner.nextLine());
        return selectedNum;
    }

    // EFFECTS: displays movie information
    public void displayMovieInfo() {
        while (true) {
            int selectedNum = displayMovieInfoHandleInputNumber();
            Movie selected = movieList.searchName(selectedNum);
            
            if (selected == null) {
                System.out.println("Movie item doesn't exist, please try again.");
                continue;
            } else {
                printMovieInfoMovie(selected);
            } 

            System.out.println("Display info for another movie?");
            printYesNo();
            String repeat = scanner.nextLine();

            if (repeat.equals("n")) {
                break;
            } else if (repeat.equals("y")) {
                continue;
            } else {
                System.out.println("Invalid command, please try again.");
                continue;
            }
        }

        start();
    }

    // REQUIRES: at least one movie is on the list
    // MODIFIES: this
    // EFFECTS: changes a movie
    public void changeMovie() {
        while (true) {
            System.out.println("Enter the number of the movie to be changed:");
            System.out.print(movieList.getAllNames());
            int selNum = Integer.valueOf(scanner.nextLine());
            Movie selected = movieList.searchName(selNum);

            printChangeOptionsMovie();
            String commandMovie = scanner.nextLine();

            processChangeMovie(commandMovie, selected);

            System.out.println("Make another change?");
            printYesNo();
            String repeat = scanner.nextLine();

            if (repeat.equals("n")) {
                break;
            } else if (repeat.equals("y")) {
                continue;
            } else {
                System.out.println("Invalid command, please try again.");
                continue;
            }
        }

        start();
    }

    // EFFECTS: processes the movie change for movies only
    public void processChangeMovie(String command, Movie selected) {
        switch (command) {
            case "s":
                changeStatus(selected);
                break;
            case "nm":
                changeName(selected);
                break;
            case "g":
                changeGenre(selected);
                break;
            case "n":
                changeNotes(selected);
                break;
            case "r":
                changeRating(selected);
                break;
            case "w":
                changeWatchTime(selected);
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: changes the status for given movie
    public void changeStatus(Movie movie) {
        while (true) {
            System.out.println("Enter the new status:");
            printStatusChoice();
            String status = scanner.nextLine();
            if (!(status.equals("w") || status.equals("c") || status.equals("t"))) {
                System.out.println("Invalid command, please try again.");
                continue;
            }

            movie.setStatus(status);
            break;
        }
    }

    // REQUIRES: name length > 0
    // MODIFIES: this
    // EFFECTS: changes the name for given movie
    public void changeName(Movie movie) {
        System.out.println("Enter the new name:");
        String name = scanner.nextLine();

        movie.setName(name);
    }

    // REQUIRES: genre length > 0
    // MODIFIES: this
    // EFFECTS: changes the genre for given movie
    public void changeGenre(Movie movie) {
        System.out.println("Enter the new genre:");
        String genre = scanner.nextLine();

        movie.setGenre(genre);
    }

    // EFFECTS: handles the input for removing/adding a note
    public String changeNotesHandleInputChoice() {
        System.out.println("Enter an option:");
        System.out.println("a - add note");
        System.out.println("r - remove note");
        String choice = scanner.nextLine();
        return choice;
    }

    // MODIFIES: this
    // EFFECTS: changes the notes for given movie   
    public void changeNotes(Movie movie) {
        while (true) {
            String choice = changeNotesHandleInputChoice();

            if (choice.equals("a")) {
                addNote(movie);
            } else if (choice.equals("r")) {
                removeNote(movie);
            } else {
                System.out.println("Invalid command, please try again.");
                continue;
            }

            System.out.println("Change another note?");
            printYesNo();
            String repeat = scanner.nextLine();

            if (repeat.equals("n")) {
                break;
            } else if (repeat.equals("y")) {
                continue;
            } else {
                System.out.println("Invalid command, please try again.");
                continue;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a note for given movie
    public void addNote(Movie movie) {
        while (true) {
            System.out.println("Enter the note to be added:");
            String note = scanner.nextLine();

            movie.addNote(note);
            
            System.out.println("Add another note?");
            printYesNo();
            String repeat = scanner.nextLine();

            if (repeat.equals("n")) {
                break;
            } else if (repeat.equals("y")) {
                continue;
            } else {
                System.out.println("Invalid command, please try again.");
                continue;
            }
        }
    }

    // REQUIRES: movie has at least one note AND num <= note size for the movie
    // MODIFIES: this
    // EFFECTS: removes a note for given movie
    public void removeNote(Movie movie) {
        while (true) {
            System.out.println("Enter the number of the note to be removed:");
            System.out.println(movie.getNotes());
            int num = Integer.valueOf(scanner.nextLine());

            movie.removeNote(num);
            System.out.println("Note " + num + " was removed.");

            System.out.println("Remove another note?");
            printYesNo();
            String repeat = scanner.nextLine();

            if (repeat.equals("n")) {
                break;
            } else if (repeat.equals("y")) {
                continue;
            } else {
                System.out.println("Invalid command, please try again.");
                continue;
            }
        }
    }

    // REQUIRES: rating >= 1 AND rating <= 5
    // MODIFIES: this
    // EFFECTS: changes the rating for given movie
    public void changeRating(Movie movie) {
        System.out.println("Enter the new rating (1-5):");
        int rating = Integer.valueOf(scanner.nextLine());

        movie.setRating(rating);
    }

    // REQUIRES: watchTime > 0
    // MODIFIES: this
    // EFFECTS: changes the watch time for given movie
    public void changeWatchTime(Movie movie) {
        System.out.println("Enter the watch time to be added:");
        int watchTime = Integer.valueOf(scanner.nextLine());

        movie.addWatchTime(watchTime);
    }

    // EFFECTS: prints all info for given movie
    public void printMovieInfoMovie(Movie movie) {
        System.out.println(movie.toString());
    }

    // EFFECTS: prints the status choices
    public void printStatusChoice() {
        System.out.println("w - watching");
        System.out.println("c - currently watching");
        System.out.println("t - to-watch");
    }

    // EFFECTS: prints yes or no
    public void printYesNo() {
        System.out.println("y - yes");
        System.out.println("n - no");
    }

    // EFFECTS: prints the change options for a movie
    public void printChangeOptionsMovie() {
        System.out.println("Enter the detail to be changed:");
        System.out.println("s - status");
        System.out.println("nm - name");
        System.out.println("g - genre");
        System.out.println("n - notes");
        System.out.println("r - rating");
        System.out.println("w - watch time");
    }
}
