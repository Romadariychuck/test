package com.example.smis;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.smis.SQLDataConnection.DataBaseHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class Login_Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button enter;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private Label message;

    @FXML
    private Button register;

    @FXML
    void initialize() {
        register.setOnAction(event ->{
            register.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Register.fxml"));
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
        enter.setOnAction(event ->{
            String login = login_field.getText().trim();
            String password = password_field.getText().trim();
            if(!login.equals("") && !password.equals("")) {
                loginUser(login, password);
            } else {
                message.setText("Поля логін і пароль пусті.");
            }
        });
    }
                            //Метод для авторизації
    public void loginUser(String login, String password) {
    DataBaseHandler DBhan = new DataBaseHandler();
        User signup = new User();
        signup.setLogin(login);
        signup.setPassword(password);
        ResultSet resultSet = DBhan.getUser(signup);
        int counter = 0;
        while(true){
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            counter++;
        }
        if(counter >= 1){
            message.setText("Користувача знайдено\n" + login);
            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
                enter.setOnAction(event -> {
                    enter.getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    if (login.equals("administrator")) {
                        loader.setLocation(getClass().getResource("Admin_Panel.fxml"));}
                    else {
                        loader.setLocation(getClass().getResource("Enter.fxml"));}
                    try {
                        loader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                    EnterController enterController = loader.getController();
                });
            }
        else {
            message.setText("Користувача з такими даними не існує.\n Спробуйте ще раз...");
        }
    }
}
