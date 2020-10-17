package Controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    @FXML
    private AnchorPane contentPane;

    @FXML
    private AnchorPane titlePane;

    @FXML
    private FontAwesomeIconView btnExit;

    private double x = 0;
    private double y = 0;

    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void clossScene() {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }


    public void loadUser() {
        try {

            Stage productsStage = new Stage();
            Parent user = FXMLLoader.load(getClass().getClassLoader().getResource("Views/frmUserEdit.fxml"));
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
