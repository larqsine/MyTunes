package bll;

import be.Playlist;
import dal.Database;

import java.util.List;

public class PlaylistManager {
    private Database database;

    public PlaylistManager(Database database) {
        this.database = database;
    }

    public List<Playlist> getAllPlaylists() {
        return database.getAllPlaylists();
    }
}
