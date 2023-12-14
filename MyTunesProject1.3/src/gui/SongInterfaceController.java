package gui;

import be.Song;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class SongInterfaceController {
    @FXML
    private TextField songTitle, songArtist, songTime, songPath;
    @FXML
    private ChoiceBox<String> songCategory;
    private ArrayList<String> categories = new ArrayList<>(Arrays.asList("POP", "DRAMA"));
    private ListView<Song> listOfSongs;
    public String title, artist, category, time;
    public Song changedSong;

    public Song selectedSong;
    public String newTitle, newArtist, newCategory, newTime;


    @FXML
    public void initialize() {
        songCategory.getItems().addAll(categories);
        setTheStringsToTextFields();
    }

    public void setListOfSongs(ListView<Song> listOfSongs) {
        this.listOfSongs = listOfSongs;
    }

    public void setSelectedSong (Song selectedSong){
        this.selectedSong = selectedSong;
    }

    @FXML
    private void chooseThePath() {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            songPath.setText(selectedFile.getAbsolutePath());
        }
    }

    @FXML
    private void saveSong() {
        String title = songTitle.getText();
        String artist = songArtist.getText();
        String category = songCategory.getValue();
        String time = songTime.getText();

        Song newSong = new Song(title, artist, category, time);

        listOfSongs.getItems().add(newSong);
    }

    @FXML
    public void saveChange() {
        saveNewAttributesOfTheSong();
        listOfSongs.getItems().add(selectedSong);
    }

    public void setTheStringsToTextFields() {
        songTitle.setText(title);
        songArtist.setText(artist);
        songTime.setText(time);
        songCategory.setValue(category);
    }

    public void saveNewAttributesOfTheSong() {
       System.out.println( "here: " + selectedSong);

        selectedSong.setArtist(songArtist.getText());
        selectedSong.setTitle(songTitle.getText());
        selectedSong.setCategory(songCategory.getValue());
        selectedSong.setTime(songTime.getText());
    }
}
