package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import tourismobject.Pagoda;
import tourismobject.TourismObject;

public class Controller implements Initializable {

    @FXML
    private TableView<TourismObject> table;

    @FXML
    private TableColumn<TourismObject, String> label;

    @FXML
    private TableColumn<TourismObject, Float> latitude;

    @FXML
    private TableColumn<TourismObject, Float> longtitude;

    @FXML
    private TableColumn<TourismObject, String> type;

    @FXML
    private TreeView<String> treeView;

    private ObservableList<TourismObject> pagodaList = FXCollections.observableArrayList(
            new Pagoda("Chua mot cot", 12.54, 15.65, "historic building"),
            new Pagoda("Chua mot cot", 12.54, 15.65, "historic building"),
            new Pagoda("Chua mot cot", 12.54, 15.65, "historic building"));

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub

        TreeItem<String> vnTourism = new TreeItem<>("VN TOURISM");

        TreeItem<String> building = new TreeItem<>("BUILDING");
        TreeItem<String> naturalPlace = new TreeItem<>("NATURAL PLACE");

        TreeItem<String> skyscraper = new TreeItem<>("SKYSCRAPER");
        TreeItem<String> historicBuilding = new TreeItem<>("HISTORIC BUILDING");
        TreeItem<String> religiousBuilding = new TreeItem<>("RELIGIOUS BUILDING");
        TreeItem<String> museum = new TreeItem<>("MUSEUM");
        TreeItem<String> pagoda = new TreeItem<>("PAGODA");

        TreeItem<String> nationalPark = new TreeItem<>("NATIONAL PARK");
        TreeItem<String> cave = new TreeItem<>("CAVE");
        TreeItem<String> beach = new TreeItem<>("BEACH");
        TreeItem<String> bodyOfWater = new TreeItem<>("BODY OF WATER");

        building.getChildren().addAll(skyscraper, historicBuilding, religiousBuilding, museum, pagoda);
        naturalPlace.getChildren().addAll(nationalPark, cave, beach, bodyOfWater);

        vnTourism.getChildren().addAll(building, naturalPlace);

        treeView.setRoot(vnTourism);

    }

    @FXML
    void selectItem(MouseEvent event) {
        TreeItem<String> item = (TreeItem<String>) treeView.getSelectionModel().getSelectedItem();
        if (item == null)
            return;
        if (item.getValue() == "HISTORIC BUILDING") {
            setItem(pagodaList);
        } else if (item.getValue() == "RELIGIOUS BUILDING") {
            setItem(pagodaList);
        } else if (item.getValue() == "SKYSCRAPER") {
            setItem(pagodaList);
        } else if (item.getValue() == "MUSEUM") {
            setItem(pagodaList);
        } else if (item.getValue() == "NATIONAL PARK") {
            setItem(pagodaList);
        } else if (item.getValue() == "CAVE") {
            setItem(pagodaList);
        } else if (item.getValue() == "BEACH") {
            setItem(pagodaList);
        } else if (item.getValue() == "BODY OF WATER") {
            setItem(pagodaList);
        } else if (item.getValue() == "PAGODA") {
            setItem(pagodaList);
        }

        System.out.println(item.getValue());
    }

    private void setItem(ObservableList<TourismObject> listItem) {
        label.setCellValueFactory(new PropertyValueFactory<TourismObject, String>("label"));
        latitude.setCellValueFactory(new PropertyValueFactory<TourismObject, Float>("lat"));
        longtitude.setCellValueFactory(new PropertyValueFactory<TourismObject, Float>("long_"));
        type.setCellValueFactory(new PropertyValueFactory<TourismObject, String>("abstract_"));
        table.setItems(listItem);
    }

}
