package App.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class TitleController implements Initializable {

    @FXML
    public Button closeBtn;

    @FXML
    void hide(ActionEvent event) {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.setIconified(true);

    }

    @FXML
    void fullscreen(ActionEvent event) {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        if (stage.isFullScreen()) {
            stage.setFullScreen(false);
        }else{
            stage.setFullScreen(true);
        }
    }

    @FXML
    void exit(ActionEvent event) {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
