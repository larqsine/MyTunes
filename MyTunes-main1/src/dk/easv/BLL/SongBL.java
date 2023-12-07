package dk.easv.BLL;

import dk.easv.BE.SongClass;
import dk.easv.DAL.SongDAO;
import dk.easv.gui.songs.NewSongController;
import javafx.scene.control.TextField;

import java.util.List;

public class SongBL {

    private int savenumber = 0;

    SongDAO songDAO=new SongDAO();


 public void checkField(TextField input, String fieldLabel) {
     if (input != null) {
         if (input.getText().isEmpty()) {
             input.setText("invalid " + fieldLabel.toLowerCase());
             System.out.println("You set an invalid " + fieldLabel + ". " + fieldLabel + " has to contain a value.");
             savenumber = 0;
         }
         if(input.getText().length()>0){
             input.getText().trim();
             savenumber=1;



         }
     } else {
         System.out.println(fieldLabel + " input is null. Make sure it is properly initialized.");
     }
 }

 public void saveSong(SongClass s){
     if(savenumber == 1){
         songDAO.createSong(s);
     }

 }
 public List<SongClass> getAllSongs(){
     return songDAO.getAllSongs();
 }

}