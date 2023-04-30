package com.example.smis;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.smis.SQLDataConnection.DataBaseHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Add_Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button add_button;

    @FXML
    private TextField car;

    @FXML
    private CheckBox check;

    @FXML
    private TextField lastname;

    @FXML
    private TextField login;

    @FXML
    private TextField name;

    @FXML
    private PasswordField password;

    @FXML
    private TextField register_number;

    @FXML
    private TextField telephone;

    @FXML
    void initialize() {
        {
            DataBaseHandler dbHandler = new DataBaseHandler();

            add_button.setOnAction(event -> {
                dbHandler.addUser(name.getText(), lastname.getText(), car.getText(), login.getText(), password.getText(),telephone.getText(), register_number.getText());
                add_button.getScene().getWindow().hide();
            });
        }
    }
}
