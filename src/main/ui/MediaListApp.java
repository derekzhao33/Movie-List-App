package ui;

import java.util.*;
import model.*;

public class MediaListApp {
    
    private MediaList mediaList;
    private Scanner scanner;
    
    // EFFECTS: creates a new media list application
    public MediaListApp() {
        this.mediaList = new MediaList();
        this.scanner = new Scanner(System.in);
        start();
    }

    // MODIFIES: this
    // EFFECTS: starts the application
    public void start() {
        printCommands();

        while(true) {
            System.out.println("Enter a command:");
            String input = scanner.nextLine();

            if (input.equals("q")) {
                break;
            }

            processCommand(input);
        }
    }

    // EFFECTS: prints the commands for the app
    public void printCommands() {
        System.out.println("Commands:");
        System.out.println("a - add media");
        System.out.println("r - remove media");
        System.out.println("p - print all media");
        System.out.println("f - filter media");
        System.out.println("i - display information");
        System.out.println("c - change existing media");
        System.out.println("q - quit app");
    }

    // EFFECTS: processes the inputs from the app
    public void processCommand(String input) {
        switch(input) {
            case "a":
                addMedia();
                break;
            case "r":
                removeMedia();
                break;
            case "p":
                printMedia();
                break;
            case "f":
                filterMedia();
                break;
            case "i":
                displayMediaInfo();
                break;
            case "c":
                changeMedia();
                break;
            default:
                System.out.println("Invalid command, please try again.");
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a media item to the list
    public void addMedia() {
        while (true) {
            System.out.println("Enter the status of the new media item:");
            printStatusChoice();
            String status = scanner.nextLine();
            
            if (!(status.equals("w") || status.equals("c") || status.equals("t"))) {
                System.out.println("Invalid command, please try again.");
                continue;
            }

            System.out.println("Enter the name of the new media item:");
            String name = scanner.nextLine();

            System.out.println("Enter the genre of the new media item:");
            String genre = scanner.nextLine();

            System.out.println("Enter the type of the new media item");
            printTypeChoice();
            String type = scanner.nextLine();
        
            if (type.equals("s")) {
                mediaList.addMedia(new Show(status, name, genre));
            } else if (type.equals("m")) {
                mediaList.addMedia(new Movie(status, name, genre));
            } else {
                System.out.println("Invalid command, please try again.");
                continue;
            }

            System.out.println("Add another media item?");
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
    // REQUIRES: at least one media item is on the list
    // EFFECTS: removes a media item from the list
    public void removeMedia() {
        while (true) {
            System.out.println("Enter the number of the media item to be removed:");
            System.out.print(mediaList.getAllNames());
            int selectedNum = Integer.valueOf(scanner.nextLine());

            mediaList.removeMedia(selectedNum);

            System.out.println("Remove another media item?");
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

    // EFFECTS: prints the names of all media items
    public void printMedia() {
        System.out.println("Printing all media items:");
        System.out.println(mediaList.getAllNames());
        start();
    }

    // REQUIRES: a media item with the given genre/status/type must exist
    // EFFECTS: filters the media items by the input
    public void filterMedia() {
        while (true) {
            System.out.println("Enter an option to filter by:");
            System.out.println("g - genre");
            System.out.println("s - status");
            System.out.println("t - type");
            String filterBy = scanner.nextLine();

            if (filterBy.equals("g")) {
                System.out.println("Enter the name of the genre to filter by:");
                String genre = scanner.nextLine();
                System.out.println(mediaList.getAllNamesByGenre(genre));
            } else if (filterBy.equals("s")) {
                System.out.println("Enter the status to filter by:");
                printStatusChoice();
                String status = scanner.nextLine();
                
                if (!(status.equals("w") || status.equals("c") || status.equals("t"))) {
                    System.out.println("Invalid command, please try again.");
                    continue;
                }

                System.out.println(mediaList.getAllNamesByStatus(status));
            } else if (filterBy.equals("t")) {
                System.out.println("Enter the type to filter by:");
                printTypeChoice();
                String type = scanner.nextLine();
                System.out.println(mediaList.getAllNamesByType(type));
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

    public void displayMediaInfo() {
        while (true) {
            System.out.println("Enter the number of the media item to display info for:");
            System.out.print(mediaList.getAllNames());
            int selectedNum = Integer.valueOf(scanner.nextLine());
            Movie selected = mediaList.searchName(selectedNum);
            
            if (selected == null) {
                System.out.println("Media item doesn't exist, please try again.");
                continue;
            } else if (mediaList.isShow(selected)) {
                Show selectedShow = new Show(selected.getStatus(), selected.getName(), selected.getGenre());
                printMediaInfoShow(selectedShow);
            } else {
                printMediaInfoMovie(selected);
            } 

            System.out.println("Display info for another media item?");
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
    // EFFECTS: changes a media item
    public void changeMedia() {
        while (true) {
            System.out.println("Enter the number of the media item to be changed:");
            System.out.print(mediaList.getAllNames());
            int selNum = Integer.valueOf(scanner.nextLine());
            Movie selected = mediaList.searchName(selNum);

            if (mediaList.isShow(selected)) {
                printChangeOptionsShow();
                String commandShow = scanner.nextLine();

                processChangeShow(commandShow, selectedShow);
            } else {
                printChangeOptionsMovie();
                String commandMovie = scanner.nextLine();

                processChangeMovie(commandMovie, selected);
            }

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

    // REQUIRES: selected is of type Movie
    // EFFECTS: processes the media item change for movies only
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

    // REQUIRES; selected is of type Show
    // EFFECTS: proceses the media item change for shows only
    public void processChangeShow(String command, Show selected) {
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
            case "e":
                changeEpisode(selected);
                break;
            case "se":
                changeSeason(selected);
                break;
            default:
                System.out.println("Invalid command, please try again.");
        }
    }

    // MODIFIES: this
    // EFFECTS: changes the status for given media item
    public void changeStatus(Movie media) {
        while (true) {
            System.out.println("Enter the new status:");
            printStatusChoice();
            String status = scanner.nextLine();
            if (!(status.equals("w") || status.equals("c") || status.equals("t"))) {
                System.out.println("Invalid command, please try again.");
                continue;
            }

            media.setStatus(status);
            break;
        }
    }

    // REQUIRES: name length > 0
    // MODIFIES: this
    // EFFECTS: changes the name for given media item
    public void changeName(Movie media) {
        System.out.println("Enter the new name:");
        String name = scanner.nextLine();

        media.setName(name);
    }

    // REQUIRES: genre length > 0
    // MODIFIES: this
    // EFFECTS: changes the genre for given media item
    public void changeGenre(Movie media) {
        System.out.println("Enter the new genre:");
        String genre = scanner.nextLine();

        media.setGenre(genre);
    }

    // MODIFIES: this
     // EFFECTS: changes the notes for given media item   
    public void changeNotes(Movie media) {
        while (true) {
            System.out.println("Enter an option:");
            System.out.println("a - add note");
            System.out.println("r - remove note");
            String choice = scanner.nextLine();

            if (choice.equals("a")) {
                addNote(media);
            } else if (choice.equals("r")) {
                removeNote(media);
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
    // EFFECTS: adds a note for given media item
    public void addNote(Movie media) {
        while (true) {
            System.out.println("Enter the note to be added:");
            String note = scanner.nextLine();

            media.addNote(note);
            
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

    // REQUIRES: media has at least one note AND num <= note size for the media item
    // MODIFIES: this
    // EFFECTS: removes a note for given media item
    public void removeNote(Movie media) {
        while (true) {
            System.out.println("Enter the number of the note to be removed:");
            System.out.println(media.getNotes());
            int num = Integer.valueOf(scanner.nextLine());

            media.removeNote(num);
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
    // EFFECTS: changes the rating for given media item
    public void changeRating(Movie media) {
        System.out.println("Enter the new rating (1-5):");
        int rating = Integer.valueOf(scanner.nextLine());

        media.setRating(rating);
    }

    // REQUIRES: watchTime > 0
    // MODIFIES: this
    // EFFECTS: changes the watch time for given media item
    public void changeWatchTime(Movie media) {
        System.out.println("Enter the watch time to be added:");
        int watchTime = Integer.valueOf(scanner.nextLine());

        media.addWatchTime(watchTime);
    }

    // REQUIRES: episode > 0
    // MODIFIES: this
    // EFFECTS: changes the episode for given media item
    public void changeEpisode(Show show) {
        System.out.println("Enter the new episode:");
        int episode = Integer.valueOf(scanner.nextLine());

        show.setEpisode(episode);
    }

    // REQUIRES: season > 0
    // MODIFIES: this
    // EFFECTS: changes the season for given media item
    public void changeSeason(Show show) {
        System.out.println("Enter the new season:");
        int season = Integer.valueOf(scanner.nextLine());

        show.setSeason(season);
    }

    // REQUIRES: media is of type Movie
    // EFFECTS: prints all info for given movie
    public void printMediaInfoMovie(Movie media) {
        System.out.println("Status: " + media.getStatus());
        System.out.println("Name: " + media.getName());
        System.out.println("Genre: " + media.getGenre());
        System.out.println("Rating: " + media.getRating() + "/5");
        System.out.println("Watch time: " + media.getWatchTime());
        System.out.println("Notes:");
        System.out.println(media.getNotes());
    }

    // REQUIRES: show is of type show
    // EFFECTS: prints all info for given show
    public void printMediaInfoShow(Show show) {
        System.out.println("Status: " + show.getStatus());
        System.out.println("Name: " + show.getName());
        System.out.println("Genre: " + show.getGenre());
        if (show.getRating() == 0) {
            System.out.println("Rating: No rating");
        } else {
            System.out.println("Rating: " + show.getRating() + "/5");
        }
        System.out.println("Watch time: " + show.getWatchTime());
        if (mediaList.isShow(show)) {
            System.out.println("Season: " + show.getSeason());
            System.out.println("Episode: " + show.getEpisode());
        }
        System.out.println("Notes:");
        System.out.println(show.getNotes());
    }

    // EFFECTS: prints the status choices
    public void printStatusChoice() {
        System.out.println("w - watching");
        System.out.println("c - currently watching");
        System.out.println("t - to-watch");
    }

    // EFFECTS: prints the type choices
    public void printTypeChoice() {
        System.out.println("s - show");
        System.out.println("m - movie");
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

    // EFFECTS: prints the change options for a show
    public void printChangeOptionsShow() {
        printChangeOptionsMovie();
        System.out.println("e - episode");
        System.out.println("se - season");
    }
}
