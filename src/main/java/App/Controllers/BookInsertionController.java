package App.Controllers;

import App.Models.BooksModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BookInsertionController extends SceneController implements Initializable {

    @FXML
    private TextField isbn;

    @FXML
    private TextField title;

    @FXML
    private TextField author;

    @FXML
    private TextField category;

    @FXML
    private TextField count;

    @FXML
    private TextArea description;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        Stage stage = (Stage)title.getScene().getWindow();
        this.changeScene(stage, "/templates/AdminDashboardBook.fxml");
    }

    @FXML
    void submit(ActionEvent event) throws IOException {
        if (title.getText().equals("") || description.getText().equals("") || author.getText().equals("") || isbn.getText().equals("") || category.getText().equals("") || count.getText().equals("")) {
        } else {
            BooksModel book = new BooksModel();

                book.setTitle(title.getText());
                book.setAuthor(author.getText());
                book.setCategory(category.getText());
                book.setDescription(description.getText());
                book.setIsbn(isbn.getText());
                book.setCount(Integer.parseInt(count.getText()));

                book.insert();
                Stage stage = (Stage) title.getScene().getWindow();
                this.changeScene(stage, "/templates/AdminDashboardBook.fxml");
        }

    }







    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
