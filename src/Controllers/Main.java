package Controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/frmLogin.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/Views/frmLogin.fxml"));
        primaryStage.setTitle("Login");
        Scene mainScene = new Scene(root);
        //Font.loadFont(getClass().getClassLoader().getResource("Fonts/SriBhashitha.ttf").toExternalForm(), 15);
        //mainScene.getStylesheets().add(getClass().getClassLoader().getResource("CSS/Decorator.css").toExternalForm());
        primaryStage.setScene(mainScene);
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
