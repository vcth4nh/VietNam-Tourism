package tourismobject;

import org.apache.jena.rdf.model.Model;
import sparql.ExecQuery;

public class Skyscraper extends Building implements Queryable {
    private String buildingType;
    private int floorCount;

    @Override
    public Model queryModel(ExecQuery execQuery) {
        selector.add(SetTriple(Queryable.object, "dct:subject", "dbc:Skyscraper_office_buildings_in_Vietnam"));

        return execQuery.execConstruct(this, selector);
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public int getFloorCount() {
        return floorCount;
    }

    public void setFloorCount(int floorCount) {
        this.floorCount = floorCount;
    }
}
