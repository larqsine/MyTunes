package gui;

import be.Playlist;
import be.Song;
import bll.FileManager;
import bll.PlaylistManager;
import bll.SongManager;
import dal.Database;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.media.MediaPlayer;

import java.util.List;

public class AppController {
    @FXML
    private ListView listOfPlaylist;
    @FXML
    private ListView listOfAllSongs;
    @FXML
    private ListView listPlaylistsSongs;
    @FXML
    private Label songLabel;
    private PlaylistManager playlistManager;
    private SongManager songManager;
    private FileManager fileManager;
    private MediaPlayer mediaPlayer;

    public AppController() {
        this.playlistManager = new PlaylistManager(new Database());
        this.songManager = new SongManager(new Database());
        this.fileManager = new FileManager();
    }

    @FXML
    public void initialize() {
        // Initialize your GUI components here
        loadPlaylistData();
        loadSongData();
        openPlaylist();
        setSong();
    }

    private void setSong() {
        listPlaylistsSongs.setOnMouseClicked(event -> {
            if(event.getClickCount() == 1) {
                String selectedSongName = listPlaylistsSongs.getSelectionModel().getSelectedItems().toString();
                songLabel.setText(selectedSongName);
            }
        });
    }

    @FXML
    private void playSong() {
        mediaPlayer.play();
    }

    private void loadSongData() {
        List<Song> songs = songManager.getAllSongs();
        listOfAllSongs.getItems().addAll(songs);
    }

    private void loadPlaylistData() {
        List<Playlist> playlists = playlistManager.getAllPlaylists();
        listOfPlaylist.getItems().addAll(playlists);
    }

    private void openPlaylist() {
        listOfPlaylist.setOnMouseClicked(event -> {
            if(event.getClickCount() == 1) {
                int selectedIndex = listOfPlaylist.getSelectionModel().getSelectedIndex();
                listPlaylistsSongs.getItems().clear();
                List<String> fileNames = fileManager.getFileNamesForIndex(selectedIndex);
                listPlaylistsSongs.getItems().addAll(fileNames);
            }
        });
    }
}
