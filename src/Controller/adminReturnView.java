package Controller;

import Classes.User;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class adminReturnView {

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
        if (!EnterUser.getText().isEmpty()) {
            String url = "jdbc:sqlite:src/DB/LibraryDB.db";
            Connection c = DriverManager.getConnection(url);
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select * from Borrowed WHERE PersonId='" + EnterUser.getText() + "'  ");//WHERE PersonId = '"+Login.User.getPersonId()+"'
            list.clear();
            while (rs.next()) {
                String Titlex = rs.getString("Title");
                String Authorx = rs.getString("Author");
                String Idx = rs.getString("Id");
                String Genrex = rs.getString("Genre");
                String libx = rs.getString("Lib");


                list.add(new book(Titlex, Idx, Authorx, Genrex, libx));

            }
            table.getItems().clear();
            table.getItems().addAll(list);
            c.close();

        }
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
        AlertBox.display("refreshed", "table refreshed");
    }

    public void Return(ActionEvent actionEvent) throws SQLException {
        if (!EnterAuthor.getText().isEmpty() && !EnterId.getText().isEmpty() && !EnterGenre.getText().isEmpty() && !EnterUser.getText().isEmpty() && !EnterTitle.getText().isEmpty() && !EnterLib.getText().isEmpty()) {
            String url = "jdbc:sqlite:src/DB/LibraryDB.db";
            Connection c = DriverManager.getConnection(url);
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select * from Books Where Author ='" + EnterAuthor.getText() + "' AND Id +'" + EnterId.getText() + "' AND Lib ='" + EnterLib.getText() + "' AND Genre='" + EnterGenre.getText() + "' AND Title = '" + EnterTitle.getText() + "' ");
            rs.next();
            String Price = rs.getString("Price");
            int CO = Integer.parseInt(rs.getString("BorrowCount"));
            int index = rs.getInt("index");
            CO--;
            rs = s.executeQuery("select * from Borrowed Where Author ='" + EnterAuthor.getText() + "' AND Id +'" + EnterId.getText() + "' AND Lib ='" + EnterLib.getText() + "' AND Genre='" + EnterGenre.getText() + "' AND Title = '" + EnterTitle.getText() + "' ");
            rs.next();
            int bindex = rs.getInt("borrowindex");
            PreparedStatement input = c.prepareStatement("DELETE FROM Borrowed WHERE borrowindex =='" + bindex + "' ");
            input.executeUpdate();
            PreparedStatement input2 = c.prepareStatement("UPDATE `main`.`Books` SET `BorrowCount` = '" + String.valueOf(CO) + "' WHERE (`index` == '" + index + "');");
            input2.executeUpdate();
            loadTables();
            c.close();
            AlertBox.display("price", "please collect " + Price + " USD ");
        } else {
            AlertBox.display("error", "fill the nescacassery info");
        }
    }

    public void clickme(MouseEvent mouseEvent) {
        EnterAuthor.setText(table.getSelectionModel().getSelectedItem().getAuthor());
        EnterGenre.setText(table.getSelectionModel().getSelectedItem().getGenre());
        EnterId.setText(table.getSelectionModel().getSelectedItem().getId());
        EnterTitle.setText(table.getSelectionModel().getSelectedItem().getTitle());
        EnterLib.setText(table.getSelectionModel().getSelectedItem().getLib());

    }
}
