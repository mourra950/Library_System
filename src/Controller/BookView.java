package Controller;

import Classes.LibraryCollection;
import Classes.book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class BookView implements Initializable {

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

    ObservableList<book> list= FXCollections.observableArrayList(
            new book("zeby", 123,"omar","zeby",true,true),
            new book("zeby", 12,"moniem","zebomar",true,true)
    );
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Author.setCellValueFactory(new PropertyValueFactory<LibraryCollection,String>("Author"));
        Genre.setCellValueFactory(new PropertyValueFactory<LibraryCollection,String>("Genre"));
        Id.setCellValueFactory(new PropertyValueFactory<LibraryCollection,Integer>("Id"));
        Title.setCellValueFactory(new PropertyValueFactory<LibraryCollection,String>("Title"));
        table.setItems(list);
    }
}
