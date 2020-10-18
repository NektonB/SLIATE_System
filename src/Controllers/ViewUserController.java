package Controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewUserController implements Initializable {

    @FXML
    private AnchorPane contentPane;

    @FXML
    private AnchorPane titlePane;

    @FXML
    private FontAwesomeIconView btnExit;

    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void closeScene() {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }
}
