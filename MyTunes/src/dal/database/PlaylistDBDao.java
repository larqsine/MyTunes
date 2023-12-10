package dal.database;

import be.Playlists;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PlaylistDBDao {
    private static DBConnection dbConnection;

    private static ObservableList<Playlists> allPlaylists = FXCollections.observableArrayList();

    public PlaylistDBDao (){
        dbConnection = new DBConnection();
    }

    public static ObservableList<Playlists> getAllPlaylists() throws SQLException {
        try (Connection connection = dbConnection.getConnection()) {
            String sql = "SELECT * FROM dbo.Playlists";
            Statement statement = connection.createStatement();
            if (statement.execute(sql)) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    int playlistid = resultSet.getInt("PlaylistId");
                    String name = resultSet.getString("Name");
                    int songs = resultSet.getInt("Songs");
                    int time = resultSet.getInt("Time");
                }
            }
        }
    return null ;}

}
