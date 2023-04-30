package com.example.smis;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.smis.SQLDataConnection.DataBaseHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RegisterController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private Button reg_button;
    @FXML
    private Button cancel;

    @FXML
    private TextField register_number;

    @FXML
    private TextField telephone;

    @FXML
    private Label message;

    @FXML
    void initialize() {
        {
            DataBaseHandler dbHandler = new DataBaseHandler();
            reg_button.setOnAction(event -> {
                if(!check.isSelected()) {
                    message.setText("Ви не згодні з правилами користування...");
                } else if (!name.equals("") && !lastname.equals("") && !car.equals("") && !login.equals("") && !password.equals("") && !telephone.equals("") && !register_number.equals("")){
                    message.setText("Не всі поля були заповнені.\n Спробуйте ще...");
                } else {
                    dbHandler.regUser(name.getText(), lastname.getText(), car.getText(), login.getText(), password.getText(), telephone.getText(), register_number.getText());
                    reg_button.getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("Login.fxml"));
                    try {
                        loader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                }});

            cancel.setOnAction(event -> {
                cancel.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Login.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            });
            }
        }
}

