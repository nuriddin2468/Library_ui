package App.Controllers;

import App.Helpers.UserSession;
import App.Helpers.ViewSession;
import App.Models.BooksModel;
import App.Models.UsersModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BookViewController extends SceneController implements Initializable {

    @FXML
    private Label title;

    @FXML
    private Text description;

    @FXML
    private Text author;

    @FXML
    private Text category;

    @FXML
    private Text isbn;

    @FXML
    private Text count;

    @FXML
    private Button updateBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    void delete(ActionEvent event) throws IOException {
        BooksModel book = new BooksModel();
        book.delete(ViewSession.getId());
        Stage stage = (Stage) updateBtn.getScene().getWindow();
        this.changeScene(stage, "/templates/AdminDashboardBook.fxml");

    }

    @FXML
    void update(ActionEvent event) throws IOException {
        Stage stage = (Stage) updateBtn.getScene().getWindow();
        this.changeScene(stage, "/templates/book_update.fxml");
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateBtn.setVisible(false);
        deleteBtn.setVisible(false);
        if(UserSession.getRole() == 2){
            updateBtn.setVisible(true);
            deleteBtn.setVisible(true);
        }
        BooksModel book = new BooksModel();
        book.getOne("id", String.format("%d",ViewSession.getId()));
        title.setText(book.getTitle());
        description.setText(book.getDescription());
        author.setText(book.getAuthor());
        category.setText(book.getCategory());
        isbn.setText(book.getIsbn());
        count.setText(String.format("%d", book.getCount()));

    }
}
