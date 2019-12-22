package App.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import App.Controllers.SceneController;
import javafx.stage.Stage;
import App.Helpers.UserSession;

public class SideBarController extends SceneController implements Initializable {

    @FXML
    private Button usersBtn;

    @FXML
    private Button booksBtn;

    @FXML
    void books(ActionEvent event) throws IOException {

        Stage stage = (Stage) usersBtn.getScene().getWindow();
        this.changeScene(stage, "/templates/AdminDashboardBook.fxml");
    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        UserSession.cleanUserSession();
        Stage stage = (Stage) usersBtn.getScene().getWindow();
        this.changeScene(stage, "/templates/SignIn.fxml");
    }

    @FXML
    void users(ActionEvent event) throws IOException {
        Stage stage = (Stage) usersBtn.getScene().getWindow();
        this.changeScene(stage, "/templates/AdminDashboardUser.fxml");
    }

        @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        }

}
