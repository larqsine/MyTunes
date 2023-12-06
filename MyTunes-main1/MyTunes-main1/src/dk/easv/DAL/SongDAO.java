package dk.easv.DAL;

import dk.easv.BE.SongClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SongDAO implements ISongDAO{
    private  final ConnectionMenager cm= new ConnectionMenager();
    @Override
    public SongClass getSong(int id) {
        {
            try(Connection con = cm.getConnection())
            {
                String sql = "SELECT * FROM Song WHERE id=?";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setInt(1,id);
                ResultSet rs = pstmt.executeQuery();
                while(rs.next()){
                    int id1         = rs.getInt("id");
                    String title     = rs.getString("Title");
                    String artist    = rs.getString("Artist");
                    String category  =rs.getString("Category");
                    Double time      = rs.getDouble("Time");
                    String file      =rs.getString("File");

                    SongClass s = new SongClass();
                    return s;
                }
                return null;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void createSong(SongClass s) {

    }

    @Override
    public void updateSong(SongClass s) {

    }

    @Override
    public void deleteSong(int id) {

    }
}
