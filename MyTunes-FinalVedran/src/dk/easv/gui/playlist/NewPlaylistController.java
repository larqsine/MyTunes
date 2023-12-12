package dk.easv.gui.playlist;

import dk.easv.BE.Playlist;
import dk.easv.BLL.PlaylistBL;
import dk.easv.gui.FxmlViewController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewPlaylistController {
    public TextField NewPlaylistInput;
    public Button SaveNewPlaylist;
    public Button BTNCancleNewPlaylist;
    private FxmlViewController f;

    public void setFxmlViewController(FxmlViewController fxmlViewController) {

        this.f = fxmlViewController;
    }

    public void ClickSaveBTN(ActionEvent actionEvent) {
        PlaylistBL playlistBL=new PlaylistBL();
        playlistBL.checkField(NewPlaylistInput);

        f.PlaylistListView.getItems().add(NewPlaylistInput.getText());
        Stage stage = (Stage) BTNCancleNewPlaylist.getScene().getWindow();
        stage.close();


    }

    public void ClickCancleBTN(ActionEvent actionEvent) {
        // get a handle to the stage
        Stage stage = (Stage) BTNCancleNewPlaylist.getScene().getWindow();
        // do what you have to do
        stage.close();


    }
}
