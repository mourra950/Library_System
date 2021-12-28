package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

public class userPage {

    @FXML
    void Logout(ActionEvent event) throws IOException {
        Login.reset();
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/GUI/LoginPage.fxml")));
        Scene Scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Borrow page");
        stage.setScene(Scene);
        stage.centerOnScreen();
        stage.show();

    }

    @FXML
    void OpenBooks(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/GUI/userBookView.fxml")));
        Scene Scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Books view");
        stage.setScene(Scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void OpenBorrowedBooks(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/GUI/userBorrowView.fxml")));
        Scene Scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("borrowed books view");
        stage.setScene(Scene);
        stage.centerOnScreen();
        stage.show();

    }

    @FXML
    void OpenLocation(ActionEvent event) {
        Desktop desktop = java.awt.Desktop.getDesktop();
        try {
            URI oURL = new URI("https://github.com/mourra950/Library_System");
            desktop.browse(oURL);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

    }

}
