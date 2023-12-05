package dk.easv.BLL;

import dk.easv.BE.SongClass;
import dk.easv.gui.songs.NewSongController;
import javafx.scene.control.TextField;

public class SongBL {


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

}