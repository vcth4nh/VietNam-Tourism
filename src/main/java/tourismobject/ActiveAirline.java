package tourismobject;

import org.apache.jena.rdf.model.Model;
import sparql.ExecQuery;

public class ActiveAirline extends Airline implements Queryable {
    protected String founded;
    protected String openingDate;

    @Override
    public Model queryModel(ExecQuery execQuery) {
        selector.add(SetTriple(Queryable.object, "dct:subject", "dbc:Airlines_of_Vietnam"));

        return execQuery.execConstruct(this, selector);
    }
}
