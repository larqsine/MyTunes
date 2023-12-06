package dk.easv.gui;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dk.easv.DAL.ConnectionMenager;
import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
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

    public static void main(String[] args) throws SQLServerException {
        /*SQLServerDataSource ds = new SQLServerDataSource();
        ds.setDatabaseName("MyTunes-vedrancop");
        ds.setUser("CSe2023b_e_27");
        ds.setPassword("CSe2023bE27#23");
        ds.setPortNumber(1433);
        ds.setServerName("10.176.111.34");
        ds.setTrustServerCertificate(true);





        System.out.println("Done");
        ConnectionMenager connectionMenager= new ConnectionMenager();


        try(
                Connection con = ds.getConnection())
        {
            String sql = "SELECT * FROM Song";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String title         = rs.getString("Title");
                String artist   = rs.getString("Artist");
                String category   = rs.getString("Category");
                Double time = Double.valueOf(rs.getString("Time"));
                String file = rs.getString("File");

                System.out.println(title + ", "+ artist + ", " + category + " " + time + " " + file);
            }
        }

        catch (
                SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }*/

        ConnectionMenager connectionMenager= new ConnectionMenager();
        connectionMenager.conect();
        launch(args);
    }
}