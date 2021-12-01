package Controller;

import Classes.book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.ArrayList;

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
        boolean Condition = false;
        System.out.println(EnterAuthor.getText());
        if (!EnterAuthor.getText().equals("")) {
            filter += "WHERE Title = '" + EnterAuthor.getText() + "' ";
            Condition = true;
        }
        if (!EnterTitle.getText().equals("")) {
            if (Condition)
                filter += " AND ";
            filter += "WHERE Author = '" + EnterTitle.getText() + "' ";
            Condition = true;
        }
        if (!EnterId.getText().equals("")) {
            if (Condition)
                filter += " AND ";
            filter += "WHERE Id = '" + EnterId.getText() + "' ";
            Condition = true;
        }
        if (!EnterGenre.getText().equals("")) {
            if (Condition)
                filter += " AND ";
            filter += "WHERE Genre = '" + EnterGenre.getText() + "' ";
            Condition = true;
        }
        if (!libChoices.getSelectionModel().isEmpty())
        {
            if (Condition)
                filter += " AND ";
            filter += "WHERE Lib = '" + libChoices.getValue() + "' ";
            Condition = true;
        }
        filter += " ;";
        System.out.println(filter);
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
}
