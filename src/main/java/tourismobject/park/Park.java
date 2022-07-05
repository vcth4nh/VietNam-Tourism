package tourismobject.park;

import tourismobject.TourismObject;

import java.util.HashMap;

public abstract class Park extends TourismObject {
    protected static final HashMap<String, String> pQuery = new HashMap<>();

    public Park() {
        pQuery.put("type", "WikicatNationalParksOfVietnam");
    }
}
