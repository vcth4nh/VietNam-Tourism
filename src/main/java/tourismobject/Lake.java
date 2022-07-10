package tourismobject;

import org.apache.jena.rdf.model.Model;
import sparql.ExecQuery;

public class Lake extends Natural implements Queryable {
    private String location;
    private String type;

    @Override
    public Model queryModel(ExecQuery execQuery) {
        selector.add(SetTriple(Queryable.object, "dct:subject", "dbc:Lakes_of_Vietnam"));

        return execQuery.execConstruct(this, selector);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}