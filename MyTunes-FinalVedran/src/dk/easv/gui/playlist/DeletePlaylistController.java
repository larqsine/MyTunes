package dk.easv.gui.playlist;

import dk.easv.gui.FxmlViewController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DeletePlaylistController {
    public Button CancleBtn;
    private FxmlViewController f;

    public void setFxmlViewController(FxmlViewController fxmlViewController) {

        this.f = fxmlViewController;
    }

    public void ClickCancleBtn(ActionEvent actionEvent) {
        // get a handle to the stage
        Stage stage = (Stage) CancleBtn.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
