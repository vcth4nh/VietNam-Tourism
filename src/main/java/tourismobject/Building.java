package tourismobject;

import java.util.ArrayList;

public abstract class Building extends TourismObject {
    private final static String type = "dbo:Building";

    protected int space;
    protected int towerHeight;
    protected int buildingType;
}