package gui;

import be.Playlist;
import be.Song;
import bll.FileManager;
import bll.PlaylistManager;
import bll.SongManager;
import dal.Database;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.util.List;

public class AppController {
    @FXML
    private ListView listOfPlaylist;
    @FXML
    private ListView listOfAllSongs;
    @FXML
    private ListView<String> listPlaylistsSongs;
    @FXML
    private Label songLabel;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Slider volumeSlider;
    private PlaylistManager playlistManager;
    private SongManager songManager;
    private FileManager fileManager;
    private MediaPlayer mediaPlayer;
    private Media media;

    public AppController() {
        this.playlistManager = new PlaylistManager(new Database());
        this.songManager = new SongManager(new Database());
        this.fileManager = new FileManager();
    }

    @FXML
    public void initialize() {
        loadPlaylistData();
        loadSongData();
        openPlaylist();
        setSong();
        getSongOnPlaylist();
    }

    private void setSong() {
        listPlaylistsSongs.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                String selectedSongName = listPlaylistsSongs.getSelectionModel().getSelectedItems().toString();
                songLabel.setText(selectedSongName);

                String filePath = fileManager.getFilePathForSong(selectedSongName);
                media = new Media(new File(filePath).toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                setProgressBar();
                setVolumeSlider();
                seekDuration();
            }
        });
    }

    @FXML
    private void stopSong() {
        mediaPlayer.pause();
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

    private void getSongOnPlaylist() {
        listOfAllSongs.setOnMouseClicked(event -> {
            if(event.getClickCount() == 1) {
                int selectedIndex = listOfAllSongs.getSelectionModel().getSelectedIndex();
                String songName = fileManager.getSongNameForIndex(selectedIndex);
                listPlaylistsSongs.getItems().add(songName);
            }
        });
    }

    private void setProgressBar() {
        if(mediaPlayer != null){
            mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                    double progress = newValue.toMillis() / media.getDuration().toMillis();
                    progressBar.setProgress(progress);
                }
            });
        }
    }

    private void setVolumeSlider() {
        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                mediaPlayer.setVolume(volumeSlider.getValue() * 0.02);
            }
        });
    }

    private void seekDuration() {
        progressBar.setOnMouseClicked(event -> {
            double mouseX = event.getX();
            double barWidth = progressBar.getWidth();
            double newTime = mouseX / barWidth * media.getDuration().toMillis();
            mediaPlayer.seek(Duration.millis(newTime));
        });
    }
}
