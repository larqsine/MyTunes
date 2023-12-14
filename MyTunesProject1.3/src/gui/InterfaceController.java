package gui;

import be.Playlist;
import be.Song;
import bll.DatabaseManager;
import bll.FileManager;
import dal.DatabaseData;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.List;

public class InterfaceController {
    @FXML
    private ListView<Playlist> listOfPlaylists;
    @FXML
    private ListView listOfSongsOnPlaylist;
    @FXML
    private ListView<Song> listOfSongs;
    @FXML
    private Label songLabel;
    @FXML
    private Slider volumeSlider;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private TextField filterTextField;

    private DatabaseManager databaseManager;
    private FileManager fileManager;
    private MediaPlayer mediaPlayer;
    private Media media;
    private String nameOfSong;
    private Song selectedSong;
    public String title, artist, category, time;
    public String newTitle, newArtist, newCategory, newTime;

    public InterfaceController() {
        this.databaseManager = new DatabaseManager(new DatabaseData());
        this.fileManager = new FileManager();
    }

    @FXML
    public void initialize() {
        loadAllDataFromDatabase();
        loadAllSongsFromPlaylists();
        setTheSong();
        selectTheSong();
        getTheSong();
        getThePlaylist();
    }

    private void loadAllSongsFromPlaylists() {
        listOfPlaylists.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                int selectedIndex = listOfPlaylists.getSelectionModel().getSelectedIndex();
                listOfSongsOnPlaylist.getItems().clear();
                List<String> fileNames = fileManager.getThePathOfPlaylist(selectedIndex);
                listOfSongsOnPlaylist.getItems().addAll(fileNames);
            }
        });
    }

    private void loadAllDataFromDatabase() {
        List<Playlist> playlists = databaseManager.getAllPlaylists();
        listOfPlaylists.getItems().addAll(playlists);
        List<Song> songs = databaseManager.getAllSongs();
        listOfSongs.getItems().addAll(songs);
    }

    private void setTheSong() {
        listOfSongsOnPlaylist.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                nameOfSong = listOfSongsOnPlaylist.getSelectionModel().getSelectedItem().toString();
                songLabel.setText(nameOfSong);

                String songPath = fileManager.getSong(nameOfSong);
                media = new Media(new File(songPath).toURI().toString());
                mediaPlayer = new MediaPlayer(media);

                setVolumeSlider();
                setProgressBar();
                seekDuration();
            }
        });
    }

    private void setVolumeSlider() {
        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                mediaPlayer.setVolume(volumeSlider.getValue() * 0.02);
            }
        });
    }

    private void setProgressBar() {
        if (mediaPlayer != null) {
            mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                    double progress = newValue.toMillis() / media.getDuration().toMillis();
                    progressBar.setProgress(progress);
                }
            });
        }
    }

    private void seekDuration() {
        progressBar.setOnMouseClicked(event -> {
            double mouseX = event.getX();
            double barWidth = progressBar.getWidth();
            double newValue = mouseX / barWidth * media.getDuration().toMillis();
            mediaPlayer.seek(Duration.millis(newValue));
        });
    }

    @FXML
    private void playSong() {
        mediaPlayer.play();
    }

    @FXML
    private void pauseSong() {
        mediaPlayer.pause();
    }

    //Search songs
    @FXML
    private void searchSong() {
        String letter = filterTextField.getText().toUpperCase();
        List<Song> allSongs = databaseManager.getAllSongs();
        listOfSongs.getItems().clear();
        for (Song song : allSongs) {
            if (song.getTitle().toUpperCase().contains(letter)) {
                listOfSongs.getItems().addAll(song);
            }
        }
    }

    @FXML
    private void searchAllSongs() {
        listOfSongs.getItems().clear();
        filterTextField.clear();
        List<Song> allSongs = databaseManager.getAllSongs();
        listOfSongs.getItems().addAll(allSongs);
    }

    //Close the application
    @FXML
    private void closeApplication() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        Platform.exit();
    }

    //Opening the windows for New song and Edit song
    @FXML
    private void windowNewSong() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SongInterface.fxml"));
            AnchorPane root = loader.load();
            Scene newSongScene = new Scene(root);
            Stage newSongStage = new Stage();
            newSongStage.setScene(newSongScene);
            newSongStage.setTitle("New/Edit Song");
            newSongStage.show();

            SongInterfaceController songController = loader.getController();
            songController.setListOfSongs(listOfSongs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void windowEditSong() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SongInterface.fxml"));
            AnchorPane root = loader.load();
            Scene newSongScene = new Scene(root);
            Stage newSongStage = new Stage();
            newSongStage.setScene(newSongScene);
            newSongStage.setTitle("New/Edit Song");
            newSongStage.show();

            SongInterfaceController songController = loader.getController();
            songController.setListOfSongs(listOfSongs);
            Song selected = listOfSongs.getSelectionModel().getSelectedItem();
            songController.setSelectedSong(selected);
            songController.title = title;
            songController.artist = artist;
            songController.category = category;
            songController.time = time;
            songController.setTheStringsToTextFields();

            songController.initialize();
            songController.newTitle = newTitle;
            songController.newArtist = newArtist;
            songController.newCategory = newCategory;
            songController.newTime = newTime;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void newPlaylistWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PlaylistInterface.fxml"));
            AnchorPane root = loader.load();
            Stage newPlaylistStage = new Stage();
            Scene newPlaylistScene = new Scene(root);
            newPlaylistStage.setScene(newPlaylistScene);
            newPlaylistStage.setTitle("New/Edit Playlist");
            newPlaylistStage.show();

            PlaylistInterfaceController playlistController = loader.getController();
            playlistController.setListOfPlaylists(listOfPlaylists);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void editPlaylistWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PlaylistInterface.fxml"));
            AnchorPane root = loader.load();
            Stage newPlaylistStage = new Stage();
            Scene newPlaylistScene = new Scene(root);
            newPlaylistStage.setScene(newPlaylistScene);
            newPlaylistStage.setTitle("New/Edit Playlist");
            newPlaylistStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Delete the song methods
    @FXML
    private void deleteSong() {
        listOfSongs.getItems().remove(listOfSongs.getSelectionModel().getSelectedItem());
    }

    private void selectTheSong() {
        listOfSongs.setOnMouseClicked(event -> {
            if(event.getClickCount() == 1) {
                selectedSong = listOfSongs.getSelectionModel().getSelectedItem();
            }
        });
    }

    //Delete the playlist: methods
    @FXML
    private void deleteThePlaylist() {
        listOfPlaylists.getItems().remove(listOfPlaylists.getSelectionModel().getSelectedItem());
    }

    private void getThePlaylist() {
        listOfPlaylists.setOnMouseClicked(event -> {
            if(event.getClickCount() == 1) {
                Playlist deletedPlaylist = listOfPlaylists.getSelectionModel().getSelectedItem();
            }
        });
    }


    //Edit song methods
    private void getAttributesOfTheSong(Song song) {
        title = song.getTitle();
        artist = song.getArtist();
        category = song.getCategory();
        time = song.getTime();
    }

    private void getTheSong() {
        listOfSongs.setOnMouseClicked(event -> {
            if(event.getClickCount() == 1) {
                Song chosenSongForEdit = listOfSongs.getSelectionModel().getSelectedItem();
                getAttributesOfTheSong(chosenSongForEdit);
            }
        });
    }

    private void getAttributesOfThePlaylist(Playlist playlist) {
        String name = playlist.getName();
        int songs = playlist.getSongs();
        String time = playlist.getTime();
    }

    private void getTheSelectedPlaylist() {
        listOfPlaylists.setOnMouseClicked(event -> {
            if(event.getClickCount() == 1) {
                Playlist selectedPlaylist = listOfPlaylists.getSelectionModel().getSelectedItem();
                getAttributesOfThePlaylist(selectedPlaylist);
            }
        });
    }
}