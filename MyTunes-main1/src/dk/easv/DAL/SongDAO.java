package dk.easv.DAL;

import dk.easv.BE.SongClass;
import dk.easv.gui.songs.NewSongController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        NewSongController n=new NewSongController();
        try (Connection con = cm.getConnection()) {
            String sql = "INSERT INTO Song(Title, Artist, Category, Time, [File]) VALUES (?,?,?,?,?)";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setString(1, String.valueOf(n.TitleInput));
                pstmt.setString(2, String.valueOf(n.ArtistInput));
                pstmt.setString(3, String.valueOf(n.CategoryInput));
                // Check if time is not null before using it
                if (n.TimeInput != null) {
                    pstmt.setDouble(4, Double.parseDouble(String.valueOf(n.TimeInput)));
                } else {
                    // Decide on a default value or behavior when time is null
                    pstmt.setNull(4, java.sql.Types.DOUBLE);
                }

                pstmt.setString(5, String.valueOf(n.FileInput));

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
        cm.AllSongs();
        return songs;

    }
}
