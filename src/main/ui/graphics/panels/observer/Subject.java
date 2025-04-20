package ui.graphics.panels.observer;

import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;

import model.MovieList;

public class Subject extends JPanel {
    private Set<Observer> observers;

    public Subject() {
        observers = new HashSet<>();
    }
    
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers(MovieList movieList) {
        for (Observer o : observers) {
            o.update(movieList);
        }
    }
}
