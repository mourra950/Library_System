package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import Classes.Librarian;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class BookView {


    public TextField EnterPrice;
    public TextField EnterCount;
    public ChoiceBox<Object> libChoices;
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
    private TableColumn<book, Integer> Count;
    @FXML
    private TableColumn<book, Integer> Price;
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
    private Button Search;

    @FXML
    private TableView<book> table;
    ArrayList<String> libraries = new ArrayList<>();

    @FXML
    void AddBook(ActionEvent event) throws SQLException {
        if (!EnterAuthor.getText().equals("") && !EnterTitle.getText().equals("") && !EnterId.getText().equals("") && !EnterGenre.getText().equals("") && !libChoices.getSelectionModel().isEmpty() && !EnterPrice.getText().equals("") && !EnterCount.getText().equals("")) {
            boolean condition = false;

            String url = "jdbc:sqlite:src/DB/LibraryDB.db";
            Connection c = DriverManager.getConnection(url);
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select * from Books WHERE Title = '" + EnterTitle.getText() + "' ;");//WHERE Title = '" + EnterTitle.getText() + "' AND WHERE Lib = '" + libChoices.getValue() + "'
            while (rs.next()) {
                condition = true;
            }

            if (!condition) {
                System.out.println("sadwdawdawd");
                String z = "0";
                s.executeUpdate("INSERT INTO `main`.`Books`(`Title`,`Id`,`Author`,`Genre`,`Count`,`BorrowCount`,`Price`,'Lib') VALUES ('" + EnterTitle.getText() + ",'" + EnterId.getText() + ",'" + EnterAuthor.getText() + ",'" + EnterGenre.getText() + ",'" + EnterCount + "','"+z+"','" + EnterPrice.getText() + ",'" + libChoices.getValue() + "');");

            } else {
                AlertBox.display("can not add", "book already in the library");
            }

            c.close();
        } else {
            AlertBox.display("error can not add", "provide all info");
        }
    }


    @FXML
    void search(ActionEvent event) throws SQLException {
        String filter = "select * from Books ";
        boolean Condition = false;
        System.out.println(EnterAuthor.getText());
        if (!EnterAuthor.getText().equals("")) {
            filter += "WHERE Author = '" + EnterAuthor.getText() + "' ";
            Condition = true;
        }
        if (!EnterTitle.getText().equals("")) {
            if (Condition)
                filter += " AND ";
            filter += "WHERE Title = '" + EnterTitle.getText() + "' ";
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
        if (!libChoices.getSelectionModel().isEmpty() && !libChoices.getValue().equals("all")) {
            if (Condition)
                filter += " AND ";
            filter += "WHERE Lib = '" + libChoices.getValue() + "' ";
            Condition = true;
        }
        if (!EnterPrice.getText().equals("")) {
            if (Condition)
                filter += " AND ";
            filter += "WHERE Price = '" + Price.getText() + "' ";
            Condition = true;
        }
        if (!EnterCount.getText().equals("")) {
            if (Condition)
                filter += " AND ";
            filter += "WHERE Count = '" + Count.getText() + "' ";
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
            int countx = rs.getInt("Count");
            int price = rs.getInt("Price");

            list.add(new book(Titlex, Idx, Authorx, Genrex, libx, countx, price));
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
        Count.setCellValueFactory(new PropertyValueFactory<>("count"));
        Price.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    public void Update(ActionEvent actionEvent) {
    }

    public void backToMain(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/GUI/adminPage.fxml")));
        Scene Scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("admin page");
        stage.setScene(Scene);
        stage.centerOnScreen();
        stage.show();
    }
}
