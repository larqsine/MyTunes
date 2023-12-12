package dk.easv.BLL;

import javafx.scene.control.TextField;

public class PlaylistBL {
    public void checkField(TextField input) {
        if (input != null) {
            if (input.getText().isEmpty()) {
                input.setText("invalid Name");
                System.out.println("You set an invalid Name" +  "Name has to contain a value.");

            }
            if(input.getText().length()>0){
                input.getText().trim();




            }
        } else {
            System.out.println(" Name input is null. Make sure it is properly initialized.");
        }
    }
}
