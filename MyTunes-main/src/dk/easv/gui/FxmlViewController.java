package dk.easv.gui;

import dk.easv.BE.SongClass;
import dk.easv.gui.songs.DeleteSongController;
import dk.easv.gui.songs.EditSongController;
import dk.easv.gui.songs.NewSongController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;

public class FxmlViewController implements Initializable {

    public Button BTNNewSong;
    public Button BTNCloseApp;
    public Button BTNEdit;
    public TableColumn<SongClass,String> TableViewTitle;
    public TableColumn<SongClass,String> TableViewArtist;
    public TableColumn<SongClass,String> TableViewCategory;
    public TableColumn<SongClass,Double> TableViewTime;
    public TableView<SongClass> tableView;
    public Button BTNDeleteSong;
    public TextField SearchBar;

    public   ObservableList<SongClass> songList;
    //FXML
    @FXML
    private Label mainLabel;
    @FXML
    private Slider volumeSlider;

    //OTHERS
    private File directory;
    private File[] files;
    public ArrayList<File> songs;
    private DeleteSongController d;

    private Media media;
    private MediaPlayer mediaPlayer;
    private int songNumber;
    private JFXPanel stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //INITIALIZE
        directory = new File("Music");
        files = directory.listFiles();
        songs = new ArrayList<>(Arrays.asList(files));

        //MEDIA && MEDIA PLAYER STAFF
        media = new Media(songs.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        //OTHERS
        mainLabel.setText(songs.get(songNumber).getName());

        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                mediaPlayer.setVolume(volumeSlider.getValue() * 0.02);
            }
        });
        // Seting info in table view
        TableViewTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        TableViewArtist.setCellValueFactory(new PropertyValueFactory<>("Artist"));
        TableViewCategory.setCellValueFactory(new PropertyValueFactory<>("Category"));
        TableViewTime.setCellValueFactory(new PropertyValueFactory<>("Time"));
        songList =FXCollections.observableArrayList();
        tableView.setItems(songList);

    }

    public void addSong(SongClass song){

        songList.add(song);
    }

    @FXML
    private void previousSong() {

        if(songNumber > 0) {
            mediaPlayer.stop();

            songNumber--;
            mainLabel.setText(songs.get(songNumber).getName());

            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
        }
    }

    @FXML
    private void playSong() {
        mediaPlayer.play();
    }

    @FXML
    private void pauseSong() {
        mediaPlayer.pause();
    }

    @FXML
    private void nextSong() {

        if(songNumber < songs.size() - 1) {

            mediaPlayer.stop();

            songNumber++;
            mainLabel.setText(songs.get(songNumber).getName());

            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
        }
    }

    public void Click(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dk/easv/gui/songs/NewSong.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        NewSongController newSongController= fxmlLoader.getController();
        newSongController.setFxmlViewController(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();

    }

    public void ClickCloseBTN(ActionEvent actionEvent) {
        // get a handle to the stage
        Stage stage = (Stage) BTNCloseApp.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    public void ClickEditBTN(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("songs/EditSong.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        EditSongController editSongController= fxmlLoader.getController();
        editSongController.setFxmlViewController(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(root2));
        stage.show();
    }
    public void setDeliteSong(DeleteSongController deliteSong){
        this.d=deliteSong;
    }

    public void ClickDeleteBTN(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("songs/DeleteSong.fxml"));
        Parent root3 = (Parent) fxmlLoader.load();
        DeleteSongController deleteSongController= fxmlLoader.getController();
        deleteSongController.setFxmlViewController(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(root3));
        stage.show();
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Confirmation");
        confirmationDialog.setHeaderText("Are you sure you want to delete?");
        confirmationDialog.setContentText("Any unsaved changes will be lost.");
        Optional<ButtonType> result = confirmationDialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            stage.close();

            ObservableList<SongClass> allSongs, singleSong;
            allSongs = tableView.getItems();
            singleSong = tableView.getSelectionModel().getSelectedItems();
            singleSong.forEach(allSongs::remove);
        }


    }

    public void Search(ActionEvent actionEvent) {

    }
}
