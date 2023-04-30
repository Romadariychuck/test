package com.example.smis;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Check_Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField teo;

    @FXML
    private TextField body;

    @FXML
    private TextField color;

    @FXML
    private TextField consumes;

    @FXML
    private Label price;

    @FXML
    private Button print;

    @FXML
    private TextField svarka;
    @FXML
    private TextField electro;
    @FXML
    private CheckBox status;
    @FXML
    void method(MouseEvent event){

    }
    @FXML
    void initialize() {
        print.setOnAction(event -> {
            System.out.println("[Ваш чек успішно збережений]");
            print.getScene().getWindow().hide();

        });
    }
    public int resultcheck(){

        return Integer.parseInt(teo.getText());

    }
    public int tocheck(){
        Integer tocheckres = Integer.valueOf(teo.getText());

        return tocheckres;
    }
    public int bodycheck(){
        Integer bodycheckres = Integer.valueOf(body.getText());

        return bodycheckres;
    }
    public int colorcheck(){
        Integer colorckeckres = Integer.valueOf(color.getText());

        return colorckeckres;
    }
    public int consumescheck(){
        Integer consumescheckres = Integer.valueOf(consumes.getText());

        return consumescheckres;
    }
    public int svarkacheck(){
        Integer svarkacheckres = Integer.valueOf(svarka.getText());

        return svarkacheckres;
    }
    public int electro(){
        Integer electronika = Integer.valueOf(electro.getText());

        return electronika;
    }
}