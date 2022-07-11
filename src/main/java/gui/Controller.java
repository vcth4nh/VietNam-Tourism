package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
import tourismData.TourismObjectData;
import utils.API4GUI;
import utils.ClassUtils;
import utils.JsonUtils;

public class Controller implements Initializable {

    @FXML
    private TableView<TourismObjectData> table;

    @FXML
    private TreeView<String> treeView;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub

        TreeItem<String> vnTourism = genTreeView("TourismObject");

        treeView.setRoot(vnTourism);
        // table.setScaleX(100);
        // table.setScaleY(100);

    }

    private String currentTopic;

    @FXML
    void selectItem(MouseEvent event) {
        TreeItem<String> item = (TreeItem<String>) treeView.getSelectionModel().getSelectedItem();
        if (item == null || item.getValue().equals(currentTopic))
            return;
        currentTopic = item.getValue();
        ObservableList<TourismObjectData> listItem = FXCollections.observableArrayList(getList(item.getValue()));
        setItem(listItem, item.getValue());

    }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
    private void setItem(ObservableList<TourismObjectData> listItem, String className) {
        List<String> fieldNames = ClassUtils.getFieldNames(className);
        List<TableColumn<TourismObjectData, ?>> columns = new ArrayList<>();
        for (String fieldName : fieldNames) {
            TableColumn<TourismObjectData, String> column = new TableColumn<>(fieldName);
            column.setCellValueFactory(new PropertyValueFactory<TourismObjectData, String>(fieldName));
            if(fieldName.equals("abstract_")) {
                column.setPrefWidth(200);
                column.setResizable(true);
                column.setReorderable(true);
            }
            columns.add(column);
        }
        table.getColumns().clear();
        table.getColumns().addAll(columns);
        table.setItems(listItem);

    }

    private List<String> visitedItem = new ArrayList<>();

    private TreeItem<String> genTreeView(String className) {
        TreeItem<String> item = new TreeItem<>(className);
        List<String> subClassesName = ClassUtils.getSubclassesName(className, true);
        if (subClassesName == null || subClassesName.isEmpty()) {
            visitedItem.add(className);
            return item;
        }
        for (String subName : subClassesName) {
            if (visitedItem.contains(subName))
                continue;
            TreeItem<String> subItem = genTreeView(subName);
            item.getChildren().add(subItem);
            visitedItem.add(subName);

        }
        return item;

    }

    public static void main(String[] args) throws Exception {
        // List a = (getListTest("NationalPark"));
        // getListTest("NationalPark");
    }

    // private String getJSONValue (JSONObject object, String key) {
    // JSONObject
    // while(!object.get(key) instanceof)
    // }

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
                Set<String> keys = element.keySet();
                HashMap<String, String> itemMap = new HashMap<String, String>();
                for (String key : keys) {
                    
                    if (key.equals("abstract_"))
                        key = "abstract";
                    if (key.equals("@id"))
                        continue;
                    String[] keyPref = key.split(":");
                    String keyLabel = key;
                    if(keyPref.length > 1) {
                        keyLabel = keyPref[1];
                    }
                    Object keyObject = element.get(key);
                    if (keyObject == null) {
                        itemMap.put(keyLabel, "");
                    } else if (keyObject instanceof Long || keyObject instanceof String) {
                        itemMap.put(keyLabel, keyObject.toString());
                    } else if (keyObject instanceof JSONArray) {
                        JSONArray keyArray = (JSONArray) keyObject;
                        StringBuilder arrayString = new StringBuilder();
                        for (int j = 0; j < keyArray.size(); j++) {
                            String val;
                            if (keyArray.get(j) instanceof JSONObject) {
                                val = (String) ((JSONObject) keyArray.get(j)).get("@value");
                            } else
                                val = (String) keyArray.get(j);

                            if (j == keyArray.size() - 1) {
                                arrayString.append(val + ".");
                            } else
                                arrayString.append(val + ", ");
                        }
                        itemMap.put(keyLabel, arrayString.toString());
                    } else {
                        String keyValue = (String) ((JSONObject) keyObject).get("@value");
                        itemMap.put(keyLabel, keyValue);
                    }
                }
                TourismObjectData newItem = new TourismObjectData(itemMap);
                list.add(newItem);
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
