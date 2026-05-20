package pl.kalendarz.kalendarz;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalTime;

public class StudentAddController {
    @FXML
    private Label statusLabel;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private ComboBox<String> dayComboBox;
    @FXML
    private Spinner<Integer> minuteSpinner;
    @FXML
    private Spinner<Integer> hourSpinner;
    @FXML
    private Button backButton;

    @FXML
    public void goBack() {
        try {
            FXMLLoader loader = new FXMLLoader(SchedulerApplication.class.getResource("studentList-view.fxml"));
            Stage stage = (Stage) backButton.getScene().getWindow();
            Scene scene = new Scene(loader.load(), 1000, 700);
            stage.setScene(scene);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void addStudent() {
        LocalTime time = LocalTime.of(hourSpinner.getValue(), minuteSpinner.getValue());
        try {
            DatabaseManager db = new DatabaseManager();
            db.insertStudent(firstNameTextField.getText(), lastNameTextField.getText(), dayComboBox.getValue(), time);
            System.out.println("Uczen dodany");
            statusLabel.setText("Dodano!!!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Nie dodano");
        }
    }

    @FXML
    public void initialize() {
        dayComboBox.getItems().addAll("Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota", "Niedziela");
        hourSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23));
        minuteSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
    }

}
