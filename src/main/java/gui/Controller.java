package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Controller implements Initializable {
    @FXML
    private TreeView<String> treeView;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        TreeItem<String> rootItem = new TreeItem<>("Files");

        TreeItem<String> branchItem1 = new TreeItem<>("Pictures");
        TreeItem<String> branchItem2 = new TreeItem<>("Videos");
        TreeItem<String> branchItem3 = new TreeItem<>("Music");

        TreeItem<String> leafItem1 = new TreeItem<>("Picture1");
        TreeItem<String> leafItem2 = new TreeItem<>("Video1");
        TreeItem<String> leafItem3 = new TreeItem<>("Music1");
        branchItem1.getChildren().add(leafItem1);
        branchItem2.getChildren().add(leafItem2);
        branchItem3.getChildren().add(leafItem3);
        rootItem.getChildren().addAll(branchItem1, branchItem2, branchItem3);
        // treeView.setShowRoot(true);  
        treeView.setRoot(rootItem);
    }

    private Stage stage;
    private Scene scene;
    private Parent root;
    String css = this.getClass().getResource("application.css").toExternalForm();

    public void selectItem() {
        TreeItem item = treeView.getSelectionModel().getSelectedItem();

        if (item != null)
            System.out.println(item.getValue());
    }

}
