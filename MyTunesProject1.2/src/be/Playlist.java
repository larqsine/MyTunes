package be;

public class Playlist {
    private String name;
    private int songs;
    private String time;

    public Playlist(String name, int songs, String time) {
        this.name = name;
        this.songs = songs;
        this.time = time;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSongs() {
        return songs;
    }

    public void setSongs(int songs) {
        this.songs = songs;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
