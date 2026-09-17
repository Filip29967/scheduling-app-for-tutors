package pl.kalendarz.kalendarz;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.time.LocalTime;

public class EditController
{
    @FXML
    private Button saveButton;
    @FXML
    private Label statusLabel;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private ComboBox<String> dayComboBox;
    @FXML
    private ComboBox<Integer> minuteComboBox;
    @FXML
    private ComboBox<Integer> hourComboBox;
    @FXML
    private Button backButton;

    private Student student;

    public void setStudent(Student student) {
        this.student = student;
        firstNameTextField.setText(student.getFirstName());
        lastNameTextField.setText(student.getLastName());
        dayComboBox.setValue(student.getDayOfWeek());
        hourComboBox.setValue(student.getTime().getHour());
        minuteComboBox.setValue(student.getTime().getMinute());
    }
    @FXML
    public void goBack() {
        try {
            FXMLLoader loader = new FXMLLoader(SchedulerApplication.class.getResource("studentList-view.fxml"));
            Stage stage = (Stage) backButton.getScene().getWindow();
            Scene scene = new Scene(loader.load(), 1000, 700);
            scene.getStylesheets().add(SchedulerApplication.class.getResource("style.css").toExternalForm());
            stage.setScene(scene);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void initialize()
    {
        dayComboBox.getItems().addAll("Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota", "Niedziela");
        for (int i = 0; i < 24; i++) hourComboBox.getItems().add(i);
        for (int i = 0; i < 60; i++) minuteComboBox.getItems().add(i);
    }
    @FXML
    public void saveStudent() {
        if (firstNameTextField.getText().isBlank() || lastNameTextField.getText().isBlank() || 
            dayComboBox.getValue() == null || hourComboBox.getValue() == null || minuteComboBox.getValue() == null) {
            statusLabel.setText("Uzupełnij wszystkie pola!");
            return;
        }

        LocalTime time = LocalTime.of(hourComboBox.getValue(), minuteComboBox.getValue());

        try {
            DatabaseManager db = new DatabaseManager();
            db.editStudent(student.getId(), firstNameTextField.getText(), lastNameTextField.getText(), dayComboBox.getValue(), time);
            statusLabel.setText("Zapisano!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            statusLabel.setText("Nie udało się zapisać");
        }
    }
}
