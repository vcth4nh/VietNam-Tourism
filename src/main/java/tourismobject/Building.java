package tourismobject;

import java.util.ArrayList;

public abstract class Building extends TourismObject {
    private final static String type = "dbo:Building";

    protected int space;
    protected int towerHeight;
    protected int buildingType;

    protected Building(String label, double lat, double long_, String abstract_) {
        super(label, lat, long_, abstract_);
    }
}