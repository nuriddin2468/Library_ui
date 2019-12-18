package App.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;
import App.Helpers.UserSession;

public class DashboardController implements Initializable {
    @FXML
    private Text hi;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hi.setText(hi.getText()+" " + UserSession.getUsername() );
    }
}
