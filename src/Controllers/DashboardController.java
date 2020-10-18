package Controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    public static Stage productsStage = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    //Load User form (PopUp)**********************
    public void loadUserView() throws Exception {
        try {
            Stage productsStage = new Stage();
            Parent user = FXMLLoader.load(getClass().getClassLoader().getResource("Views/frmViewUser.fxml"));
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
            productsStage.setX(250);
            productsStage.setY(450);
            productsStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public void loadSubject() {
        try {

            Stage productsStage = new Stage();
            Parent user = FXMLLoader.load(getClass().getClassLoader().getResource("Views/frmSubject.fxml"));
            productsStage.setTitle("User");
            Scene scene = new Scene(user);
            productsStage.setScene(scene);
            productsStage.initStyle(StageStyle.UNDECORATED);
            //productsStage.getIcons().add(new Image("/images/Main_01.png"));
            productsStage.setResizable(false);
            productsStage.initModality(Modality.APPLICATION_MODAL);
            productsStage.setX(450);
            productsStage.setY(430);
            productsStage.showAndWait();
            // productsStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public void loadSemester() {
        try {

            Stage productsStage = new Stage();
            Parent user = FXMLLoader.load(getClass().getClassLoader().getResource("Views/frmSemester.fxml"));
            Scene scene = new Scene(user);
            productsStage.setScene(scene);
            productsStage.initStyle(StageStyle.UNDECORATED);
            //productsStage.getIcons().add(new Image("/images/Main_01.png"));
            productsStage.setResizable(false);
            productsStage.initModality(Modality.APPLICATION_MODAL);
            productsStage.setX(700);
            productsStage.setY(450);
            productsStage.showAndWait();
            // productsStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

}
