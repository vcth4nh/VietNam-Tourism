package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.jena.rdf.model.Model;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sparql.Construct;
import tourismData.DataType;
import tourismData.TourismObjectData;
import utils.API4GUI;
import utils.JsonUtils;

public class Controller implements Initializable {

    @FXML
    private TableView<TourismObjectData> table;

    @FXML
    private TableColumn<TourismObjectData, String> label;

    @FXML
    private TableColumn<TourismObjectData, Float> latitude;

    @FXML
    private TableColumn<TourismObjectData, Float> longtitude;

    @FXML
    private TableColumn<TourismObjectData, String> type;

    @FXML
    private TreeView<String> treeView;

    private ObservableList<TourismObjectData> pagodaList = FXCollections.observableArrayList(getList("Pagoda"));
    private ObservableList<TourismObjectData> nationalParkList = FXCollections
            .observableArrayList(getList("NationalPark"));
    private ObservableList<TourismObjectData> parkList = FXCollections.observableArrayList(getList("Park"));
    private ObservableList<TourismObjectData> naturalList = FXCollections.observableArrayList(getList("Natural"));
    private ObservableList<TourismObjectData> tourismList = FXCollections.observableArrayList(getList("TourismObject"));
    private ObservableList<TourismObjectData> buildingList = FXCollections.observableArrayList(getList("Building"));
    private ObservableList<TourismObjectData> lakeList = FXCollections.observableArrayList(getList("Lake"));

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub

        TreeItem<String> vnTourism = new TreeItem<>(DataType.VNTOURISM.getName());

        TreeItem<String> building = new TreeItem<>(DataType.BUILDING.getName());
        TreeItem<String> naturalPlace = new TreeItem<>(DataType.NATURALPLACE.getName());

        // TreeItem<String> skyscraper = new TreeItem<>("SKYSCRAPER");
        // TreeItem<String> historicBuilding = new TreeItem<>("HISTORIC BUILDING");
        // TreeItem<String> religiousBuilding = new TreeItem<>("RELIGIOUS BUILDING");
        // TreeItem<String> museum = new TreeItem<>("MUSEUM");
        TreeItem<String> pagoda = new TreeItem<>(DataType.PAGODA.getName());

        TreeItem<String> nationalPark = new TreeItem<>(DataType.NATTIONALPARK.getName());
        // TreeItem<String> cave = new TreeItem<>("CAVE");
        // TreeItem<String> beach = new TreeItem<>("BEACH");
        // TreeItem<String> bodyOfWater = new TreeItem<>("BODY OF WATER");
        TreeItem<String> lake = new TreeItem<>(DataType.LAKE.getName());

        building.getChildren().addAll(pagoda);
        naturalPlace.getChildren().addAll(nationalPark, lake);

        vnTourism.getChildren().addAll(building, naturalPlace);

        treeView.setRoot(vnTourism);

        Construct queryOnline = new Construct();
        Model db = queryOnline.queryOnlineAll();

    }

    @FXML
    void selectItem(MouseEvent event) {
        TreeItem<String> item = (TreeItem<String>) treeView.getSelectionModel().getSelectedItem();
        if (item == null)
            return;
        if (item.getValue().equals(DataType.VNTOURISM.getName())) {
            setItem(tourismList);

        } else if (item.getValue().equals(DataType.BUILDING.getName())) {
            setItem(buildingList);
        } else if (item.getValue().equals(DataType.NATURALPLACE.getName())) {
            setItem(naturalList);
        } else if (item.getValue().equals(DataType.PAGODA.getName())) {
            setItem(pagodaList);
        } else if (item.getValue().equals(DataType.NATTIONALPARK.getName())) {
            setItem(nationalParkList);
        } else if (item.getValue().equals(DataType.LAKE.getName())) {
            setItem(lakeList);
        } else if (item.getValue().equals(DataType.PARK.getName())) {
            setItem(parkList);
        }
        System.out.println(item.getValue());
    }

    private void setItem(ObservableList<TourismObjectData> listItem) {
        label.setCellValueFactory(new PropertyValueFactory<TourismObjectData, String>("label"));
        latitude.setCellValueFactory(new PropertyValueFactory<TourismObjectData, Float>("lat"));
        longtitude.setCellValueFactory(new PropertyValueFactory<TourismObjectData, Float>("long_"));
        type.setCellValueFactory(new PropertyValueFactory<TourismObjectData, String>("abstract_"));
        table.setItems(listItem);
    }

    public static void main(String[] args) throws Exception {
        String path = JsonUtils.getJsonPath("Natural");
        System.out.println(path);
        JSONObject jsonObject = API4GUI.ObjectToJson("Natural");
        JSONArray graph = (JSONArray) jsonObject.get("@graph");
        ArrayList<TourismObjectData> list = new ArrayList<>();
        int index = 0;
        for (Object i : graph) {
            index++;
            JSONObject element = (JSONObject) i;
            String id = (String) element.get("@id");
            String latString = (String) element.get("lat");
            String longString = (String) element.get("long");
            if (latString == null)
                latString = "0";
            if (longString == null)
                longString = "0";
            double lat = Double.parseDouble(latString);
            double long_ = Double.parseDouble(longString);
            JSONObject labelObject = (JSONObject) element.get("label");
            JSONObject abstractObject = (JSONObject) element.get("abstract");
            String label = (String) labelObject.get("@value");
            String abstract_ = (String) abstractObject.get("@value");
            TourismObjectData newItem = new TourismObjectData(label, lat, long_, abstract_);
            list.add(newItem);
        }
    }

    private ArrayList<TourismObjectData> getList(String className) {
        try {
            String path = JsonUtils.getJsonPath(className);
            System.out.println(path);
            JSONObject jsonObject = API4GUI.ObjectToJson(className);
            JSONArray graph = (JSONArray) jsonObject.get("@graph");
            if (graph == null)
                return new ArrayList<>();
            ArrayList<TourismObjectData> list = new ArrayList<>();
            int index = 0;
            for (Object i : graph) {
                index++;
                JSONObject element = (JSONObject) i;
                String id = (String) element.get("@id");
                String latString = (String) element.get("lat");
                String longString = (String) element.get("long");
                if (latString == null)
                    latString = "0";
                if (longString == null)
                    longString = "0";
                double lat = Double.parseDouble(latString);
                double long_ = Double.parseDouble(longString);
                JSONObject labelObject = (JSONObject) element.get("label");
                JSONObject abstractObject = (JSONObject) element.get("abstract");
                String label = (String) labelObject.get("@value");
                String abstract_ = (String) abstractObject.get("@value");
                TourismObjectData newItem = new TourismObjectData(label, lat, long_, abstract_);
                list.add(newItem);
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
