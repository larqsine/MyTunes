package dal;

import be.Playlist;
import be.Song;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.sun.tools.javac.Main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    public List<Playlist> getAllPlaylists() {
        List<Playlist> playlists = new ArrayList<>();

        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setDatabaseName("CSe23B_28_MyTunes");
        ds.setUser("CSe2023b_e_28");
        ds.setPassword("CSe2023bE28#23");
        ds.setServerName("10.176.111.34");
        ds.setTrustServerCertificate(true);

        try (Connection con = ds.getConnection()) {
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

        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setDatabaseName("CSe23B_28_MyTunes");
        ds.setUser("CSe2023b_e_28");
        ds.setPassword("CSe2023bE28#23");
        ds.setServerName("10.176.111.34");
        ds.setTrustServerCertificate(true);

        try (Connection con = ds.getConnection()) {
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