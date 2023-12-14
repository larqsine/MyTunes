package gui;

import be.Playlist;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


public class PlaylistInterfaceController {

    private ListView<Playlist> listOfPlaylists;

    @FXML
    private TextField nameOfPlaylist, amountOfPlaylistSongs, timeOfPlaylist;

    public void setListOfPlaylists(ListView<Playlist> listOfPlaylists) {
        this.listOfPlaylists = listOfPlaylists;
    }

    @FXML
    private void savePlaylist() {
        String namePlaylist = nameOfPlaylist.getText();
        int amountSongs = amountOfPlaylistSongs.getAnchor();
        String timeSong = timeOfPlaylist.getText();

        Playlist newPlaylist = new Playlist(namePlaylist, amountSongs, timeSong);

        listOfPlaylists.getItems().add(newPlaylist);

    }

    @FXML
    private void editPlaylist() {
    }
}
