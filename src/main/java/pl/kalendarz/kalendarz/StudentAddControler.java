package pl.kalendarz.kalendarz;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalTime;

public class StudentAddControler {
    @FXML
    private Label uczenDodany;
    @FXML
    private TextField imieTextField;
    @FXML
    private TextField nazwiskoTextField;
    @FXML
    private ComboBox<String> comboDzien;
    @FXML
    private Spinner<Integer> spinerMinuty;
    @FXML
    Spinner<Integer> spinerGodziny;
    @FXML
    private Button przyciskPowrotu;
    @FXML
    public void powrot(){
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("kalendarz.fxml"));
            Stage stage = (Stage) przyciskPowrotu.getScene().getWindow();
            Scene scene = new Scene(loader.load(),1000,700 );
            stage.setScene(scene);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        }

    @FXML
    public void dodaj(){
        LocalTime godzina = LocalTime.of(spinerGodziny.getValue(),spinerMinuty.getValue());
        try {
            DataBaseMen db = new DataBaseMen();
            db.insertUczniowie(imieTextField.getText(), nazwiskoTextField.getText(), comboDzien.getValue(), godzina);
            System.out.println("Uczen dodany");
            uczenDodany.setText("Dodano!!!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Nie dodano");
        }
        }

    @FXML
    public void initialize(){
        comboDzien.getItems().addAll("Poniedziałek","Wtorek","Środa","Czwartek","Piątek","Sobota","Niedziela");
        spinerGodziny.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,23));
        spinerMinuty.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59));
    }

}
