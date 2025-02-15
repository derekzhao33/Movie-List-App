package model;

// Represents a show, which is a subclass of Movie
// Apart from the information that Movie represents, Show keeps track of the current season and episode
public class Show extends Movie {

    private int season;
    private int episode;
    
    // REQUIRES: length of name and genre > 0 AND classifcation is one of: "Watched", "Currently Watching", "To-watch"
    // EFFECTS: creates a new show with status, name, and genre empty notes, 
    //          no rating (0), zero watchTime, 0 episode number, and 0 season number
    public Show(String status, String name, String genre) {
        super(status, name, genre);
    }

    public int getSeason() {
        return -1; // stub
    }

    public int getEpisode() {
        return -1; // stub
    }

    // REQUIRES: season > 0;
    public void setSeason(int season) {
        // TODO
    }
    
    // REQUIRES: episode > 0;
    public void setEpisode(int episode) {
        // TODO
    }

    // REQUIRES: compared is Movie or Show
    // EFFECTS: compares the type of compared to this
    @Override
    public boolean equals(Object compared) {
        return false; // stub
    }
}
