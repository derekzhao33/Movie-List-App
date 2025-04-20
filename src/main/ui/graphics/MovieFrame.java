package ui.graphics;

import model.EventLog;
import model.MovieList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;

import model.Event;

import ui.graphics.panels.CardPanel;

// Represents a frame for the movie list application
public class MovieFrame extends JFrame implements ActionListener, WindowListener {
    private static final String JSON_STORE = "./data/movieList.json";
    private static final String LOGO_PATH = "data/logo.png";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private CardPanel cardPanel;
    private MovieList movieList;
    private JTextArea textArea;
    private MenuHandler menuHandler;

    // EFFECTS: creates a new frame
    public MovieFrame() {
        movieList = new MovieList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        movieList = new MovieList();
        cardPanel = new CardPanel(movieList);
        menuHandler = MenuHandler.getInstance(this);

        initializeFrame();

        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initialized the frame
    public void initializeFrame() {
        setLocationRelativeTo(null);
        setTitle("Movie List Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        ImageIcon imageIcon = new ImageIcon(LOGO_PATH);
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(500, 500, Image.SCALE_DEFAULT);
        setIconImage(scaledImage);
        
        setLayout(new BorderLayout());
        setJMenuBar(menuHandler.getMenuBar());
        add(cardPanel);
        pack();
        addWindowListener(this);
    }

    // EFFECTS: handles the actions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuHandler.getLoadMenuItem()) {
            loadMovieList();
        } else if (e.getSource() == menuHandler.getSaveMenuItem()) {
            saveMovieList();
        } else if (e.getSource() == menuHandler.getAddMenuItem()) {
            cardPanel.switchPanels("add", movieList);
        } else if (e.getSource() == menuHandler.getRemoveMenuItem()) {
            cardPanel.switchPanels("remove", movieList);
        } else if (e.getSource() == menuHandler.getDisplayMenuItem()) {
            cardPanel.switchPanels("display", movieList);
        } else if (e.getSource() == menuHandler.getFilterStatusMenuItem()) {
            cardPanel.switchPanels("filter status", movieList);
        } else if (e.getSource() == menuHandler.getFilterGenreMenuItem()) {
            cardPanel.switchPanels("filter genre", movieList);
        } else if (e.getSource() == menuHandler.getChangMenuItem()) {
            cardPanel.switchPanels("change", movieList);
        }
    }

    // EFFECTS: loads the movie list from file
    private void loadMovieList() {
        try {
            movieList = jsonReader.read();
            JOptionPane.showMessageDialog(this, "Loaded movie list from: " + JSON_STORE, 
                                        "Load", JOptionPane.INFORMATION_MESSAGE);
            cardPanel.initializePanels(movieList);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unable to load movies", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // EFFECTS: saves the movie list to file
    private void saveMovieList() {
        try {
            jsonWriter.open();
            jsonWriter.write(movieList);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this, "Saved movie list to: " + JSON_STORE, 
                                        "Save", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException e) {
            textArea.setText("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: does nothing
    @Override
    public void windowOpened(WindowEvent e) {

    }

    // EFFECTS: prints out all events to the console
    @Override
    public void windowClosing(WindowEvent e) {
        printLog(EventLog.getInstance());
    }

    // EFFECTS: does nothing
    @Override
    public void windowClosed(WindowEvent e) {

    }

    // EFFECTS: does nothing
    @Override
    public void windowIconified(WindowEvent e) {

    }

    // EFFECTS: does nothing
    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    // EFFECTS: does nothing
    @Override
    public void windowActivated(WindowEvent e) {

    }

    // EFFECTS: does nothing
    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    // EFFECTS: prints out all events
    public void printLog(EventLog eventLog) {
        for (Event e : eventLog) {
            System.out.println(e.toString());
        }
    }
}