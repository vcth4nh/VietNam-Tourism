package tourismobject;

import org.apache.jena.rdf.model.Model;
import sparql.ExecQuery;

public class Airport extends Building implements Queryable {
    protected String iata;
    protected String icao;

    @Override
    public Model queryModel(ExecQuery execQuery) {
        selector.add(SetTriple(Queryable.object, "dct:subject", "dbc:Airports_in_Vietnam"));

        return execQuery.execConstruct(this, selector);
    }
}
