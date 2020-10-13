package Controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DepartmentController implements Initializable {

    @FXML
    private JFXButton btnCloss;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void clossScene(){
        Stage stage = (Stage) btnCloss.getScene().getWindow();
        stage.close();
    }
}
