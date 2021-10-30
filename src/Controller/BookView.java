package Controller;

import Classes.LibraryCollection;
import Classes.book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class BookView implements Initializable {
    int z=0;
    @FXML
    private TableColumn<LibraryCollection, String> Author;

    @FXML
    private TableColumn<LibraryCollection, String> Genre;

    @FXML
    private TableColumn<LibraryCollection, Integer> Id;

    @FXML
    private TableColumn<LibraryCollection, String> Title;

    @FXML
    private TableColumn<LibraryCollection, String> Topic;

    @FXML
    private TableView<book> table;
    @FXML
    private Button Add;


    @FXML
    private Button Search;

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
    void AddBook(ActionEvent event) {
        //LibraryCollection.AddBook();

    }

    @FXML
    void delete(ActionEvent event) {
        //LibraryCollection.RemoveBook();
    }

    @FXML
    void search(ActionEvent event) {

    }

    ObservableList<book> list= FXCollections.observableArrayList(
            //new book("zeby", z,"omar","zeby","rrrrr"),
            //new book("zeby", 12,"moniem","zebomar","zeb")

    );
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Author.setCellValueFactory(new PropertyValueFactory<LibraryCollection,String>("Author"));
        Genre.setCellValueFactory(new PropertyValueFactory<LibraryCollection,String>("Genre"));
        Id.setCellValueFactory(new PropertyValueFactory<LibraryCollection,Integer>("Id"));
        Title.setCellValueFactory(new PropertyValueFactory<LibraryCollection,String>("Title"));
        Topic.setCellValueFactory(new PropertyValueFactory<LibraryCollection,String>("Topic"));
        table.setItems(list);
    }

    public void putinfo(ActionEvent actionEvent) {
        z+=10;
    }



}
