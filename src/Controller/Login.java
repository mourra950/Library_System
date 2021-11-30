package Controller;

import connect.testjdbc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
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
    void LoadRegister(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/GUI/hello-view.fxml")));
        Scene Scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Register");
        stage.setScene(Scene);
        stage.centerOnScreen();
        stage.show();

    }

    @FXML
    void LoginUser(ActionEvent event) throws IOException {
        boolean found = false;
        boolean admin=false;
        Connection con = testjdbc.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            String sql = "SELECT * FROM 'main'.'person' WHERE email = '"+Mail.getText()+"' AND password = '"+Password.getText()+"' ;";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next() && !found) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("Password");

               if(email.equals(Mail.getText()) && password.equals(Password.getText()))
                 found = true;
               if(rs.getString("admin").equals("true"))
                   admin=true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(found && admin)
        {
            login(event,"adminPage.fxml");
        }
        else if (found)
        {
            login(event,"userPage.fxml");
        }
        else
        {
            AlertBox.display("not found","error try again or register");
        }
    }

    void login(ActionEvent event ,String page) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/GUI/"+page)));
        Scene Scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("main");
        stage.setScene(Scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void initialize() {
        assert LogBut != null : "fx:id=\"LogBut\" was not injected: check your FXML file 'LoginPage.fxml'.";
        assert Mail != null : "fx:id=\"Mail\" was not injected: check your FXML file 'LoginPage.fxml'.";
        assert Password != null : "fx:id=\"Password\" was not injected: check your FXML file 'LoginPage.fxml'.";
        assert RegButton != null : "fx:id=\"RegButton\" was not injected: check your FXML file 'LoginPage.fxml'.";

    }

}