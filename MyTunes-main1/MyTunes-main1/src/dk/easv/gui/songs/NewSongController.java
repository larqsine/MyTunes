package dk.easv.gui.songs;

import dk.easv.BLL.SongBL;
import dk.easv.gui.FxmlViewController;
import dk.easv.BE.SongClass;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;


import java.io.File;
import java.io.IOException;

import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.title;

public class NewSongController {
    public Button BTNCancleNewSong;
    public Button BTNSaveSong;
    public TextField CategoryInput;
    public TextField TitleInput;
    public TextField ArtistInput;
    public TextField TimeInput;
    public Button BTNChoose;
    public TextField FileInput;

    private FxmlViewController f;


    public void ClickCancle(ActionEvent actionEvent) {
        // get a handle to the stage
        Stage stage = (Stage) BTNCancleNewSong.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    public void setFxmlViewController(FxmlViewController fxmlViewController) {

        this.f = fxmlViewController;
    }

    public void ClickSave(ActionEvent actionEvent) throws IOException {
        SongBL songBL = new SongBL();
        songBL.checkField(TitleInput, "Title");
        songBL.checkField(ArtistInput, "Artist");
        songBL.checkField(CategoryInput, "Category");



        SongClass songClass = new SongClass();
        songClass.setTitle(TitleInput.getText());
        songClass.setArtist(ArtistInput.getText());
        songClass.setCategory(CategoryInput.getText());
        songClass.setTime(Double.parseDouble(TimeInput.getText()));
        f.addSong(songClass);
        Stage currentStage = (Stage) BTNCancleNewSong.getScene().getWindow();
        currentStage.close();


    }


    public void ClickChoose(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        Window SongClass = null;
        fileChooser.setInitialFileName(String.valueOf(new File("C:/Music")));
        File selectedfile = fileChooser.showOpenDialog(SongClass);
        if(selectedfile != null) {
            FileInput.setText(String.valueOf(selectedfile));
        }
            else{
                System.out.println("file is not valid");
            }
        }
    }


