package dk.easv.DAL;

import dk.easv.BE.SongClass;
import dk.easv.gui.songs.NewSongController;

import java.util.List;

public interface ISongDAO {

    public SongClass getSong(int id);
    public void createSong(SongClass s);
    public void updateSong(SongClass s);
    public void deleteSong(String s);

    public List<SongClass> getAllSongs();

}
