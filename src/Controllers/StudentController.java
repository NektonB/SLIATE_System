package Controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentController implements Initializable {
    @FXML
    private FontAwesomeIconView btnExit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    //Load User form (PopUp)**********************
    public void loadUser() throws Exception {
        try {
            Stage productsStage = new Stage();
            Parent user = FXMLLoader.load(getClass().getClassLoader().getResource("Views/frmStudent.fxml"));
            productsStage.setTitle("User Management");
            Scene scene = new Scene(user);
            productsStage.setScene(scene);
            productsStage.initStyle(StageStyle.UNDECORATED);
            productsStage.initModality(Modality.APPLICATION_MODAL);
            productsStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clossScene() {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }
}
