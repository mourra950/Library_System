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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class userBookView {

    ObservableList<book> list = FXCollections.observableArrayList();
    public ChoiceBox<String> libChoices;

    @FXML
    private TextField EnterAuthor;

    @FXML
    private TextField EnterGenre;

    @FXML
    private TextField EnterId;

    @FXML
    private TextField EnterTitle;
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
    void search(ActionEvent event) throws SQLException {
        String filter = "select * from 'main'.'Books' ";
        boolean c2=false;
        boolean Condition = false;

        if (!EnterAuthor.getText().equals("")) {
            if(!c2) {
                filter += " WHERE ";
                c2=true;
            }
                filter += "Title = '" + EnterAuthor.getText() + "' ";
            Condition = true;
        }
        if (!EnterTitle.getText().equals("")) {
            if(!c2) {
                filter += " WHERE ";
                c2=true;
            }
            if (Condition)
                filter += " AND ";
            filter += " Author = '" + EnterTitle.getText() + "' ";
            Condition = true;
        }
        if (!EnterId.getText().equals("")) {
            if(!c2) {
                filter += " WHERE ";
                c2=true;
            }
            if (Condition)
                filter += " AND ";
            filter += " Id = '" + EnterId.getText() + "' ";
            Condition = true;
        }
        if (!EnterGenre.getText().equals("")) {
            if(!c2) {
                filter += " WHERE ";
                c2=true;
            }
            if (Condition)
                filter += " AND ";
            filter += " Genre = '" + EnterGenre.getText() + "' ";
            Condition = true;
        }
        if (!libChoices.getSelectionModel().isEmpty() || !libChoices.getValue().equals("all") )
        {
            if(!c2) {
                filter += " WHERE ";
            }
            if (Condition)
                filter += " AND ";
            filter += " Lib = '" + libChoices.getValue() + "' ";
            Condition = true;
        }
        filter += " ;";

        if (Condition) {

            loadTables(filter);
        }
    }

    @FXML
    void initialize() throws Exception {
        String url = "jdbc:sqlite:src/DB/LibraryDB.db";
        Connection c = DriverManager.getConnection(url);
        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery("select * from library");
        ArrayList<String> libraries = new ArrayList<>();
        int i = 0;
        while (rs.next()) {
            libraries.add(rs.getString("name"));
        }
        libChoices.setItems(FXCollections.observableArrayList(libraries));
        c.close();
        initcol();
        loadTables("select * from Books");

    }

    private void loadTables(String filter) throws SQLException {
        String url = "jdbc:sqlite:src/DB/LibraryDB.db";
        Connection c = DriverManager.getConnection(url);
        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery(filter);
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

    public void initcol() {
        Author.setCellValueFactory(new PropertyValueFactory<>("author"));
        Id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Title.setCellValueFactory(new PropertyValueFactory<>("title"));
        Genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        Lib.setCellValueFactory(new PropertyValueFactory<>("lib"));
    }

    public void back(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/GUI/userPage.fxml")));
        Scene Scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("main user page");
        stage.setScene(Scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void borrow(ActionEvent actionEvent) {
    }


}
