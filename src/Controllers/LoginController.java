package Controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void loadMain() {
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/frmHome.fxml"));
//            Parent root = FXMLLoader.load(getClass().getResource("/Views/frmMain.fxml"));
            primaryStage.setTitle("Result Management System");
            Scene mainScene = new Scene(root);
            primaryStage.setScene(mainScene);
            primaryStage.initStyle(StageStyle.DECORATED);
            primaryStage.initModality(Modality.APPLICATION_MODAL);

            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


}
