package App.Controllers;

import App.Models.UsersModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import App.Controllers.SceneController;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class UserInsertionController extends SceneController implements Initializable {


    @FXML
    private TextField firstName;

    @FXML
    private TextField secondName;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private RadioButton isLibrarian;

    @FXML
    private RadioButton isStudent;

    @FXML
    private RadioButton isAdmin;

    @FXML
    void addUser(ActionEvent event) throws IOException {
        if (firstName.getText().equals("") || secondName.getText().equals("") || username.getText().equals("") || password.getText().equals("")) {
        } else {
            UsersModel user = new UsersModel();
            user.getOne("username", username.getText());
            if (user.getUsername() == null) {
                user.setName(firstName.getText() + " " + secondName.getText());
                user.setPassword(password.getText());
                user.setUsername(username.getText());
                if(toggle.getSelectedToggle() == isAdmin){
                    user.setRole(3);
                }else if(toggle.getSelectedToggle() == isLibrarian){
                    user.setRole(2);
                }else{
                    user.setRole(1);
                }
                user.insert();
                Stage stage = (Stage) username.getScene().getWindow();
                this.changeScene(stage, "/templates/AdminDashboardUser.fxml");
            }

        }
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        Stage stage = (Stage)firstName.getScene().getWindow();
        this.changeScene(stage, "/templates/AdminDashboardUser.fxml");
    }


    ToggleGroup toggle = new ToggleGroup();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        isStudent.setSelected(true);
        isAdmin.setToggleGroup(toggle);
        isLibrarian.setToggleGroup(toggle);
        isStudent.setToggleGroup(toggle);

    }
}
