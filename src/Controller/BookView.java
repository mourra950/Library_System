package Controller;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import Classes.book;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class BookView {
    ObservableList<book> list = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Add;


    @FXML
    private Button Delete;

    @FXML
    private TextField EnterAuthor;

    @FXML
    private TextField EnterGenre;

    @FXML
    private TextField EnterId;

    @FXML
    private TextField EnterTitle;

    @FXML
    private TextField EnterTopic;

    @FXML
    private TableColumn<book, String> Author;

    @FXML
    private TableColumn<book, String> Genre;

    @FXML
    private TableColumn<book, String> Id;

    @FXML
    private TableColumn<book, String> Title;

    @FXML
    private Button Search;

    @FXML
    private TableView<book> table;

    @FXML
    void AddBook(ActionEvent event) {

    }

    @FXML
    void delete(ActionEvent event) {

    }

    @FXML
    void search(ActionEvent event) {

    }

    @FXML
    void initialize() throws Exception {

        initcol();
        loadTables("select * from Books");

    }

    private void loadTables(String filter) throws SQLException {
        String url = "jdbc:sqlite:src/DB/LibraryDB.db";
        Connection c = DriverManager.getConnection(url);
        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery(filter);

        while (rs.next()) {
            String Titlex = rs.getString("Title");
            String Authorx = rs.getString("Author");
            String Idx = rs.getString("Id");
            String Genrex = rs.getString("Genre");

            list.add(new book(Titlex, Idx, Authorx, Genrex));
            System.out.println(list);
        }
        table.getItems().addAll(list);
        c.close();


    }

    public void initcol() {
        Author.setCellValueFactory(new PropertyValueFactory<>("author"));
        Id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Title.setCellValueFactory(new PropertyValueFactory<>("title"));
        Genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
    }
}
