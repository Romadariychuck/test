package com.example.smis;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import com.example.smis.SQLDataConnection.DBC_For_Admin_Panel;
import com.example.smis.SQLDataConnection.DataBaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Admin_Panel_Controller extends DataBaseHandler {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private TableView<User> able;

    @FXML
    private TableColumn<User, Integer> id;

    @FXML
    private TableColumn<User, String> auto;
    @FXML
    private TableColumn<User, Integer> price;

    @FXML
    private TableColumn<User, String> serial;

    @FXML
    private TableColumn<User, String> username;

    @FXML
    private TableColumn<User, String> lastname;

    @FXML
    private TableColumn<User, String> phone;

    @FXML
    private TableColumn<User, String> status;

    @FXML
    private Button add;

    @FXML
    private Button change;

    @FXML
    private Button delete;

    @FXML
    private Button exit;

    @FXML
    private Button check;
    @FXML
    private TextField User_Auto;

    @FXML
    private TextField User_Lastname;

    @FXML
    private TextField User_Name;

    @FXML
    private TextField User_Number;

    @FXML
    private TextField User_Serial;

    @FXML
    private TextField search_field;
    int methode(){
        Check_Controller A = new Check_Controller();
        int result = A.resultcheck();
        return result;
    }
    @FXML
    void initialize1(MouseEvent event) {
        User_Name.setText(able.getSelectionModel().getSelectedItem().getName());
        User_Lastname.setText(able.getSelectionModel().getSelectedItem().getLast_Name());
        User_Auto.setText(able.getSelectionModel().getSelectedItem().getAuto());
        User_Number.setText(able.getSelectionModel().getSelectedItem().getTelephone());
        User_Serial.setText(able.getSelectionModel().getSelectedItem().getRegister_Number());
    }
    ObservableList<User> Users_View_List = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        DBC_For_Admin_Panel connection = new DBC_For_Admin_Panel();
        try {
            Connection DBConnection = connection.getDBConnection();
            String InfoView = "SELECT id, Name, Last_name, Auto, Telephone, Register_Number, Price, Status FROM database.intousers WHERE Status = 'Очікується'";
            Statement statement = DBConnection.createStatement();
            ResultSet info = statement.executeQuery(InfoView);
            while (info.next()){
                Integer queryID = info.getInt("Id");
                String queryName = info.getString("Name");
                String queryLast_name = info.getString("Last_name");
                String queryAuto = info.getString("Auto");
                String queryTelephone = info.getString("Telephone");
                String queryRegister_Number = info.getString("Register_Number");
                Integer queryPrice = info.getInt("Price");
                String queryStatus = info.getString("Status");
        Users_View_List.add(new User(queryID, queryName, queryLast_name, queryAuto, queryTelephone, queryRegister_Number, queryPrice,queryStatus));

            }
                                            //Встановлення елементів в колонки таблиці
            able.setItems(Users_View_List);
            id.setCellValueFactory(new PropertyValueFactory<>("ID"));
            username.setCellValueFactory(new PropertyValueFactory<>("Name"));
            lastname.setCellValueFactory(new PropertyValueFactory<>("Last_Name"));
            phone.setCellValueFactory(new PropertyValueFactory<>("Telephone"));
            serial.setCellValueFactory(new PropertyValueFactory<>("Register_Number"));
            auto.setCellValueFactory(new PropertyValueFactory<>("Auto"));
            price.setCellValueFactory(new PropertyValueFactory<>("Price"));
            status.setCellValueFactory(new PropertyValueFactory<>("Status"));
            FilteredList<User> filteredList = new FilteredList<>(Users_View_List, b -> true);

                                //Пошук елементів списка за ключовими словами

            search_field.textProperty().addListener((observable, oldValue, newValue) ->{
                filteredList.setPredicate(user -> {
                    if(newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;

                    }
                    String Search = newValue.toLowerCase();
                    if(user.getName().toLowerCase().indexOf(Search) > -1) {

                        return true;

                    } else if (user.getLast_Name().toLowerCase().indexOf(Search) > -1) {
                        return true;
                    }
                    else if (user.getTelephone().toLowerCase().indexOf(Search) > -1) {
                        return true;
                    }
                    else if (user.getRegister_Number().toLowerCase().indexOf(Search) > -1) {
                        return true;
                    }
                    else if (user.getAuto().toLowerCase().indexOf(Search) > -1) {
                        return true;
                    }
                    else if (user.getStatus().toLowerCase().indexOf(Search) > -1) {
                        return true;
                    }
                    else
                        return false; //Нічого не знайдено
                });
            });
            SortedList<User> sortData = new SortedList<>(filteredList);
            sortData.comparatorProperty().bind(able.comparatorProperty());
            able.setItems(sortData);

                                    //Запит для видалення запису

            delete.setOnAction(event -> {
                User selectedItem = able.getSelectionModel().getSelectedItem();
                int selid = selectedItem.getID();
                String deleted = "DELETE FROM `database`.`intousers` WHERE (`id` = '"+selid+"');";
                PreparedStatement prST1;
                try {
                    prST1 = getDbConnection().prepareStatement(deleted);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                try {
                    prST1.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                delete.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Admin_Panel.fxml"));
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

                                            //Запит для Редагування запису
                change.setOnAction(event -> {
                    User selectedItem = able.getSelectionModel().getSelectedItem();
                    int selid = selectedItem.getID();
                    String Update = "UPDATE `database`.`intousers` SET `Name` = ?,`Last_name` = ?,`Auto` = ?,`Telephone`=?,`Register_Number`=? WHERE (`id` = '" + selid + "');";
                    PreparedStatement prST2;
                    try {
                        prST2 = getDbConnection().prepareStatement(Update);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        prST2.setString(1, User_Name.getText());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        prST2.setString(2, User_Lastname.getText());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    try {
                        prST2.setString(3, User_Auto.getText());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        prST2.setString(4, User_Number.getText());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        prST2.setString(5, User_Serial.getText());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        prST2.executeUpdate();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    change.getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("Admin_Panel.fxml"));
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
                                            //Виписування чеку

                check.setOnAction(event -> {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("Check.fxml"));
                        try {
                            loader.load();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Parent root = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();

                    int a = methode();
            User selectedItem = able.getSelectionModel().getSelectedItem();
            int selid = selectedItem.getID();
                    String Check = "UPDATE `database`.`intousers` SET `Price` = '" + a + "' WHERE (`id` = '" + selid + "');";
            PreparedStatement prST3 = null;
                    try {
                        prST3 = getDbConnection().prepareStatement(Check);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        prST3.executeUpdate();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            Logger.getLogger(Admin_Panel_Controller.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }

            //Подія для виходу із кабінету Адміністратора
            exit.setOnAction(event -> {

                exit.getScene().getWindow().hide();
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

            //Подія для переходу на форму додавання нового запису

            add.setOnAction(event -> {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Add.fxml"));
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