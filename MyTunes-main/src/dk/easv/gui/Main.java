package dk.easv.gui;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FxmlView.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("My-Tunes");
        stage.show();
    }

    public static void main(String[] args) {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setDatabaseName("CSe23B_28_MyTunes");
        ds.setUser("CSe2023b_e_28");
        ds.setPassword("CSe2023bE28#23");
        ds.setPortNumber(1433);
        ds.setServerName("10.176.111.34");
        ds.setTrustServerCertificate(true);





        System.out.println("Done");


        try(
                Connection con = ds.getConnection())
        {
            String sql = "SELECT * FROM Playlists";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String Name         = rs.getString("Name");
                int Songs    = rs.getInt("Songs");
                double Time    = rs.getDouble("Time");
                System.out.println(Name + ", "+ Songs + ", " + Time);
            }
        }

        catch (
                SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        launch(args);
    }
}