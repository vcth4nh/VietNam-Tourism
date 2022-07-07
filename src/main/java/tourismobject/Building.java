package tourismobject;

import java.util.ArrayList;

public abstract class Building extends TourismObject {
    private final static String type = "dbo:Building";
    private ArrayList<Building> allBuilding;

    protected int space;
    protected int towerHeight;
    protected int buildingType;

    public static void main(String[] args) {
//        System.out.println(ClassUtils.getInheritedFieldsName(new Building()));
    }
}