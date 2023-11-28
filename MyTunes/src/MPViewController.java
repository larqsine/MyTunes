
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MPViewController {

    @FXML
    public void openEditWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Edit Playlist");
            stage.setScene(new Scene(root));

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openNewWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("NewView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("New Playlist");
            stage.setScene(new Scene(root));

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*private void openDeleteWindow() {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DeleteView.fxml"));
                Parent root = loader.load();

                Stage stage = new Stage();
                stage.setTitle("New Playlist");
                stage.setScene(new Scene(root));

                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }

    @FXML
    private void openDeleteWindow(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DeleteView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Delete Playlist");
            stage.setScene(new Scene(root));

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}