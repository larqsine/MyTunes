package dk.easv.BLL;

import dk.easv.BE.Playlist;
import dk.easv.BE.SongClass;
import dk.easv.DAL.PlaylistDAO;
import dk.easv.DAL.SongDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.Optional;

public class PlaylistBL {
    private ObservableList<Playlist> playlists;



    PlaylistDAO playlistDAO=new PlaylistDAO();

    public int saveNumber= 0;
    public void checkField(TextField input) {
        if (input != null) {
            if (input.getText().isEmpty()) {
                Alert AlertDialog = new Alert(Alert.AlertType.ERROR);
                AlertDialog.setTitle("Alert");
                AlertDialog.setHeaderText("You cant leave the fields empty");
                Optional<ButtonType> result = AlertDialog.showAndWait();
                saveNumber =0;

            }
            if(input.getText().length()>0){
                input.getText().trim();
                saveNumber =1;





            }
        } else {
            System.out.println(" Name input is null. Make sure it is properly initialized.");
        }
    }
    public List<Playlist> getAllPlaylist(){
        return playlistDAO.getAllPlaylist();
    }
    public ObservableList<Playlist> getPlaylists(){
        playlists= FXCollections.observableArrayList();
        playlists.addAll(getAllPlaylist());
        return playlists;
    }
}
