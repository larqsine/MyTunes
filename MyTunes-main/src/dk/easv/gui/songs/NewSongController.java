package dk.easv.gui.songs;

import dk.easv.gui.FxmlViewController;
import dk.easv.BE.SongClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;

public class NewSongController {
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

    public void ClickSave(ActionEvent actionEvent) throws IOException {

        SongClass songClass=new SongClass();
        songClass.setTitle(TitleInput.getText());
        songClass.setArtist(ArtistInput.getText());
        songClass.setCategory(CategoryInput.getText());
        songClass.setTime(Double.parseDouble(TimeInput.getText()));
        f.addSong(songClass);
        Stage currentStage = (Stage) BTNCancleNewSong.getScene().getWindow();
        currentStage.close();


    }

}
