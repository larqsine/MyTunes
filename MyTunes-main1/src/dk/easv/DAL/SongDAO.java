package dk.easv.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import dk.easv.BE.SongClass;
import dk.easv.gui.Main;
import dk.easv.gui.songs.NewSongController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.title;

public class SongDAO implements ISongDAO{
    private  final ConnectionMenager cm= new ConnectionMenager();
    @Override
    public SongClass getSong(int id) {
        try (Connection con = cm.getConnection()) {
            String sql = "SELECT * FROM Song WHERE id=?";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        int id1 = rs.getInt("id");
                        String title = rs.getString("Title");
                        String artist = rs.getString("Artist");
                        String category = rs.getString("Category");
                        Double time = rs.getDouble("Time");
                        String file = rs.getString("File");

                        SongClass s = new SongClass();
                        // Set properties of s here
                        return s;
                    }
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createSong(SongClass s) {
        try (Connection con = cm.getConnection()) {
            String sql = "INSERT INTO Song(Title, Artist, Category, Time, [File]) VALUES (?,?,?,?,?)";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setString(1, String.valueOf(s.getTitle()));
                pstmt.setString(2, String.valueOf(s.getArtist()));
                pstmt.setString(3, String.valueOf(s.getCategory()));
                // Check if time is not null before using it
                if (s.getTime() != null) {
                    pstmt.setDouble(4, Double.parseDouble(String.valueOf(s.getTime())));
                } else {
                    // Decide on a default value or behavior when time is null
                    pstmt.setNull(4, java.sql.Types.DOUBLE);
                }

                pstmt.setString(5, String.valueOf(s.getFile()));

                pstmt.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateSong(NewSongController s) {

    }

    @Override
    public void deleteSong(int id) {

    }

    @Override
     public List<SongClass> getAllSongs() {

        List<SongClass> songs = new ArrayList<>();



        try (Connection con = cm.getConnection()) {
            String sql = "SELECT * FROM Song";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String title = rs.getString("Title");
                String artist = rs.getString("Artist");
                String category = rs.getString("Category");
                Double time = rs.getDouble("Time");
                String file = rs.getString("File");


                SongClass song = new SongClass(title, artist, category, time, file);
                songs.add(song);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return songs;
    }
}
