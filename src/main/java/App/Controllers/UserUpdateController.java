package App.Controllers;

import App.Models.UsersModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import App.Controllers.SceneController;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import App.Helpers.ViewSession;

public class UserUpdateController extends SceneController implements Initializable {

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
    void updateUser(ActionEvent event) throws IOException {
        int student_id = ViewSession.getId();
        UsersModel object = new UsersModel();
        object.getOne("id", String.format("%d",student_id) );
        object.setUsername(username.getText());
        object.setName(firstName.getText()+" "+secondName.getText());
        object.setPassword(password.getText());
        if(toggle.getSelectedToggle() == isStudent) {
            object.setRole(1);
        }else if(toggle.getSelectedToggle() == isAdmin) {
            object.setRole(3);
        }else if(toggle.getSelectedToggle() == isLibrarian) {
            object.setRole(2);
        }
        object.update();

        Stage stage = (Stage)firstName.getScene().getWindow();
        this.changeScene(stage, "/templates/UserAccountView.fxml");
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        Stage stage = (Stage)firstName.getScene().getWindow();
        this.changeScene(stage, "/templates/AdminDashboardUser.fxml");
    }

    ToggleGroup toggle = new ToggleGroup();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //isStudent.setSelected(true);
        isAdmin.setToggleGroup(toggle);
        isLibrarian.setToggleGroup(toggle);
        isStudent.setToggleGroup(toggle);

        int student_id = ViewSession.getId();
        UsersModel object = new UsersModel();
        object.getOne("id", String.format("%d",student_id) );
        firstName.setText(object.getName().split(" ")[0]);
        secondName.setText(object.getName().split(" ")[1]);
        username.setText(object.getUsername());
        password.setText(object.getPassword());
        int role = object.getRole();
        if(role == 1){
            isStudent.setSelected(true);
        }else if(role == 2){
            isLibrarian.setSelected(true);
        }else if(role == 3){
            isAdmin.setSelected(true);
        }
    }
}
