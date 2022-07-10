package tourismobject;

import org.apache.jena.rdf.model.Model;
import sparql.ExecQuery;

public class DefunctAirline extends Airline implements Queryable {
    protected String founded;
    protected String ceased;

    @Override
    public Model queryModel(ExecQuery execQuery) {
        selector.add(SetTriple(Queryable.object, "dct:subject", "dbc:Defunct_airlines_of_Vietnam"));
        selector.add(SetTriple(Queryable.object, "rdf:type", "dbo:Airline"));

        return execQuery.execConstruct(this, selector);
    }
}
