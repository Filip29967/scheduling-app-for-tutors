package pl.kalendarz.kalendarz;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;



public class MainController {
    @FXML
    private Label titleLabel;
    @FXML
    private Button studentButton;

    @FXML
    public void openList()  {
        try {
            FXMLLoader loader = new FXMLLoader(SchedulerApplication.class.getResource("studentList-view.fxml"));
            Stage stage = (Stage) studentButton.getScene().getWindow();
            Scene scene = new Scene(loader.load(), 1000, 700);
            stage.setTitle("Lista Uczniów");
            stage.setScene(scene);

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }
}
