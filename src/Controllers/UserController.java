package Controllers;

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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {

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

    }

    public void loadUser() {
        try {
            Stage productsStage = new Stage();
            Parent user = FXMLLoader.load(getClass().getClassLoader().getResource("Views/frmViewUser.fxml"));
            productsStage.setTitle("User");
            Scene scene = new Scene(user);
            productsStage.setScene(scene);
            productsStage.initStyle(StageStyle.UNDECORATED);
            //productsStage.getIcons().add(new Image("/images/Main_01.png"));
            productsStage.setResizable(false);
            productsStage.initModality(Modality.APPLICATION_MODAL);
            productsStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
}
