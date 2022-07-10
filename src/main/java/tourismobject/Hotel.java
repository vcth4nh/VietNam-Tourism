package tourismobject;

import org.apache.jena.rdf.model.Model;
import sparql.ExecQuery;

public class Hotel extends Building implements Queryable {
    protected String homepage;
    protected String numberOfRooms;
    protected String numberOfSuites;
    protected String numberOfRestaurants;

    @Override
    public Model queryModel(ExecQuery execQuery) {
        selector.add(SetTriple(Queryable.object, "dct:subject", "dbc:Hotels_in_Vietnam"));

        return execQuery.execConstruct(this, selector);
    }
}
