package model;

// Represents a show, which is a subclass of Movie
// Apart from the information that Movie represents, Show keeps track of the current season and episode
public class Show implements MediaItem {

    private int season;
    private int episode;
    
    // REQUIRES: length of name and genre > 0 AND classifcation is one of: "Watched", "Currently Watching", "To-watch"
    // EFFECTS: creates a new show with status, name, and genre empty notes, 
    //          no rating (0), zero watchTime, 0 episode number, and 0 season number
    public Show(String status, String name, String genre) {
        super(status, name, genre);
    }

    public int getSeason() {
        return this.season; 
    }

    public int getEpisode() {
        return this.episode;
    }

    // REQUIRES: season > 0;
    public void setSeason(int season) {
        this.season = season;
    }
    
    // REQUIRES: episode > 0;
    public void setEpisode(int episode) {
        this.episode = episode;
    }
}
