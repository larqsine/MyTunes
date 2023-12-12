package dk.easv.gui.songs;

import dk.easv.BLL.SongBL;
import dk.easv.DAL.SongDAO;
import dk.easv.gui.FxmlViewController;
import dk.easv.BE.SongClass;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;


import java.io.File;
import java.io.IOException;

public class EditSongController {

    public Button BTNSaveSong;
    public TextField CategoryInput;
    public TextField TitleInput;
    public TextField ArtistInput;
    public TextField TimeInput;
    public Button BTNCancleEditSong;
    public Button BTNChoose;
    public TextField FileInput;

     private SongBL songBL;
    private FxmlViewController f;


    public void ClickCancle(ActionEvent actionEvent) {
        // get a handle to the stage
        Stage stage = (Stage) BTNCancleEditSong.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    public void setSongBL( SongBL songBL){

        this.songBL= songBL;
    }
    public void setFxmlViewController( FxmlViewController fxmlViewController){

        this.f=fxmlViewController;
    }

    public void ClickSave(ActionEvent actionEvent) throws IOException {

        songBL.checkField(TitleInput, "Title");
        songBL.checkField(ArtistInput, "Artist");
        songBL.checkField(CategoryInput, "Category");

        if(songBL.saveNumber==1){


        SongClass selectedSong = songBL.getSongToBeEdited();
        if (selectedSong != null) {
             String oldTitle = selectedSong.getTitle();
             String oldArtist = selectedSong.getArtist();
            SongClass songClass = new SongClass();
            songClass.setTitle(TitleInput.getText());
            songClass.setArtist(ArtistInput.getText());
            songClass.setCategory(CategoryInput.getText());
            songClass.setTime(Double.parseDouble(TimeInput.getText()));
            songClass.setFile(FileInput.getText());
            songBL.updateSong(songClass, oldTitle, oldArtist);
            Stage currentStage = (Stage) BTNCancleEditSong.getScene().getWindow();
            currentStage.close();
            f.tableView.refresh();
        }
        }
    }

    public void ClickChoose(ActionEvent actionEvent) {
        FileChooser fileChooser=new FileChooser();
        Window SongClass = null;
        fileChooser.setInitialFileName(String.valueOf(new File("C:/Music")));
        File selectedfile= fileChooser.showOpenDialog(SongClass);
        if(selectedfile != null) {
            FileInput.setText(String.valueOf(selectedfile));
        }
        else{
            System.out.println("file is not valid");
        }
    }

}
