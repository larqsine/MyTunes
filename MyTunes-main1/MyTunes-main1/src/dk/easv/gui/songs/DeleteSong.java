package dk.easv.gui.songs;

import dk.easv.BE.SongClass;
import dk.easv.gui.FxmlViewController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class DeleteSong {
    public Button BTNCancleNewSong;
    public Button BTNSaveSong;
    public TextField CategoryInput;
    public TextField TitleInput;
    public TextField ArtistInput;
    public TextField TimeInput;

    private FxmlViewController f;


    public void ClickCancle(ActionEvent actionEvent) {
        // get a handle to the stage
        Stage stage = (Stage) BTNCancleNewSong.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
    public void setFxmlViewController( FxmlViewController fxmlViewController){

        this.f=fxmlViewController;
    }



}
