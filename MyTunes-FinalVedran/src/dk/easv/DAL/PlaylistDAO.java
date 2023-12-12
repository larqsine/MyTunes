package dk.easv.DAL;

import dk.easv.BE.Playlist;
import dk.easv.gui.Main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlaylistDAO {
    private  final ConnectionMenager cm= new ConnectionMenager();
    public List<Playlist> getAllPlaylist() {

        List<Playlist> playlists = new ArrayList<>();



        try (Connection con = cm.getConnection()) {
            String sql = "SELECT * FROM Playlist";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("Name");

                Playlist playlist = new Playlist(name);
                playlists.add(playlist);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return playlists;
    }
}
