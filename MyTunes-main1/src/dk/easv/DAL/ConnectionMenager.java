package dk.easv.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dk.easv.BE.SongClass;
import dk.easv.gui.Main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.management.remote.JMXConnectorFactory.connect;


public class ConnectionMenager {
    private SQLServerDataSource ds;

    public ConnectionMenager() {

    }



        public void conect(){


        ds = new SQLServerDataSource();
        ds.setDatabaseName("MyTunes-vedrancop");
        ds.setUser("CSe2023b_e_27");
        ds.setPassword("CSe2023bE27#23");
        ds.setPortNumber(1433);
        ds.setServerName("10.176.111.34");
        ds.setTrustServerCertificate(true);





        System.out.println("Done");


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
                double time = rs.getDouble("Time");
                String file = rs.getString("File");



                System.out.println(title + ", "+ artist + ", " + category + " " + time + " " + file);
            }
        }

        catch (
                SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Connection getConnection() throws SQLServerException {
        // Make sure ds is not null before trying to get a connection.
        if (ds == null) {
            conect();
        }
        return ds.getConnection();
    }

    public void AllSongs(){

        List<SongClass> songs = new ArrayList<>();
        ds = new SQLServerDataSource();
        ds.setDatabaseName("MyTunes-vedrancop");
        ds.setUser("CSe2023b_e_27");
        ds.setPassword("CSe2023bE27#23");
        ds.setPortNumber(1433);
        ds.setServerName("10.176.111.34");
        ds.setTrustServerCertificate(true);





        System.out.println("Done");


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
                double time = rs.getDouble("Time");
                String file = rs.getString("File");


                SongClass song = new SongClass(title, artist, category, time, file);
                songs.add(song);

            }
        }

        catch (
                SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    }

