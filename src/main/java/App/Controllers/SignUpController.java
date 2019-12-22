package App.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import App.Models.UsersModel;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class SignUpController extends SceneController implements Initializable {
    @FXML
    private TextField first_name;

    @FXML
    private TextField second_name;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password_1;

    @FXML
    private PasswordField password_2;

    @FXML
    private Text errorMsg;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        Stage stage = (Stage) username.getScene().getWindow();
        this.changeScene(stage, "/templates/SignIn.fxml");
    }


    @FXML
    void register(ActionEvent event) throws IOException {
        if(first_name.getText().equals("") || second_name.getText().equals("") || username.getText().equals("") || password_1.getText().equals("") || password_2.getText().equals("")){
            errorMsg.setText("Please fill all lines!");
        }else{
            if(password_1.getText().equals(password_2.getText())){
                UsersModel user = new UsersModel();
                user.getOne("username", username.getText());
                if(user.getUsername() == null) {
                    user.setName(first_name.getText() + " " + second_name.getText());
                    user.setPassword(password_1.getText());
                    user.setUsername(username.getText());
                    user.setRole(1);
                    user.insert();
                    Stage stage = (Stage) username.getScene().getWindow();
                    this.changeScene(stage, "/templates/SignIn.fxml");
                }else{
                    errorMsg.setText("Current username exists");
                }
            }else{
                errorMsg.setText("Please repeat your password correctly");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
