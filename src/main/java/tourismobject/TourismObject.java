package tourismobject;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class TourismObject {
    @JsonProperty("label")
    protected String label;

    @JsonProperty("lat")
    protected double lat;

    @JsonProperty("long")
    protected double long_;

    @JsonProperty("abstract")
    protected String abstract_;

    public static void main(String[] args) {

    }
}
