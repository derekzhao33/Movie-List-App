package ui;

import java.util.*;
import model.*;

public class MediaListApp {
    
    private MediaList mediaList;
    private Scanner scanner;

    public MediaListApp() {
        this.mediaList = new MediaList();
        this.scanner = new Scanner(System.in);
        start();
    }

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

    public void addMedia() {
        while (true) {
            System.out.println("Enter the status of the new media item:");
            printStatusChoice();
            String status = scanner.nextLine();
            
            if (!status.equals("w") || !status.equals("c") || !status.equals("t")) {
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

    public void printMedia() {
        System.out.println("Printing all media items:");
        System.out.println(mediaList.getAllNames());
        start();
    }

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
                
                if (!status.equals("w") || !status.equals("c") || !status.equals("t")) {
                    System.out.println("Invalid command, please try again.");
                    continue;
                }

                System.out.println(mediaList.getAllNamesByStatus(status));
                break;
            } else if (filterBy.equals("t")) {
                System.out.println("Enter the type to filter by:");
                printTypeChoice();
                String type = scanner.nextLine();
                System.out.println(mediaList.getAllNamesByType(type));
                break;
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

    public void changeMedia() {
        while (true) {
            System.out.println("Enter the number of the media item to be changed:");
            System.out.print(mediaList.getAllNames());
            int selNum = Integer.valueOf(scanner.nextLine());
            Movie selected = mediaList.searchName(selNum);

            if (mediaList.isShow(selected)) {
                printChangeOptionsShow();
                String commandShow = scanner.nextLine();
                Show selectedShow = new Show(selected.getStatus(), selected.getName(), selected.getGenre());

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
        }
    }

    public void changeStatus(Movie media) {
        while (true) {
            System.out.println("Enter the new status:");
            printStatusChoice();
            String status = scanner.nextLine();
            if (!status.equals("w") || !status.equals("c") || !status.equals("t")) {
                System.out.println("Invalid command, please try again.");
                continue;
            }

            media.setStatus(status);
            break;
        }
    }

    public void changeName(Movie media) {
        System.out.println("Enter the new name:");
        String name = scanner.nextLine();

        media.setName(name);
    }

    public void changeGenre(Movie media) {
        System.out.println("Enter the new genre:");
        String genre = scanner.nextLine();

        media.setGenre(genre);
    }

    public void changeNotes(Movie media) {
        while (true) {
            System.out.println("Enter an option: ");
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
        }
    }

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

    public void removeNote(Movie media) {
        while (true) {
            System.out.println("Enter the number of the note to be removed:");
            media.getNotes();
            int num = Integer.valueOf(scanner.nextLine());

            media.removeNote(num);

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

    public void changeRating(Movie media) {
        System.out.println("Enter the new rating (1-5):");
        int rating = Integer.valueOf(scanner.nextLine());

        media.setRating(rating);
    }

    public void changeWatchTime(Movie media) {
        System.out.println("Enter the watch time to be added:");
        int watchTime = Integer.valueOf(scanner.nextLine());

        media.addWatchTime(watchTime);
    }

    public void changeEpisode(Show show) {
        System.out.println("Enter the new episode:");
        int episode = Integer.valueOf(scanner.nextLine());

        show.setEpisode(episode);
    }

    public void changeSeason(Show show) {
        System.out.println("Enter the new season:");
        int season = Integer.valueOf(scanner.nextLine());

        show.setSeason(season);
    }

    public void printMediaInfoMovie(Movie media) {
        System.out.println("Status: " + media.getStatus());
        System.out.println("Name: " + media.getName());
        System.out.println("Genre: " + media.getGenre());
        System.out.println("Rating: " + media.getRating() + "/5");
        System.out.println("Watch time: " + media.getWatchTime());
        System.out.println("Notes:");
        System.out.println(media.getNotes());
    }

    public void printMediaInfoShow(Show show) {
        System.out.println("Status: " + show.getStatus());
        System.out.println("Name: " + show.getName());
        System.out.println("Genre: " + show.getGenre());
        System.out.println("Rating: " + show.getRating() + "/5");
        System.out.println("Watch time: " + show.getWatchTime());
        if (mediaList.isShow(show)) {
            System.out.println("Season: " + show.getSeason());
            System.out.println("Episode: " + show.getEpisode());
        }
        System.out.println("Notes:");
        System.out.println(show.getNotes());
    }

    public void printStatusChoice() {
        System.out.println("w - watching");
        System.out.println("c - currently watching");
        System.out.println("t - to-watch");
    }

    public void printTypeChoice() {
        System.out.println("s - show");
        System.out.println("m - movie");
    }

    public void printYesNo() {
        System.out.println("y - yes");
        System.out.println("n - no");
    }

    public void printChangeOptionsMovie() {
        System.out.println("Enter the detail to be changed:");
        System.out.println("s - status");
        System.out.println("nm - name");
        System.out.println("g - genre");
        System.out.println("m - notes");
        System.out.println("r - rating");
        System.out.println("w - watch time");
    }

    public void printChangeOptionsShow() {
        printChangeOptionsMovie();
        System.out.println("e - episode");
        System.out.println("se - season");
    }
}
