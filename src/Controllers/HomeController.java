package Controllers;

import Modules.User;
import Modules.UserType;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    //ReportViewer reportViewer;
    Alerts alerts;
    User user;
    UserType userType;

    @FXML
    private StackPane rootPane;

    @FXML
    private JFXButton btnHome;

    @FXML
    private JFXButton btnStudent;

    @FXML
    private JFXButton btnCourse;

    private JFXButton temp = null;
    private JFXButton recover = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        StackPane pane = null;
        try {
            Thread readyData = new Thread(() -> {
                ObjectGenerator.getAutoBackup().AutoGet();
            });
            readyData.setName("Main Controller");
            readyData.start();
            //reportViewer = ObjectGenerator.getReportViewer();
            alerts = ObjectGenerator.getAlerts();
            user = ObjectGenerator.getUser();
            userType = ObjectGenerator.getUserType();

            pane = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Dashboard.fxml"));
            rootPane.getChildren().setAll(pane);
            btnHome.setStyle("-fx-background-color: #a34758; -fx-font-weight: bold;");
            btnStudent.setStyle("-fx-background-color: transparent;");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exitNow(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Attention !, This is a System Exit conformation.");
        alert.setContentText("Hi User, Do you really want to exit ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.exit(0);
        } else {

        }
    }

    public void loadDashboard(ActionEvent event) throws IOException {
        StackPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Dashboard.fxml"));
        rootPane.getChildren().setAll(pane);

        btnHome.setStyle("-fx-background-color: #a34758; -fx-font-weight: bold;");

        btnStudent.setStyle("-fx-background-color: transparent;");
    }

    public void loadStudent(ActionEvent event) throws IOException {
        StackPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("Views/pnlStudent.fxml"));
        rootPane.getChildren().setAll(pane);

        btnStudent.setStyle("-fx-background-color: #a34758; -fx-font-weight: bold;");

        btnHome.setStyle("-fx-background-color: transparent");
    }

    public void loadCourse(ActionEvent event) throws IOException {
        StackPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("Views/pnlCourse.fxml"));
        rootPane.getChildren().setAll(pane);

        btnCourse.setStyle("-fx-background-color: #a34758; -fx-font-weight: bold;");

        btnHome.setStyle("-fx-background-color: transparent");
        btnStudent.setStyle("-fx-background-color: transparent");
    }

    public void loadUser() {
        try {

            Stage productsStage = new Stage();
            Parent user = FXMLLoader.load(getClass().getClassLoader().getResource("Views/frmUser.fxml"));
            productsStage.setTitle("User");
            Scene scene = new Scene(user);
            productsStage.setScene(scene);
            productsStage.initStyle(StageStyle.UTILITY);
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
