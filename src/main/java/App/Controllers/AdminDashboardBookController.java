package App.Controllers;


import App.Helpers.ViewSession;
import App.Models.BooksModel;
import App.Models.UsersModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class AdminDashboardBookController extends SceneController implements Initializable {

    @FXML
    private TextField search_item;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private TableView<BooksModel> table;

    @FXML
    private TableColumn<BooksModel, Integer> col_id;

    @FXML
    private TableColumn<BooksModel, String> col_title;

    @FXML
    private TableColumn<BooksModel, String> col_author;

    @FXML
    private TableColumn<BooksModel, Integer> col_count;


    @FXML
    void flushTable(ActionEvent event) {
        data.removeAll();
        table.getItems().clear();
        getBooks();

    }

    @FXML
    void addBook(ActionEvent event) throws IOException {

        Stage stage = (Stage) search_item.getScene().getWindow();
        this.changeScene(stage, "/templates/book_insertion.fxml");

    }

    @FXML
    void itemClick(MouseEvent event) throws IOException {
        if (event.getClickCount() == 2) //Checking double click
        {
            if(table.getSelectionModel().getSelectedItem() != null) {
                ViewSession.setId(table.getSelectionModel().getSelectedItem().getId());
                Stage stage = (Stage) search_item.getScene().getWindow();
                this.changeScene(stage, "/templates/BookView.fxml");
            }
        }
    }

    @FXML
    void search(ActionEvent event) {

        data.removeAll();
        table.getItems().clear();
        try {
            BooksModel getBooks = new BooksModel();
            String search_by = comboBox.getValue();
            if(search_by==null){
                search_by = "title";
            }
            ResultSet rs = getBooks.search(search_by, search_item.getText());
            while (rs.next()) {
                data.add(new BooksModel(rs.getInt("id"), rs.getString("title"), rs.getString("description"), rs.getString("author"), rs.getString("category"), rs.getString("isbn"), rs.getInt("count")));
            }
            col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
            col_author.setCellValueFactory(new PropertyValueFactory<>("author"));
            col_count.setCellValueFactory(new PropertyValueFactory<>("count"));
            table.setItems(data);

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }



    void getBooks() {
        try {
            BooksModel getBooks = new BooksModel();
            ResultSet rs = getBooks.getAll();
            while (rs.next()) {
                data.add(new BooksModel(rs.getInt("id"), rs.getString("title"), rs.getString("description"), rs.getString("author"), rs.getString("category"), rs.getString("isbn"), rs.getInt("count")));
            }
            col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
            col_author.setCellValueFactory(new PropertyValueFactory<>("author"));
            col_count.setCellValueFactory(new PropertyValueFactory<>("count"));
            table.setItems(data);

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }


    void setComboBox(){
        comboBox.getItems().add("id");
        comboBox.getItems().add("title");
        comboBox.getItems().add("author");
    }

    ObservableList<BooksModel> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getBooks();
        setComboBox();
    }
}
