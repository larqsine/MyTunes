package dk.easv.DAL;

import dk.easv.BE.SongClass;

public interface ISongDAO {

    public SongClass getSong(int id);
    public void createSong(SongClass s);
    public void updateSong(SongClass s);
    public void deleteSong(int id);

}
