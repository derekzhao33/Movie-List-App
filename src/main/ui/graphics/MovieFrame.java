package ui.graphics;

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
        this.movieList = new MovieList();
        this.jsonWriter = new JsonWriter(JSON_STORE);
        this.jsonReader = new JsonReader(JSON_STORE);
        this.movieList = new MovieList();
        this.cardPanel = new CardPanel(this.movieList);
        this.menuHandler = new MenuHandler(this);

        initializeFrame();

        this.setVisible(true);
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
    }

    // EFFECTS: handles the actions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuHandler.getLoadMenuItem()) {
            loadMovieList();
        } else if (e.getSource() == menuHandler.getSaveMenuItem()) {
            saveMovieList();
        } else if (e.getSource() == menuHandler.getAddMenuItem()) {
            this.cardPanel.switchPanels("add", this.movieList);
        } else if (e.getSource() == menuHandler.getRemoveMenuItem()) {
            this.cardPanel.switchPanels("remove", this.movieList);
        } else if (e.getSource() == menuHandler.getDisplayMenuItem()) {
            this.cardPanel.switchPanels("display", this.movieList);
        } else if (e.getSource() == menuHandler.getFilterStatusMenuItem()) {
            this.cardPanel.switchPanels("filter status", this.movieList);
        } else if (e.getSource() == menuHandler.getFilterGenreMenuItem()) {
            this.cardPanel.switchPanels("filter genre", this.movieList);
        }
    }

    // EFFECTS: loads the movie list from file
    private void loadMovieList() {
        try {
            this.movieList = jsonReader.read();
            JOptionPane.showMessageDialog(this, "Loaded movie list from: " + JSON_STORE, 
                                        "Load", JOptionPane.INFORMATION_MESSAGE);
            this.cardPanel.initializePanels(this.movieList);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unable to load movies", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // EFFECTS: saves the movie list to file
    private void saveMovieList() {
        try {
            this.jsonWriter.open();
            this.jsonWriter.write(movieList);
            this.jsonWriter.close();
            JOptionPane.showMessageDialog(this, "Saved movie list to: " + JSON_STORE, 
                                        "Save", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException e) {
            textArea.setText("Unable to write to file: " + JSON_STORE);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'windowOpened'");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'windowClosing'");
    }

    @Override
    public void windowClosed(WindowEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'windowClosed'");
    }

    @Override
    public void windowIconified(WindowEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'windowIconified'");
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'windowDeiconified'");
    }

    @Override
    public void windowActivated(WindowEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'windowActivated'");
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'windowDeactivated'");
    }
}