package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

import Classes.Librarian;
import Classes.User;
import connect.testjdbc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class signUpPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField CPass;

    @FXML
    private Button Cancel;

    @FXML
    private TextField Mail;

    @FXML
    private TextField Name;

    @FXML
    private TextField Pass;

    @FXML
    void SignmeUP(ActionEvent event) throws IOException {
        if (!Name.getText().isEmpty() && !Pass.getText().isEmpty() && !CPass.getText().isEmpty() && !Mail.getText().isEmpty()) {
            boolean found = false;
            Connection con = testjdbc.connect();
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                String sql = "SELECT * FROM 'main'.'person' WHERE id = '" + Mail.getText() + "';";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    found = true;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if (!found) {
                User user = new User(Name.getText(), Mail.getText(), Pass.getText(), Mail.getText());
                Librarian.adduser(user);
                changescene(event,"LoginPage.fxml");
            } else {
                AlertBox.display("already used", "this mail is already used");
            }
        } else {
            AlertBox.display("error", "please provide all info");
        }

    }

        void changescene(ActionEvent event,String page) throws IOException {
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


    }

    public void Cancel(ActionEvent actionEvent) throws IOException {
        changescene(actionEvent,"LoginPage.fxml");

    }
}
