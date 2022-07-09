package tourismData;
import java.util.ArrayList;


public abstract class BuildingData extends TourismObjectData {
    private final static String type = "dbo:Building";

    protected int space;
    protected int towerHeight;
    protected int buildingType;
    public BuildingData(String label, double lat, double long_, String abstract_) {
        super(label, lat, long_, abstract_);
    }

    // protected Building(String label, double lat, double long_, String abstract_)
    // {
    // super(label, lat, long_, abstract_);
    // }
}