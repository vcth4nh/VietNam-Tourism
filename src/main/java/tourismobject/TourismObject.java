package tourismobject;

public abstract class TourismObject {
    protected String label;
    protected String lat;
    protected String long_;
    protected String abstract_;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLong_() {
        return long_;
    }

    public void setLong_(String long_) {
        this.long_ = long_;
    }

    public String getAbstract_() {
        return abstract_;
    }

    public void setAbstract_(String abstract_) {
        this.abstract_ = abstract_;
    }
}
