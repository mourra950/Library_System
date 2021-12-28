package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

import Classes.Library;
import Classes.book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class libView {

    public TextField EnterId;
    public TextField EnterLibName;
    public TextField EnterLibAdress;
    ObservableList<Library> list = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Library, String> ID;
    @FXML
    private TableColumn<Library, String> Adress;

    @FXML
    private TableColumn<Library, String> Lib;

    @FXML
    private TableView<Library> table;

    @FXML
    void AddBook(ActionEvent event) throws SQLException {
        try {
            if (!EnterLibAdress.getText().equals("") && !EnterLibName.getText().equals("")) {
                String url = "jdbc:sqlite:src/DB/LibraryDB.db";
                Connection c = DriverManager.getConnection(url);
                PreparedStatement input = c.prepareStatement("INSERT INTO `main`.`library`(`adress`,`name`) VALUES ('" + EnterLibAdress.getText() + "','" + EnterLibName.getText() + "') ");
                input.executeUpdate();
                c.close();
                AlertBox.display("success", "book added successfully");
            }
        } catch (Exception E) {
            AlertBox.display("error", "something went wrong");
        }
        loadTables();
    }

    @FXML
    void Update(ActionEvent event) throws SQLException {
        String url = "jdbc:sqlite:src/DB/LibraryDB.db";
        Connection c = DriverManager.getConnection(url);
        PreparedStatement input = c.prepareStatement("DELETE FROM library WHERE id ='" + EnterId.getText() + "';");
        input.executeUpdate();
        loadTables();
        c.close();
        Connection c2 = DriverManager.getConnection(url);
        PreparedStatement input2 = c2.prepareStatement("DELETE FROM Borrowed WHERE Lib ='" + EnterId.getText() + "';");
        input2.executeUpdate();
        c2.close();
        Connection c3 = DriverManager.getConnection(url);
        PreparedStatement input3 = c3.prepareStatement("DELETE FROM Books WHERE Lib ='" + EnterId.getText() + "';");
        input3.executeUpdate();

        c3.close();
        AlertBox.display("success", "book deleted successfully from db");
    }

    @FXML
    void backToMain(ActionEvent event) throws IOException {
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
        ResultSet rs = s.executeQuery("select * from library");
        list.clear();
        while (rs.next()) {
            String adress = rs.getString("adress");
            String lib = rs.getString("name");
            String idx = rs.getString("id");
            list.add(new Library(adress, lib, idx));
        }
        table.getItems().clear();
        table.getItems().addAll(list);
        c.close();


    }

    public void initcol() {
        ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        Lib.setCellValueFactory(new PropertyValueFactory<>("name"));
        Adress.setCellValueFactory(new PropertyValueFactory<>("adress"));


    }

}
