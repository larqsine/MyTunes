package bll;

import be.Playlist;
import be.Song;
import dal.DatabaseData;

import java.util.List;

public class DatabaseManager {

    private DatabaseData databaseData;

    public DatabaseManager(DatabaseData databaseData) {
        this.databaseData = databaseData;
    }

    public List<Playlist> getAllPlaylists() {
        return databaseData.getAllPlaylists();
    }

    public List<Song> getAllSongs() {
        return databaseData.getAllSongs();
    }
}
