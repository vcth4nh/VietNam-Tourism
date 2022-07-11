import java.io.File;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sparql.ExecQuery;
import utils.API4GUI;

import static org.junit.Assert.assertNotEquals;


public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainScene.fxml")));
            Scene scene = new Scene(root, Color.LIGHTSKYBLUE);
            String css = Objects.requireNonNull(this.getClass().getResource("application.css")).toExternalForm();

            scene.getStylesheets().add(css);
            stage.setScene(scene);
            Image icon = new Image("file:logo.jpg");
            File file = new File("");
            System.out.println(file.getAbsolutePath());

            stage.setTitle("Stage demo program");
            stage.getIcons().add(icon);
            stage.show();
            stage.setResizable(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        API4GUI.destroyCache();
        ExecQuery execQuery = new ExecQuery();
        /*
        String endpoint;
        ExecQuery execQuery = new ExecQuery(endpoint);
        */
        assertNotEquals("Empty database", 0, (execQuery.queryOnlineAll().size()));



        launch();
    }

}