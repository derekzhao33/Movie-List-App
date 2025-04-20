package ui.graphics.panels.observer;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import model.MovieList;

public class Subject extends JPanel {
    private List<Observer> observers;

    public Subject() {
        observers = new ArrayList<>();
    }
    
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    
    public void notifyObserver(MovieList movieList) {
        for (Observer o : observers) {
            o.update(movieList);
        }
    }
}
