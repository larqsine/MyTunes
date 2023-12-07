package dk.easv.DAL;

import dk.easv.BE.SongClass;
import dk.easv.gui.songs.NewSongController;

import java.util.List;

public interface ISongDAO {

    public SongClass getSong(int id);
    public void createSong(SongClass s);
    public void updateSong(NewSongController s);
    public void deleteSong(int id);

    public List<SongClass> getAllSongs();

}