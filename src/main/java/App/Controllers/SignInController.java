package App.Controllers;

import App.Models.BooksModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
    private Text errorMsg;

    @FXML
    void login(ActionEvent event) throws IOException{
        Stage stage = (Stage) btn_login.getScene().getWindow();
        UsersModel obj = new UsersModel();
        obj.getOne("username", username.getText());

        if(obj.getUsername() == null){
            errorMsg.setText("Username not found!");
        }else{
            if(obj.getPassword().equals(password.getText())){
                UserSession.setUsername(obj.getUsername());
                UserSession.setName(obj.getName());
                UserSession.setRole(obj.getRole());
                errorMsg.setText("");
                this.changeScene(stage , "/templates/AdminDashboardUser.fxml");
            }else{
                errorMsg.setText("Password is incorrect!");
            }
        }

    }


    @FXML
    void register(MouseEvent event) throws IOException {
        Stage stage = (Stage) btn_login.getScene().getWindow();
        this.changeScene(stage , "/templates/SignUp.fxml");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
