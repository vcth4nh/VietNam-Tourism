package tourismobject;

import java.util.ArrayList;

public abstract class Building extends TourismObject {
    protected String location;
    protected String completedDate;


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // protected Building(String label,  String abstract_) {
    //     super(label, lat, long_, abstract_);
    // }
}