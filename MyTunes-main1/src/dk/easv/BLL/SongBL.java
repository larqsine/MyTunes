package dk.easv.BLL;

import dk.easv.BE.SongClass;
import dk.easv.DAL.SongDAO;
import dk.easv.gui.songs.NewSongController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

import java.util.List;

public class SongBL {

    private ObservableList<SongClass> songlist;



    SongDAO songDAO=new SongDAO();


 public void checkField(TextField input, String fieldLabel) {
     if (input != null) {
         if (input.getText().isEmpty()) {
             input.setText("invalid " + fieldLabel.toLowerCase());
             System.out.println("You set an invalid " + fieldLabel + ". " + fieldLabel + " has to contain a value.");

         }
         if(input.getText().length()>0){
             input.getText().trim();




         }
     } else {
         System.out.println(fieldLabel + " input is null. Make sure it is properly initialized.");
     }
 }

 public void saveSong(SongClass s){

         songDAO.createSong(s);
     }


 public List<SongClass> getAllSongs(){
     return songDAO.getAllSongs();
 }

 public ObservableList<SongClass> getSonglist(){
     songlist= FXCollections.observableArrayList();
     songlist.addAll(getAllSongs());
     return songlist;
 }
}