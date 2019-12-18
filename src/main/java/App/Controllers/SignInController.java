package App.Controllers;

import App.Models.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.ResourceBundle;
import App.Models.UsersModel;
import App.Helpers.UserSession;

public class SignInController extends SceneController implements Initializable {
    @FXML
    private PasswordField password;

    @FXML
    private Button btn_login;

    @FXML
    private TextField username;

    @FXML
    void login(ActionEvent event) throws IOException{
        Stage stage = (Stage) btn_login.getScene().getWindow();
        UsersModel obj = new UsersModel();
        obj.getOne("username", username.getText());

        if(obj.getUsername() == null){
            System.out.println("User not found!");
        }else{
            if(obj.getPassword().equals(password.getText())){

                System.out.println("OK!");
                UserSession.setUsername(obj.getUsername());
                System.out.println("object name = " + obj.getUsername());
                UserSession.setName(obj.getName());
                UserSession.setRole(obj.getRole());
                this.changeScene(stage , "/templates/dashboard.fxml");
            }else{
                System.out.println("Password is incorrect!");
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
