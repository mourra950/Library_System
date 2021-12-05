package Controller;

import Classes.book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class adminReturnView {
    public TableColumn Lib1;
    @FXML
    private TextField EnterAuthor;

    @FXML
    private TextField EnterGenre;

    @FXML
    private TextField EnterId;

    @FXML
    private TextField EnterLib;

    @FXML
    private TextField EnterTitle;

    @FXML
    private TextField EnterUser;
    ObservableList<book> list = FXCollections.observableArrayList();

    @FXML
    private TableColumn<book, String> Lib;
    @FXML
    private TableColumn<book, String> Author;

    @FXML
    private TableColumn<book, String> Genre;

    @FXML
    private TableColumn<book, String> Id;

    @FXML
    private TableColumn<book, String> Title;

    @FXML
    private TableView<book> table;


    @FXML
    void back(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/GUI/adminPage.fxml")));
        Scene Scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("main page");
        stage.setScene(Scene);
        stage.centerOnScreen();
        stage.show();

    }


    @FXML
    void initialize() throws SQLException {
        initcol();
        loadTables();

    }

    private void loadTables() throws SQLException {
        String url = "jdbc:sqlite:src/DB/LibraryDB.db";
        Connection c = DriverManager.getConnection(url);
        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery("select * from Borrowed ");//WHERE PersonId = '"+Login.User.getPersonId()+"'
        list.clear();
        while (rs.next()) {
            String Titlex = rs.getString("Title");
            String Authorx = rs.getString("Author");
            String Idx = rs.getString("Id");
            String Genrex = rs.getString("Genre");
            String libx = rs.getString("Lib");

            list.add(new book(Titlex, Idx, Authorx, Genrex, libx));
            System.out.println(list);
        }
        table.getItems().clear();
        table.getItems().addAll(list);
        c.close();


    }

    public void initcol() {
        Author.setCellValueFactory(new PropertyValueFactory<>("author"));
        Id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Title.setCellValueFactory(new PropertyValueFactory<>("title"));
        Genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        Lib.setCellValueFactory(new PropertyValueFactory<>("lib"));
    }

    public void Refresh(ActionEvent actionEvent) throws SQLException {
        loadTables();
    }

    public void Return(ActionEvent actionEvent) throws SQLException {
        if (!EnterAuthor.getText().isEmpty() && !EnterId.getText().isEmpty() && !EnterGenre.getText().isEmpty() && !EnterUser.getText().isEmpty() && !EnterTitle.getText().isEmpty() && !EnterLib.getText().isEmpty()) {
            String url = "jdbc:sqlite:src/DB/LibraryDB.db";
            Connection c = DriverManager.getConnection(url);
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select * from library Where Author='" + EnterAuthor.getText() + "' AND Id +'" + EnterId.getText() + "' AND Lib ='" + EnterLib.getText() + "' AND Genre='" + EnterGenre.getText() + "' AND Title = '" + EnterTitle.getText() + "' ");//WHERE PersonId = '"+Login.User.getPersonId()+"'
            rs.next();
            String Price = rs.getString("Price");
            PreparedStatement input = c.prepareStatement("DELETE FROM Borrowed WHERE PersonId ='" + EnterUser.getText() + "' AND Author='" + EnterAuthor.getText() + "' AND Id +'" + EnterId.getText() + "' AND Lib ='" + EnterLib.getText() + "' AND Genre='" + EnterGenre.getText() + "' AND Title = '" + EnterTitle.getText() + "' LIMIT 1;");
            input.executeUpdate();
            loadTables();
            c.close();
            AlertBox.display("price", "please collect " + Price + " USD ");
        } else {
            AlertBox.display("error", "fill the nescacassery info");
        }
    }
}
