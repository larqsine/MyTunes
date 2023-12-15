package gui;

import be.Playlist;
import be.Song;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


public class PlaylistInterfaceController {

    private ListView<Playlist> listOfPlaylists;

    @FXML
    private TextField nameOfPlaylist, amountOfPlaylistSongs, timeOfPlaylist;
    public String name, playlistTime;
    public int songs;
    public Playlist selectedPlaylist;

    @FXML
    public void initialize() {
        setTheDataToTheTextFields();
    }

    public void setListOfPlaylists(ListView<Playlist> listOfPlaylists) {
        this.listOfPlaylists = listOfPlaylists;
    }

    public void setSelectedSong(Playlist selectedPlaylist) {
        this.selectedPlaylist = selectedPlaylist;
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
    public void editPlaylist() {
        listOfPlaylists.getItems().add(selectedPlaylist);
        saveNewAttributesOfThePlaylist();
    }

    public void setTheDataToTheTextFields() {
        nameOfPlaylist.setText(name);
        amountOfPlaylistSongs.setText(String.valueOf(songs));
        timeOfPlaylist.setText(playlistTime);
    }

    public void saveNewAttributesOfThePlaylist() {
        System.out.println("here: " + selectedPlaylist);

        selectedPlaylist.setName(nameOfPlaylist.getText());
        selectedPlaylist.setTime(timeOfPlaylist.getText());
        selectedPlaylist.setSongs(Integer.parseInt(amountOfPlaylistSongs.getText()));
    }
}
