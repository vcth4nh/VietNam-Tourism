package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tourismobject.Building;

public class TableController implements Initializable {
    @FXML
    private TableView<Building> table;
    private TableColumn<Building, String> labelColumn;
    private TableColumn<Building, Double> latitudeColumn;
    private TableColumn<Building, Double> longtitudeColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelColumn.setCellValueFactory(new PropertyValueFactory<>("Label1"));
        latitudeColumn.setCellValueFactory(new PropertyValueFactory<>("Label1"));
        longtitudeColumn.setCellValueFactory(new PropertyValueFactory<>("Label1"));

    }

}
