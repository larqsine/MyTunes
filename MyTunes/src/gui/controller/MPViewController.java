package gui.controller;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MPViewController {


    @FXML
    public void openEditWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/EditView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Edit Playlist");
            stage.setScene(new Scene(root));

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openNewWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/NewView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("New Playlist");
            stage.setScene(new Scene(root));

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*private void sqlSetup() {
        try {
            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setDatabaseName("CSe2023b_e_Mytunes");
            ds.setUser("CSe2023b_e_23");
            ds.setPassword("CSe2023bE23#23");
            ds.setServerName("10.176.111.34");
            ds.setTrustServerCertificate(true);

            try (Connection con = ds.getConnection()) {
                String sql = "SELECT * FROM dbo.Playlists";
                try (Statement stmt = con.createStatement();
                     ResultSet rs = stmt.executeQuery(sql)) {
                    while (rs.next()) {
                        String name = rs.getString("Name");
                        int songs = rs.getInt("Songs");
                        String time = rs.getString("Time");
                        System.out.println(name + ", " + songs + ", " + time);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(MPViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}