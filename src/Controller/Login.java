package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Login {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button LogBut;

    @FXML
    private TextField Mail;

    @FXML
    private PasswordField Password;

    @FXML
    private Button RegButton;

    @FXML
    void LoadRegister(ActionEvent event) {

    }

    @FXML
    void LoginUser(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert LogBut != null : "fx:id=\"LogBut\" was not injected: check your FXML file 'LoginPage.fxml'.";
        assert Mail != null : "fx:id=\"Mail\" was not injected: check your FXML file 'LoginPage.fxml'.";
        assert Password != null : "fx:id=\"Password\" was not injected: check your FXML file 'LoginPage.fxml'.";
        assert RegButton != null : "fx:id=\"RegButton\" was not injected: check your FXML file 'LoginPage.fxml'.";

    }

}