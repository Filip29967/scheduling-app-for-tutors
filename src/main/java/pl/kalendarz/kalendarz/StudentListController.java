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



public class StudentListController {
    @FXML
    private Button backButton;
    @FXML
    private Button addStudentButton;
    @FXML
    private TableView<Student> tableView;
    @FXML
    private TableColumn<Student, String> firstName;
    @FXML
    private TableColumn<Student, String> lastName;
    @FXML
    private TableColumn<Student, String> dayOfWeek;
    @FXML
    private TableColumn<Student, LocalTime> time;
    @FXML
    private TableColumn<Student, Integer> id;
    @FXML
    private Button deleteStudentButton;

    @FXML
    public void openDeleteStudentView() throws IOException {
        FXMLLoader loader = new FXMLLoader(SchedulerApplication.class.getResource("deleteStudent-view"));
        Stage stage = (Stage) deleteStudentButton.getScene().getWindow();
        Scene scene = new Scene(loader.load(), 1000, 700);
        stage.setScene(scene);

    }

    @FXML
    public void goBack() throws IOException {
        FXMLLoader loader = new FXMLLoader(SchedulerApplication.class.getResource("main-view.fxml"));
        Stage stage = (Stage) backButton.getScene().getWindow();
        Scene scene = new Scene(loader.load(), 1000, 700);
        stage.setTitle("Kalendarz Korepetytora");
        stage.setScene(scene);
    }

    public void initialize() {
        try {
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            dayOfWeek.setCellValueFactory(new PropertyValueFactory<>("dayOfWeek"));
            time.setCellValueFactory(new PropertyValueFactory<>("time"));
            DatabaseManager db = new DatabaseManager();
            db.createTable();
            ObservableList<Student> students = FXCollections.observableArrayList(db.getStudents());
            tableView.setItems(students);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void openAddStudentView() {
        try {
            FXMLLoader loader = new FXMLLoader(SchedulerApplication.class.getResource("addStudent-view.fxml"));
            Stage stage = (Stage) addStudentButton.getScene().getWindow();
            Scene scene = new Scene(loader.load(), 1000, 700);
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
