package tourismData;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TourismObjectData {
    private String label;
    private String abstract_;
    private String nativeName;
    private String location;
    private String completedDate;
    private String iata;
    private String icao;
    private String homepage;
    private String numberOfRooms;
    private String numberOfSuites;
    private String numberOfRestaurants;
    private String buildingType;
    private String floorCount;
    private String type;
    private String discovery;
    private String entranceCount;
    private String depth;
    private String length;
    private String geology;
    private String nearestCity;
    private String areaTotal;
    private String area;
    private String date;
    private String host;
    private String wikiPageExternalLink;
    private String stadium;
    private String year;
    private String id;
    private String founded;
    private String openingDate;

    public TourismObjectData(Map<String, String> itemMap) {
        this.label = itemMap.get("label");
        this.abstract_ = itemMap.get("abstract");
        this.nativeName = itemMap.get("nativeName");
        this.location = itemMap.get("location");
        this.completedDate = itemMap.get("completedDate");
        this.iata = itemMap.get("iata");
        this.icao = itemMap.get("icao");
        this.homepage = itemMap.get("homepage");
        this.numberOfRooms = itemMap.get("numberOfRooms");
        this.numberOfSuites = itemMap.get("numberOfSuites");
        this.numberOfRestaurants = itemMap.get("numberOfRestaurants");
        this.buildingType = itemMap.get("buildingType");
        this.floorCount = itemMap.get("floorCount");
        this.type = itemMap.get("type");
        this.discovery = itemMap.get("discovery");
        this.entranceCount = itemMap.get("entranceCount");
        this.depth = itemMap.get("depth");
        this.length = itemMap.get("length");
        this.geology = itemMap.get("geology");
        this.nearestCity = itemMap.get("nearestCity");
        this.areaTotal = itemMap.get("areaTotal");
        this.area = itemMap.get("area");
        this.date = itemMap.get("date");
        this.host = itemMap.get("host");
        this.wikiPageExternalLink = itemMap.get("wikiPageExternalLink");
        this.stadium = itemMap.get("stadium");
        this.year = itemMap.get("year");
        this.id = itemMap.get("id");
        this.founded = itemMap.get("founded");
        this.openingDate = itemMap.get("openingDate");
        this.ceased = itemMap.get("ceased");
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getAbstract_() {
        return abstract_;
    }

    public void setAbstract_(String abstract_) {
        this.abstract_ = abstract_;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(String completedDate) {
        this.completedDate = completedDate;
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public String getIcao() {
        return icao;
    }

    public void setIcao(String icao) {
        this.icao = icao;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(String numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public String getNumberOfSuites() {
        return numberOfSuites;
    }

    public void setNumberOfSuites(String numberOfSuites) {
        this.numberOfSuites = numberOfSuites;
    }

    public String getNumberOfRestaurants() {
        return numberOfRestaurants;
    }

    public void setNumberOfRestaurants(String numberOfRestaurants) {
        this.numberOfRestaurants = numberOfRestaurants;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public String getFloorCount() {
        return floorCount;
    }

    public void setFloorCount(String floorCount) {
        this.floorCount = floorCount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDiscovery() {
        return discovery;
    }

    public void setDiscovery(String discovery) {
        this.discovery = discovery;
    }

    public String getEntranceCount() {
        return entranceCount;
    }

    public void setEntranceCount(String entranceCount) {
        this.entranceCount = entranceCount;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getGeology() {
        return geology;
    }

    public void setGeology(String geology) {
        this.geology = geology;
    }

    public String getNearestCity() {
        return nearestCity;
    }

    public void setNearestCity(String nearestCity) {
        this.nearestCity = nearestCity;
    }

    public String getAreaTotal() {
        return areaTotal;
    }

    public void setAreaTotal(String areaTotal) {
        this.areaTotal = areaTotal;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getWikiPageExternalLink() {
        return wikiPageExternalLink;
    }

    public void setWikiPageExternalLink(String wikiPageExternalLink) {
        this.wikiPageExternalLink = wikiPageExternalLink;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFounded() {
        return founded;
    }

    public void setFounded(String founded) {
        this.founded = founded;
    }

    public String getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(String openingDate) {
        this.openingDate = openingDate;
    }

    public String getCeased() {
        return ceased;
    }

    public void setCeased(String ceased) {
        this.ceased = ceased;
    }

    private String ceased;
}
