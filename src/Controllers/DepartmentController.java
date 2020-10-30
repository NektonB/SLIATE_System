package Controllers;

import DataControllers.DataReader;
import DataControllers.DataWriter;
import Modules.AD_Status;
import Modules.Department;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
            department.setName(txtName.getText());
            ad_status.setId(1);

            String operation = dataWriter.suDepartment();
            if (operation.equals("Save")) {
                alerts.getSuccessNotify("Department Registration", "Congratulation Chief..!\nDepartment registration successful");
            } else if (operation.equals("Update")) {
                alerts.getSuccessNotify("Department Update", "Congratulation Chief..!\nDepartment update successful");
            } else if (operation.equals("failed")) {
                alerts.getErrorNotify("Error", "Sorry Chief..!\nOperation unsuccessful");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveDepartment_key(KeyEvent event) {
        if (event.isControlDown() && event.getCode().equals(KeyCode.S)) {
            saveDepartment();
        }
    }

    public void deleteDepartment() {
        try {
            String operation = dataWriter.deleteDepartment(txtName.getText());
            System.out.println("Call");
            if (operation.equals("Delete")) {
                alerts.getSuccessNotify("Department Delete", "Congratulation Chief..!\nDepartment delete successful");
            } else if (operation.equals("failed")) {
                alerts.getErrorNotify("Error", "Sorry Chief..!\nOperation unsuccessful");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAnchorPaneKeyEvent(KeyEvent event) {
        if (event.isControlDown() && event.getCode().equals(KeyCode.S)) {
            saveDepartment();
        } else if (event.getCode().equals(KeyCode.DELETE)) {
            deleteDepartment();
        }
    }

    public void closeMe() {
        Stage stage = (Stage) btnDismiss.getScene().getWindow();
        stage.close();
    }
}
