package App.Controllers;

import App.Helpers.UserSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import App.Models.UsersModel;
import App.Helpers.ViewSession;
import App.Controllers.SceneController;
import javafx.stage.Stage;


public class AdminDashboardUserController extends SceneController implements Initializable {

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private TableView<UsersModel> table;

    @FXML
    private TableColumn<UsersModel, Integer> col_id;

    @FXML
    private TableColumn<UsersModel, String>col_name;

    @FXML
    private TableColumn<UsersModel, String>col_username;

    @FXML
    private TableColumn<UsersModel, Integer>col_role;

    @FXML
    private TableColumn<?, ?> col_control;

    @FXML
    private TextField search_item;

    @FXML
    void search(ActionEvent event) {
        data.removeAll();
        table.getItems().clear();
        try {
            UsersModel getUser = new UsersModel();
            String search_by = comboBox.getValue();
            if(search_by==null){
                search_by = "username";
            }
            ResultSet rs = getUser.search(search_by, search_item.getText());
            while (rs.next()) {
                data.add(new UsersModel(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("name"), rs.getInt("role")));
            }
            col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
            col_role.setCellValueFactory(new PropertyValueFactory<>("role"));
            table.setItems(data);

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    void itemClick(MouseEvent event) throws IOException {
        if (event.getClickCount() == 2) //Checking double click
        {
            if(table.getSelectionModel().getSelectedItem() != null) {
                ViewSession.setId(table.getSelectionModel().getSelectedItem().getId());
                Stage stage = (Stage) search_item.getScene().getWindow();
                this.changeScene(stage, "/templates/UserAccountView.fxml");
            }
        }
    }



    void getUsers() {
        try {
            UsersModel getUser = new UsersModel();
            ResultSet rs = getUser.getAll();
            while (rs.next()) {
                data.add(new UsersModel(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("name"), rs.getInt("role")));
            }
            col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
            col_role.setCellValueFactory(new PropertyValueFactory<>("role"));

            table.setItems(data);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }



    @FXML
    void addUser(ActionEvent event) throws IOException {
        Stage stage = (Stage) search_item.getScene().getWindow();
        this.changeScene(stage, "/templates/user_insertion.fxml");
    }


    void setComboBox(){
        comboBox.getItems().add("id");
        comboBox.getItems().add("name");
        comboBox.getItems().add("username");
    }

    @FXML
    void flushTable(ActionEvent event){
        data.removeAll();
        table.getItems().clear();
        getUsers();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setComboBox();
        getUsers();
    }


    ObservableList<UsersModel> data = FXCollections.observableArrayList();


}

