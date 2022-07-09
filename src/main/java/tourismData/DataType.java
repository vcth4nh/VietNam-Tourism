package tourismData;

public enum DataType {
    VNTOURISM("VN TOURISM"),
    BUILDING("BUILDING"),
    NATURALPLACE("NATURAL PLACE"),
    LAKE("LAKE"),
    PARK("PARK"),
    NATTIONALPARK("NATIONAL PARK"),
    PAGODA("PAGODA")
    ;

    private String name;

    DataType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
