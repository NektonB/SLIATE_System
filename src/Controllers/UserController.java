package Controllers;

import DataControllers.DataReader;
import DataControllers.DataWriter;
import Modules.AD_Status;
import Modules.User;
import Modules.UserType;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    DataWriter dataWriter;
    DataReader dataReader;

    Alerts alerts;

    User user;
    UserType userType;
    AD_Status ad_status;
    @FXML
    private JFXButton btnViewUser;
    @FXML
    private Label lblTitle;
    @FXML
    private JFXTextField txtFullName;
    @FXML
    private JFXTextField txtNIC;
    @FXML
    private JFXTextField txtContactNumber;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtUserName;
    @FXML
    private JFXComboBox<String> cmbUserType;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXComboBox<String> cmbStatus;
    @FXML
    private JFXButton btnSave;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            dataReader = ObjectGenerator.getDataReader();
            dataWriter = ObjectGenerator.getDataWriter();
            user = ObjectGenerator.getUser();
            userType = ObjectGenerator.getUserType();
            ad_status = ObjectGenerator.getAd_status();
            alerts = ObjectGenerator.getAlerts();

            dataReader.fillStatusCombo(cmbStatus);
            cmbStatus.setValue("Active");
            dataReader.fillUserTypeCombo(cmbUserType);
            cmbUserType.setValue("Guest");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean validateFiled() {
        if (!txtNIC.getText().isEmpty()) {
            txtNIC.setStyle("-fx-background-color: none");
        } else {
            txtNIC.setStyle("-fx-background-color: rgba(255,0,0,0.1)");
            //txtNIC.setStyle("-fx-border-color: transparent transparent rgba(255,0,0,) transparent");
        }

        if (!txtUserName.getText().isEmpty()) {
            txtUserName.setStyle("-fx-background-color: none");
        } else {
            txtUserName.setStyle("-fx-background-color: rgba(255,0,0,0.1)");
            //txtNIC.setStyle("-fx-border-color: transparent transparent rgba(255,0,0,) transparent");
        }

        if (!txtPassword.getText().isEmpty()) {
            txtPassword.setStyle("-fx-background-color: none");
        } else {
            txtPassword.setStyle("-fx-background-color: rgba(255,0,0,0.1)");
            //txtNIC.setStyle("-fx-border-color: transparent transparent rgba(255,0,0,) transparent");
        }

        /*if (!cmbUserType.getValue().isEmpty()) {
            cmbUserType.setStyle("-fx-background-color: none");
        } else {
            cmbUserType.setStyle("-fx-background-color: rgba(255,0,0,0.1)");
            //txtNIC.setStyle("-fx-border-color: transparent transparent rgba(255,0,0,) transparent");
        }

        if (!cmbStatus.getValue().isEmpty()) {
            cmbStatus.setStyle("-fx-background-color: none");
        } else {
            cmbStatus.setStyle("-fx-background-color: rgba(255,0,0,0.1)");
            //txtNIC.setStyle("-fx-border-color: transparent transparent rgba(255,0,0,) transparent");
        }*/

        return !txtNIC.getText().isEmpty() && !txtUserName.getText().isEmpty() && !txtPassword.getText().isEmpty() && !cmbUserType.getValue().isEmpty() && !cmbUserType.getValue().isEmpty();
    }

    public void resetFiled() {
        if (!txtNIC.getText().isEmpty()) {
            txtNIC.setStyle("-fx-background-color: none");
            //txtNIC.setStyle("-fx-border-color: none");
        }

        if (!txtUserName.getText().isEmpty()) {
            txtUserName.setStyle("-fx-background-color: none");
        }

        if (!txtPassword.getText().isEmpty()) {
            txtPassword.setStyle("-fx-background-color: none");
        }

        /*if (!cmbUserType.getSelectionModel().isEmpty()) {
            cmbUserType.setStyle("-fx-background-color: none");
        }

        if (!cmbStatus.getSelectionModel().isEmpty()) {
            cmbUserType.setStyle("-fx-background-color: none");
        }*/
    }

    public void saveUser() {
        try {
            if (validateFiled()) {
                user.setFullName(txtFullName.getText());
                user.setNic(txtNIC.getText());
                user.setContactNumber(txtContactNumber.getText());
                user.setEmail(txtEmail.getText());
                user.setUserName(txtUserName.getText());
                user.setPassword(txtPassword.getText());

                dataReader.getUT_DetailsByType(cmbUserType.getValue());
                dataReader.getADS_DetailsByStatus(cmbStatus.getValue());

                String operation = dataWriter.SU_User();
                if (operation.equals("Save")) {
                    resetAll();
                    alerts.getSuccessNotify("User Registration", "Congratulation Chief..!\nUser registration successful");
                } else if (operation.equals("Update")) {
                    resetAll();
                    alerts.getSuccessNotify("User Update", "Congratulation Chief..!\nUser update successful");
                } else if (operation.equals("failed")) {
                    alerts.getSuccessNotify("Error", "Sorry Chief..!\nOperation unsuccessful");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveUser_key(KeyEvent event) {
        if (event.isControlDown() && event.getCode().equals(KeyCode.S)) {
            saveUser();
        }
    }

    public void resetAll() {
        txtFullName.setText("");
        txtNIC.setText("");
        txtContactNumber.setText("");
        txtEmail.setText("");
        txtUserName.setText("");
        txtPassword.setText("");
        cmbStatus.setValue("Active");
        cmbUserType.setValue("Guest");

        user.resetAll();
        userType.resetAll();
        ad_status.resetAll();
    }

    public void loadViewUser() {
        try {
            Stage productsStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Views/frmViewUser.fxml"));
            Parent user = loader.load();
            productsStage.setTitle("User");
            Scene scene = new Scene(user);
            productsStage.setScene(scene);
            productsStage.initStyle(StageStyle.UNDECORATED);
            //productsStage.getIcons().add(new Image("/images/Main_01.png"));
            productsStage.setResizable(false);
            productsStage.initModality(Modality.APPLICATION_MODAL);

            ViewUserController controller = loader.getController();
            //controller.setLoadDataFunction(resetAll());

            productsStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
}
