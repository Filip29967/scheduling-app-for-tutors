package pl.kalendarz.kalendarz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;



public class StudentListControler {
    @FXML
    private Button przyciskPowrotu;
    @FXML
    private Button addStudentButton;
    @FXML
    private TableView<Uczen> tableView;
    @FXML
    private TableColumn<Uczen,String> imie;
    @FXML
    private TableColumn<Uczen,String> nazwisko;
    @FXML
    private TableColumn<Uczen,String> dzien;
    @FXML
    private TableColumn<Uczen, LocalTime> godzina;
    @FXML
    private TableColumn<Uczen,Integer> id;
    @FXML
    private Button deleteStButton;
    //private TableColumn<Uczen, Boolean> kolumnaUsun;
    @FXML
    public void goToDeleteStudentWin() throws IOException{
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("deleteStudent-view"));
        Stage stage = (Stage)deleteStButton.getScene().getWindow();
        Scene scene = new Scene(loader.load(),1000,700);
        stage.setScene(scene);

    }

    @FXML
    public void przyciskPowroc() throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Stage stage = (Stage)przyciskPowrotu.getScene().getWindow();
        Scene scene = new Scene(loader.load(),1000,700);
        stage.setTitle("Kalendarz Korepetytora");
        stage.setScene(scene);
    }

    public void initialize() {
        try {
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            imie.setCellValueFactory(new PropertyValueFactory<>("imie"));
            nazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
            dzien.setCellValueFactory(new PropertyValueFactory<>("dzien"));
            godzina.setCellValueFactory(new PropertyValueFactory<>("godzina"));
            //kolumnaUsun = new TableColumn<>("Usuń");
            //kolumnaUsun.setCellFactory();
            DataBaseMen db = new DataBaseMen();
            //db.deleteTable(); //odkomentuj żeby usunac tabele
            db.createTable();
            ObservableList<Uczen> uczniowie = FXCollections.observableArrayList(db.pobierzUczniow());
            tableView.setItems(uczniowie);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void dodajUcznia(){
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("dodajUcznia-view.fxml"));
            Stage stage = new Stage();
            stage = (Stage) addStudentButton.getScene().getWindow();
            Scene scene = new Scene(loader.load(), 1000, 700);
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
