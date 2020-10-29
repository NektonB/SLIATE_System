package Controllers;

import DataControllers.DataReader;
import DataControllers.DataWriter;
import Modules.AD_Status;
import Modules.Department;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DepartmentController implements Initializable {

    DataWriter dataWriter;
    DataReader dataReader;

    Alerts alerts;

    Department department;
    AD_Status ad_status;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnDismiss;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            dataReader = ObjectGenerator.getDataReader();
            dataWriter = ObjectGenerator.getDataWriter();
            alerts = ObjectGenerator.getAlerts();
            department = ObjectGenerator.getDepartment();
            ad_status = ObjectGenerator.getAd_status();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveDepartment() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeScene() {
        Stage stage = (Stage) btnDismiss.getScene().getWindow();
        stage.close();
    }
}
