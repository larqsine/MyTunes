package dk.easv.gui;

import dk.easv.BE.SongClass;
import dk.easv.BLL.SongBL;
import dk.easv.DAL.SongDAO;
import dk.easv.gui.playlist.DeletePlaylistController;
import dk.easv.gui.playlist.EditPlaylistController;
import dk.easv.gui.playlist.NewPlaylistController;
import dk.easv.gui.songs.DeleteSongController;
import dk.easv.gui.songs.EditSongController;
import dk.easv.gui.songs.NewSongController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

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
    public Button BTNNewPlaylist;
    public ListView PlaylistListView;
    public Button BTNEditPlaylist;
    public Button BTNDeletePlaylist;

    //FXML
    @FXML
    private Label mainLabel;
    @FXML
    private Slider volumeSlider;

    //OTHERS
    private File directory;
    private File[] files;
    public ArrayList<File> songs;


    private Media media;
    private MediaPlayer mediaPlayer;
    private int songNumber;

    private SongBL s;

    public void setSongBL(SongBL songBL) {

        this.s = songBL;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //INITIALIZE
        setSongBL(new SongBL());
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
        songList = FXCollections.observableArrayList();
        loadSongData();

        //Serach Bar
        search();



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
            for (SongClass selectedSong : singleSong) {
                SongDAO s = new SongDAO();
                s.deleteSong(selectedSong.getTitle(), selectedSong.getArtist());
            }
            List<SongClass> allSongsList = new ArrayList<>(allSongs);
            allSongsList.removeAll(singleSong);

            // Update the TableView
            tableView.setItems(FXCollections.observableArrayList(allSongsList));
            tableView.refresh();
        }


    }


     public void loadSongData(){

         TableViewTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
         TableViewArtist.setCellValueFactory(new PropertyValueFactory<>("Artist"));
         TableViewCategory.setCellValueFactory(new PropertyValueFactory<>("Category"));
         TableViewTime.setCellValueFactory(new PropertyValueFactory<>("Time"));
         tableView.getColumns().addAll();
         tableView.setItems(s.getSonglist());
         tableView.refresh();

     }


     public void search(){
         loadSongData();
         FilteredList<SongClass> filteredSongs = new FilteredList<>(s.getSonglist(), b -> true);

         SearchBar.textProperty().addListener((observable, oldValue, newValue) -> {
             filteredSongs.setPredicate(songClass -> {
                 if (newValue == null || newValue.isEmpty()) {
                     return true;
                 }

                 String lowerCaseFilter = newValue.toLowerCase();

                 String title = songClass.getTitle();
                 String artist = songClass.getArtist();
                 String category = songClass.getCategory();

                 boolean titleMatches = title != null && title.toLowerCase().contains(lowerCaseFilter);
                 boolean artistMatches = artist != null && artist.toLowerCase().contains(lowerCaseFilter);
                 boolean categoryMatches = category != null && category.toLowerCase().contains(lowerCaseFilter);

                 return titleMatches || artistMatches || categoryMatches;
             });
         });

         SortedList<SongClass> sortedSongs = new SortedList<>(filteredSongs);
         sortedSongs.comparatorProperty().bind(tableView.comparatorProperty());
         tableView.setItems(sortedSongs);
     }

    public void ClickNewPlaylistBTN(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dk/easv/gui/playlist/NewPlaylist.fxml"));
        Parent root4 = (Parent) fxmlLoader.load();
        NewPlaylistController newPlaylistController= fxmlLoader.getController();
        newPlaylistController.setFxmlViewController(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(root4));
        stage.show();
    }

    public void ClickEditPlaylistBTN(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dk/easv/gui/playlist/EditPlaylist.fxml"));
        Parent root5 = (Parent) fxmlLoader.load();
        EditPlaylistController editPlaylistController= fxmlLoader.getController();
        editPlaylistController.setFxmlViewController(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(root5));
        stage.show();
    }

    public void ClickDeletePlaylistBTN(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dk/easv/gui/playlist/DeletePlaylist.fxml"));
        Parent root6 = (Parent) fxmlLoader.load();
        DeletePlaylistController deletePlaylistController= fxmlLoader.getController();
        deletePlaylistController.setFxmlViewController(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(root6));
        stage.show();
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Confirmation");
        confirmationDialog.setHeaderText("Are you sure you want to delete?");
        confirmationDialog.setContentText("Any unsaved changes will be lost.");
        Optional<ButtonType> result = confirmationDialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Get the index of the selected playlist
            int selectedIndex = PlaylistListView.getSelectionModel().getSelectedIndex();

            // Remove the selected playlist from the list
            PlaylistListView.getItems().remove(selectedIndex);

            stage.close();
        }
    }
}
