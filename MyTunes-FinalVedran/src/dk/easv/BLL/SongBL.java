package dk.easv.BLL;

import dk.easv.BE.SongClass;
import dk.easv.DAL.SongDAO;
import dk.easv.gui.songs.NewSongController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.Optional;

public class SongBL {

    public int saveNumber=0;

    private ObservableList<SongClass> songlist;



    SongDAO songDAO=new SongDAO();


 public void checkField(TextField input, String fieldLabel) {
     if (input != null) {
         if (input.getText().isEmpty()) {
             Alert AlertDialog = new Alert(Alert.AlertType.ERROR);
             AlertDialog.setTitle("Alert");
             AlertDialog.setHeaderText("You cant leave the fields empty");
             Optional<ButtonType> result = AlertDialog.showAndWait();
             saveNumber =0;

         }
         if(input.getText().length()>0){
             input.getText().trim();
             saveNumber =1;

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
     public void updateSong(SongClass s){

     songDAO.updateSong(s);
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