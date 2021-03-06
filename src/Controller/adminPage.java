package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class adminPage {
    public void gotobooks(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/GUI/BookView.fxml")));
        Scene Scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("admin books view");
        stage.setScene(Scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void gotolibs(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/GUI/libView.fxml")));
        Scene Scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("admin lib view");
        stage.setScene(Scene);
        stage.centerOnScreen();
        stage.show();

    }

    public void logout(ActionEvent actionEvent) throws IOException {
        Login.reset();
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/GUI/LoginPage.fxml")));
        Scene Scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Login page");
        stage.setScene(Scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void gotoBorrow(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/GUI/adminBorrowView.fxml")));
        Scene Scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("borrow admin view");
        stage.setScene(Scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void gotoreturn(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/GUI/adminReturnView.fxml")));
        Scene Scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("return admin page");
        stage.setScene(Scene);
        stage.centerOnScreen();
        stage.show();
    }
}
