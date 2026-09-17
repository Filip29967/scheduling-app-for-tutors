package pl.kalendarz.kalendarz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class StudentListController {
    @FXML
    private Button backButton;
    @FXML
    private Button addStudentButton;
    @FXML
    private TableView<Student> tableView;
    @FXML
    private TableColumn<Student, Integer> id;
    @FXML
    private TableColumn<Student, String> firstName;
    @FXML
    private TableColumn<Student, String> lastName;
    @FXML
    private TableColumn<Student, String> dayOfWeek;
    @FXML
    private TableColumn<Student, LocalTime> time;
    @FXML
    private TableColumn<Student, Boolean> select;
    @FXML
    private Button deleteStudentButton;
    @FXML
    private Button selectAllButton;
    @FXML
    private Label statusLabel;
    @FXML
    private Button editButton;

    private boolean allSelected = false;

    @FXML
    public void selectAllStudents() {
        allSelected = !allSelected;
        for (Student student : tableView.getItems()) {
            student.setSelected(allSelected);
        }
        if (allSelected) {
            selectAllButton.setText("Odznacz wszystkich");
        } else {
            selectAllButton.setText("Zaznacz wszystkich");
        }
    }

    @FXML
    public void goEdit() {
        List<Student> selected = new ArrayList<>();
        for (Student student : tableView.getItems()) {
            if (student.isSelected()) {
                selected.add(student);
            }
        }

        if (selected.size() != 1) {
            statusLabel.setText("Zaznacz dokładnie jednego ucznia do edycji");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(SchedulerApplication.class.getResource("editStudent-view.fxml"));
            Scene scene = new Scene(loader.load(), 1000, 700);
            scene.getStylesheets().add(SchedulerApplication.class.getResource("style.css").toExternalForm());
            EditController controller = loader.getController();
            controller.setStudent(selected.get(0));
            Stage stage = (Stage) editButton.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void openDeleteStudentView() throws IOException {
        FXMLLoader loader = new FXMLLoader(SchedulerApplication.class.getResource("deleteStudent-view.fxml"));
        Stage stage = (Stage) deleteStudentButton.getScene().getWindow();
        Scene scene = new Scene(loader.load(), 1000, 700);
        stage.setScene(scene);

    }

    @FXML
    public void goBack() throws IOException {
        FXMLLoader loader = new FXMLLoader(SchedulerApplication.class.getResource("main-view.fxml"));
        Stage stage = (Stage) backButton.getScene().getWindow();
        Scene scene = new Scene(loader.load(), 1000, 700);
        scene.getStylesheets().add(SchedulerApplication.class.getResource("style.css").toExternalForm());
        stage.setTitle("Kalendarz Korepetytora");
        stage.setScene(scene);
    }

    public void initialize() {
        try {
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            select.setCellValueFactory(cellData -> cellData.getValue().selectedProperty());
            select.setCellFactory(CheckBoxTableCell.forTableColumn(select));
            firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            dayOfWeek.setCellValueFactory(new PropertyValueFactory<>("dayOfWeek"));
            time.setCellValueFactory(new PropertyValueFactory<>("time"));
            DatabaseManager db = new DatabaseManager();
            db.createTable();
            ObservableList<Student> students = FXCollections.observableArrayList(db.getStudents());
            tableView.setItems(students);
            tableView.setEditable(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void deleteSelectedStudents() {
        if (tableView.getItems().isEmpty()) {
            statusLabel.setText("Nie ma nic do usunięcia");
        }

        List<Student> selectedStudents = new ArrayList<>();
        DatabaseManager db = new DatabaseManager();
        for (Student student : tableView.getItems()) {
            if (student.isSelected())
            {
            db.deleteStudent(student.getId());
            selectedStudents.add(student);
            statusLabel.setText("Uczniowie usunięci");
            }
            else if (!student.isSelected())
            {
                statusLabel.setText("Nie wybrano żadnego ucznia");
            }
        }
        tableView.getItems().removeAll(selectedStudents);
    }

    @FXML
    public void openAddStudentView() {
        try {
            FXMLLoader loader = new FXMLLoader(SchedulerApplication.class.getResource("addStudent-view.fxml"));
            Stage stage = (Stage) addStudentButton.getScene().getWindow();
            Scene scene = new Scene(loader.load(), 1000, 700);
            scene.getStylesheets().add(SchedulerApplication.class.getResource("style.css").toExternalForm());
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
