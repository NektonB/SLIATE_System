package Controllers;

import com.jfoenix.controls.JFXButton;
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

public class CourseController implements Initializable {

    @FXML
    private JFXButton btnClose;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void loadCourse() throws Exception {
        try {
            Stage productsStage = new Stage();
            Parent user = FXMLLoader.load(getClass().getClassLoader().getResource("Views/frmCourse.fxml"));
            productsStage.setTitle("Add new courses");
            Scene scene = new Scene(user);
            productsStage.setScene(scene);
            productsStage.initStyle(StageStyle.UNDECORATED);
            productsStage.initModality(Modality.APPLICATION_MODAL);
            productsStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clossScene(){
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    public void loadDepartment() {
        try {

            Stage productsStage = new Stage();
            Parent user = FXMLLoader.load(getClass().getClassLoader().getResource("Views/frmDepartment.fxml"));
            productsStage.setTitle("User");
            Scene scene = new Scene(user);
            productsStage.setScene(scene);
            productsStage.initStyle(StageStyle.UNDECORATED);
            //productsStage.getIcons().add(new Image("/images/Main_01.png"));
            productsStage.setResizable(false);
            productsStage.initModality(Modality.APPLICATION_MODAL);
            productsStage.setX(580);
            productsStage.setY(245);
            productsStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
}
