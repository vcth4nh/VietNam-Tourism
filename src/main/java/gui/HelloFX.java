package gui;

import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class HelloFX extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
            Scene scene = new Scene(root, Color.LIGHTSKYBLUE);
            String css = this.getClass().getResource("application.css").toExternalForm();

            scene.getStylesheets().add(css);
            stage.setScene(scene);
            Image icon = new Image("file:logo.jpg");
            File file = new File("");
            System.out.println(file.getAbsolutePath());

            stage.setTitle("Stage demo program");
            stage.getIcons().add(icon);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

}