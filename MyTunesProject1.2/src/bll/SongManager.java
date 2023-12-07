package bll;

import be.Song;
import dal.Database;

import java.util.List;

public class SongManager {
    private Database database;

    public SongManager(Database database) {
        this.database = database;
    }

    public List<Song> getAllSongs() {
        return database.getAllSongs();
    }
}
