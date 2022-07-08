package tourismobject;

import tourismobject.TourismObject;

public abstract class Park extends TourismObject {
    public Park(String label, double lat, double long_, String abstract_) {
        super(label, lat, long_, abstract_);
    }
}