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



    public ChoiceBox<Object> libChoices;
    ObservableList<book> list = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Add;

    public TextField EnterPrice;
    public TextField EnterCount;

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
    private TableColumn<book, String> Count;
    @FXML
    private TableColumn<book, String> Price;
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
                if (rs.getString("Lib").equals(libChoices.getValue()))
                    condition = true;
            }
            c.close();
            if (!condition) {

                String z = "0";
                Connection ca = DriverManager.getConnection(url);
                PreparedStatement input = ca.prepareStatement("INSERT INTO `main`.`Books`(`Title`,`Id`,`Author`,`Genre`,`Count`,`BorrowCount`,`Price`,'Lib') VALUES ('" + EnterTitle.getText() + "','" + EnterId.getText() + "','" + EnterAuthor.getText() + "','" + EnterGenre.getText() + "','" + EnterCount.getText() + "','" + z + "','" + EnterPrice.getText() + "','" + libChoices.getValue() + "');");

                input.executeUpdate();

                ca.close();
                loadTables("select * from Books");


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
        boolean c2=false;
        boolean Condition = false;
        if (!EnterAuthor.getText().equals("")) {
            if(!c2) {
                filter += " WHERE ";
                c2=true;
            }
            filter += " Author = '" + EnterAuthor.getText() + "' ";
            Condition = true;
        }
        if (!EnterTitle.getText().equals("")) {
            if(!c2) {
                filter += " WHERE ";
                c2=true;
            }
            if (Condition)
                filter += " AND ";
            filter += " Title = '" + EnterTitle.getText() + "' ";
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
        if (!libChoices.getSelectionModel().isEmpty() && !libChoices.getValue().equals("all")) {
            if(!c2) {
                filter += " WHERE ";
                c2=true;
            }
            if (Condition)
                filter += " AND ";
            filter += " Lib = '" + libChoices.getValue() + "' ";
            Condition = true;
        }
        if (!EnterPrice.getText().equals("")) {
            if(!c2) {
                filter += " WHERE ";
                c2=true;
            }
            if (Condition)
                filter += " AND ";
            filter += " Price = '" + EnterPrice.getText() + "' ";
            Condition = true;
        }
        if (!EnterCount.getText().equals("")) {
            if(!c2) {
                filter += " WHERE ";
                c2=true;
            }
            if (Condition)
                filter += " AND ";
            filter += " Count = '" + EnterCount.getText() + "' ";
            Condition = true;
        }
        System.out.println(filter);

            loadTables(filter);

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
            String countx = rs.getString("Count");
            String price = rs.getString("Price");

            list.add(new book(Titlex, Idx, Authorx, Genrex, libx, countx, price));

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

    public void Update(ActionEvent actionEvent) throws SQLException {
        if (!EnterAuthor.getText().equals("") && !EnterTitle.getText().equals("") && !EnterId.getText().equals("") && !EnterGenre.getText().equals("") && !libChoices.getSelectionModel().isEmpty() && !EnterPrice.getText().equals("") && !EnterCount.getText().equals("")) {

            String url = "jdbc:sqlite:src/DB/LibraryDB.db";
            Connection c2 = DriverManager.getConnection(url);
            PreparedStatement input2 = c2.prepareStatement("DELETE from Books  WHERE  Author = '" + EnterAuthor.getText() + "'  AND  Title = '" + EnterTitle.getText() + "'  AND  Id = '" + EnterId.getText() + "'  AND  Genre = '" + EnterGenre.getText() + "'  AND  Lib = '" + libChoices.getValue() + "'  AND  Price = '" + EnterPrice.getText() + "'  AND  Count = '" + EnterCount.getText() + "';");
            input2.executeUpdate();
            c2.close();
            Connection c3 = DriverManager.getConnection(url);
            PreparedStatement input3 = c3.prepareStatement("DELETE from Borrowed  WHERE  Author = '" + EnterAuthor.getText() + "'  AND  Title = '" + EnterTitle.getText() + "'  AND  Id = '" + EnterId.getText() + "'  AND  Genre = '" + EnterGenre.getText() + "'  AND  Lib = '" + libChoices.getValue() + "'  AND  Price = '" + EnterPrice.getText() + "';");
            input3.executeUpdate();
            c3.close();
            loadTables("select * from Books");
            AlertBox.display("success", "done");
        }
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
