package dk.easv.gui.playlist;

import dk.easv.BE.Playlist;
import dk.easv.BE.SongClass;
import dk.easv.BLL.PlaylistBL;
import dk.easv.gui.FxmlViewController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditPlaylistController {
    public TextField EditPlaylistInput;
    public Button BTNCancleEditPlaylist;
    public Button SaveEditPlaylist;
    private FxmlViewController f;

    public void setFxmlViewController(FxmlViewController fxmlViewController) {

        this.f = fxmlViewController;
    }

    public void ClickSavePlaylistBTN(ActionEvent actionEvent) {
        PlaylistBL playlistBL=new PlaylistBL();
        playlistBL.checkField(EditPlaylistInput);

        ObservableList<String> allPlaylists;
        allPlaylists = f.PlaylistListView.getItems();

        // Get the index of the selected playlist
        int selectedIndex = f.PlaylistListView.getSelectionModel().getSelectedIndex();

        // Update the playlist name with the edited text
        String editedPlaylistName = EditPlaylistInput.getText();

        // Replace the selected playlist name in the list
        allPlaylists.set(selectedIndex, editedPlaylistName);

        // Close the stage
        Stage stage = (Stage) BTNCancleEditPlaylist.getScene().getWindow();
        stage.close();


    }

    public void ClickCancleBTN(ActionEvent actionEvent) {
        // get a handle to the stage
        Stage stage = (Stage) BTNCancleEditPlaylist.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
