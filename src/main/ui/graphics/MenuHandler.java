package ui.graphics;

import javax.swing.*;
import java.awt.event.ActionListener;

// Represents a menu handler for the movie list application
public class MenuHandler {
    private JMenuBar menuBar;
    private JMenu movieMenu;
    private JMenu fileMenu;
    private JMenu changeMenu;
    private JMenuItem load;
    private JMenuItem save;
    private JMenuItem add;
    private JMenuItem remove;
    private JMenuItem display;

    // EFFECTS: creates a new MenuHandler
    public MenuHandler(ActionListener listener) {
        menuBar = new JMenuBar();
        movieMenu = new JMenu("Movie");
        fileMenu = new JMenu("File");
        changeMenu = new JMenu("Change");

        load = new JMenuItem("Load");
        save = new JMenuItem("Save");
        add = new JMenuItem("Add");
        remove = new JMenuItem("Remove");
        display = new JMenuItem("Display");

        initializeMenus(listener);
    }

    // MODIFIES: this
    // EFFECTS: initializes the menus
    private void initializeMenus(ActionListener listener) {
        load.addActionListener(listener);
        save.addActionListener(listener);
        add.addActionListener(listener);
        remove.addActionListener(listener);
        display.addActionListener(listener);

        fileMenu.add(load);
        fileMenu.add(save);
        movieMenu.add(add);
        movieMenu.add(remove);
        movieMenu.add(display);

        menuBar.add(movieMenu);
        menuBar.add(fileMenu);
        menuBar.add(changeMenu);
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
}