package ui.graphics;

import javax.swing.*;
import java.awt.event.ActionListener;

// Represents a menu handler for the movie list application
public class MenuHandler {
    private static MenuHandler singleton;
    private JMenuBar menuBar;
    private JMenu movieMenu;
    private JMenu fileMenu;
    private JMenu filterMenu;
    private JMenuItem load;
    private JMenuItem save;
    private JMenuItem add;
    private JMenuItem remove;
    private JMenuItem display;
    private JMenuItem filterStatus;
    private JMenuItem filterGenre;

    // EFFECTS: creates a new MenuHandler
    private MenuHandler(ActionListener listener) {
        menuBar = new JMenuBar();
        movieMenu = new JMenu("Movie");
        fileMenu = new JMenu("File");
        filterMenu = new JMenu("Filter");

        load = new JMenuItem("Load");
        save = new JMenuItem("Save");
        add = new JMenuItem("Add");
        remove = new JMenuItem("Remove");
        display = new JMenuItem("Display");
        filterStatus = new JMenuItem("Status");
        filterGenre = new JMenuItem("Genre");

        initializeMenus(listener);
    }

    public static MenuHandler getInstance(ActionListener listener) {
        if (singleton == null) {
            singleton = new MenuHandler(listener);
        } 

        return singleton;
    }

    // MODIFIES: this
    // EFFECTS: initializes the menus
    private void initializeMenus(ActionListener listener) {
        load.addActionListener(listener);
        save.addActionListener(listener);
        add.addActionListener(listener);
        remove.addActionListener(listener);
        display.addActionListener(listener);
        filterStatus.addActionListener(listener);
        filterGenre.addActionListener(listener);

        fileMenu.add(load);
        fileMenu.add(save);

        filterMenu.add(filterStatus);
        filterMenu.add(filterGenre);
        
        movieMenu.add(add);
        movieMenu.add(remove);
        movieMenu.add(display);
        movieMenu.add(filterMenu);

        menuBar.add(movieMenu);
        menuBar.add(fileMenu);
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public JMenuItem getLoadMenuItem() {
        return load;
    }

    public JMenuItem getSaveMenuItem() {
        return save;
    }

    public JMenuItem getAddMenuItem() {
        return add;
    }

    public JMenuItem getRemoveMenuItem() {
        return remove;
    }

    public JMenuItem getDisplayMenuItem() {
        return display;
    }

    public JMenuItem getFilterStatusMenuItem() {
        return filterStatus;
    }

    public JMenuItem getFilterGenreMenuItem() {
        return filterGenre;
    }
}