package App.Controllers;

import App.Helpers.ViewSession;
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

public class BookUpdateController extends SceneController implements Initializable {


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
        Stage stage = (Stage) category.getScene().getWindow();
        this.changeScene(stage, "/templates/BookView.fxml");

    }


    @FXML
    void submit(ActionEvent event) throws IOException {
        BooksModel book = new BooksModel();
        book.setId(ViewSession.getId());
        book.setTitle(title.getText());
        book.setAuthor(author.getText());
        book.setCategory(category.getText());
        book.setDescription(description.getText());
        book.setIsbn(isbn.getText());
        book.setCount(Integer.parseInt(count.getText()));
        book.update();
        Stage stage = (Stage) category.getScene().getWindow();
        this.changeScene(stage, "/templates/BookView.fxml");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BooksModel book = new BooksModel();
        book.getOne("id", String.format("%d", ViewSession.getId()));
        title.setText(book.getTitle());
        description.setText(book.getDescription());
        author.setText(book.getAuthor());
        category.setText(book.getCategory());
        isbn.setText(book.getIsbn());
        count.setText(String.format("%d", book.getCount()));


    }
}
