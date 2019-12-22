package App.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class SceneController {
    public void changeScene(Stage stage , String fxml) throws IOException {
        Parent pane = FXMLLoader.load(
                getClass().getResource(fxml));
        stage.setHeight(600);
        stage.setWidth(900);

        stage.getScene().setRoot(pane);
    }
}
