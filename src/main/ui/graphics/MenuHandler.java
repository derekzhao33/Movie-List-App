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
    private JMenuItem filterStatus;

    // EFFECTS: creates a new MenuHandler
    public MenuHandler(ActionListener listener) {
        this.menuBar = new JMenuBar();
        this.movieMenu = new JMenu("Movie");
        fileMenu = new JMenu("File");
        changeMenu = new JMenu("Change");

        this.load = new JMenuItem("Load");
        this.save = new JMenuItem("Save");
        this.add = new JMenuItem("Add");
        this.remove = new JMenuItem("Remove");
        this.display = new JMenuItem("Display");
        this.filterStatus = new JMenuItem("Filter Status");

        initializeMenus(listener);
    }

    // MODIFIES: this
    // EFFECTS: initializes the menus
    private void initializeMenus(ActionListener listener) {
        this.load.addActionListener(listener);
        this.save.addActionListener(listener);
        this.add.addActionListener(listener);
        this.remove.addActionListener(listener);
        this.display.addActionListener(listener);
        this.filterStatus.addActionListener(listener);

        this.fileMenu.add(this.load);
        this.fileMenu.add(this.save);
        this.movieMenu.add(this.add);
        this.movieMenu.add(this.remove);
        this.movieMenu.add(this.display);
        this.movieMenu.add(this.filterStatus);

        this.menuBar.add(this.movieMenu);
        this.menuBar.add(this.fileMenu);
        this.menuBar.add(this.changeMenu);
    }

    public JMenuBar getMenuBar() {
        return this.menuBar;
    }

    public JMenuItem getLoadMenuItem() {
        return this.load;
    }

    public JMenuItem getSaveMenuItem() {
        return this.save;
    }

    public JMenuItem getAddMenuItem() {
        return this.add;
    }

    public JMenuItem getRemoveMenuItem() {
        return this.remove;
    }

    public JMenuItem getDisplayMenuItem() {
        return this.display;
    }

    public JMenuItem getFilterStatusMenuItem() {
        return this.filterStatus;
    }
}