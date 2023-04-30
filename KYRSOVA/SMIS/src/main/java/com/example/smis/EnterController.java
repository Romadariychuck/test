package com.example.smis;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.smis.SQLDataConnection.DBC_For_Admin_Panel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class EnterController extends Login_Controller {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private TableView<User> Table;

    @FXML
    private TableColumn<User, String> Car;

    @FXML
    private TableColumn<User, String> Last_name;

    @FXML
    private TableColumn<User, String> Name;

    @FXML
    private TableColumn<User, String> Price;

    @FXML
    private TableColumn<User, String> Status_job;

    @FXML
    private TableColumn<User, String> Telephone;

    @FXML
    private Button home;
    ObservableList<User> User_List = FXCollections.observableArrayList();

    @FXML
    void initialize() {
            DBC_For_Admin_Panel connection = new DBC_For_Admin_Panel();
            try {
                Connection DBConnection = connection.getDBConnection();
                String InfoView = "SELECT Name, Last_name, Auto, Telephone, Price, Status FROM database.intousers WHERE (`id` = 41)";
                Statement statement = DBConnection.createStatement();
                ResultSet info = statement.executeQuery(InfoView);
                while (info.next()) {
                    String User_Name = info.getString("Name");
                    String User_Last_name = info.getString("Last_Name");
                    String User_Auto = info.getString("Auto");
                    String User_Telephone = info.getString("Telephone");
                    Integer User_Price = info.getInt("Price");
                    String User_Status = info.getString("Status");
                    User_List.add(new User(User_Name, User_Last_name, User_Auto, User_Telephone, User_Price, User_Status));

                    Table.setItems(User_List);

                    Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
                    Last_name.setCellValueFactory(new PropertyValueFactory<>("Last_Name"));
                    Car.setCellValueFactory(new PropertyValueFactory<>("Auto"));
                    Telephone.setCellValueFactory(new PropertyValueFactory<>("Telephone"));
                    Price.setCellValueFactory(new PropertyValueFactory<>("Price"));
                    Status_job.setCellValueFactory(new PropertyValueFactory<>("Status"));

                }

                // Подія для виходу з Облікового запису
                home.setOnAction(event -> {
                    home.getScene().getWindow().hide();
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
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                Logger.getLogger(EnterController.class.getName()).log(Level.SEVERE, null, e);
                e.printStackTrace();
                throw new RuntimeException(e);
            }
    }
}



