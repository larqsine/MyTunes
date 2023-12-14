package dal;

import be.Playlist;
import be.Song;
import com.sun.tools.javac.Main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseData {

    private DatabaseConnection connection = new DatabaseConnection();

    public List<Playlist> getAllPlaylists() {

        List<Playlist> playlists = new ArrayList<>();

        try (Connection con = connection.getConnection()) {
            String sql = "SELECT * FROM dbo.PlaylistsMy";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String name = rs.getString("Name");
                int songs = rs.getInt("Songs");
                String time = rs.getString("Time");

                Playlist playlist = new Playlist(name, songs, time);
                playlists.add(playlist);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return playlists;
    }

    public List<Song> getAllSongs() {

        List<Song> songs = new ArrayList<>();

        try (Connection con = connection.getConnection()) {
            String sql = "SELECT * FROM dbo.SongMy";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String title = rs.getString("Title");
                String artist = rs.getString("Artist");
                String category = rs.getString("Category");
                String time = rs.getString("Time");

                Song song = new Song(title, artist, category, time);
                songs.add(song);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return songs;
    }
}
