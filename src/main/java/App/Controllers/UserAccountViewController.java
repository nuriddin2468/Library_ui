package App.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import App.Models.UsersModel;
import App.Helpers.ViewSession;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import App.Helpers.UserSession;
import App.Controllers.SceneController;
import javafx.stage.Stage;

public class UserAccountViewController extends SceneController implements Initializable {


    @FXML
    private Button updateUser;

    @FXML
    private Button deleteUser;

    @FXML
    private Text firstName;

    @FXML
    private Text SecondName;

    @FXML
    private Text username;

    @FXML
    private Text role;

    @FXML
    void deleteUser(ActionEvent event) throws IOException {
        UsersModel student = new UsersModel();
        student.delete(ViewSession.getId());
        Stage stage = (Stage) role.getScene().getWindow();
        this.changeScene(stage, "/templates/AdminDashboardUser.fxml");
    }

    @FXML
    void updateUser(ActionEvent event) throws IOException {
        Stage stage = (Stage) role.getScene().getWindow();
        this.changeScene(stage, "/templates/user_update.fxml");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateUser.setVisible(false);
        deleteUser.setVisible(false);
        if(UserSession.getRole() == 3){
            updateUser.setVisible(true);
            deleteUser.setVisible(true);
        }
        int student_id = ViewSession.getId();
        UsersModel student = new UsersModel();
        student.getOne("id", String.format("%d",student_id) );
        firstName.setText(student.getName().split(" ")[0]);
        SecondName.setText(student.getName().split(" ")[1]);
        username.setText(student.getUsername());
        if(student.getRole() == 1){
            role.setText("Student");
        }else if(student.getRole() == 2){
            role.setText("Librarian");
        }else if(student.getRole() == 3){
            role.setText("Administration");
        }
    }
}
