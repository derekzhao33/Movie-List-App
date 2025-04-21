# Movie List App

## Overview
The **Movie List App** is a personal project designed to help users manage their movie-watching habits. It allows users to keep track of movies they have watched, are currently watching, or plan to watch in the future. The application provides features such as adding notes or reviews, filtering movies by status or genre, and editing movie details. 

This project was inspired by my love for movies and the need for a simple, organized way to track my movie-watching journey. 

## Key Features:
- **Categorization**: Classify movies as "Watched," "Currently Watching," or "To-watch."
- **Notes and Reviews**: Add personal notes or reviews for each movie.
- **Filtering**: Search and filter movies by status or genre.
- **Editing**: Update movie details such as status, name, genre, notes, ratings, and watch time.
- **Persistence**: Save and load your movie list to/from a JSON file.

## Technologies Used
- **Java**: Core programming language used for the application.
- **Swing**: For building the graphical user interface (GUI).
- **JUnit 5**: For writing and running unit tests.
- **JSON**: For saving and loading movie data to/from files.

## Known Issues
- The `ChangePanel` may not correctly update the watch time or rating if invalid input is entered.
- The `FilterStatusPanel` and `FilterGenrePanel` may not update dynamically when the movie list is modified.
- The `JScrollPane` in `DisplayInfoPanel` may exceed the application bounds in certain window sizes.

## Prerequisites
- **Java 17** or later installed on your system.
- A Java IDE like **IntelliJ IDEA**, **Eclipse**, or **Visual Studio Code** (optional but recommended).
- The following libraries included in the project:
  - `lib/json-20240303.jar` (for JSON persistence)
  - `lib/junit-platform-console-standalone-1.10.2.jar` (for running tests)

## Steps to Build
1. Clone the repository:
   ```bash
   git clone https://github.com/derekzhao33/Movie-List-App.git

## Steps to Run

1. **To run the console-based app**:
   - Open a terminal and navigate to the project directory.
   - Run the following command:
     ```bash
     java -cp "bin;lib/*" ui.Main
     ```

2. **To run the GUI**:
   - Open a terminal and navigate to the project directory.
   - Run the following command:
     ```bash
     java -cp "bin;lib/*" ui.MainGUI
     ```

3. **To run without commands**:
   - Open the project in your IDE (e.g., IntelliJ IDEA, Eclipse, or Visual Studio Code).
   - Locate the `Main` class for the console-based app or the `MainGUI` class for the GUI.
   - Right-click on the class and select `Run` or `Debug` to start the application.

## Steps to run tests
- Ensure the project is built and the test libraries are included in the build path.
- Run the test suite using your IDE's test runner (e.g., JUnit 5).

## Author/Contact Information
This project was developed by Derek Zhao. For any questions, feedback, or bug reports, please contact:
- **Email**: derekzhao33@gmail.com
- **GitHub**: [https://github.com/derekzhao33](https://github.com/derekzhao33)

---