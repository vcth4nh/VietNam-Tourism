package tourismobject;

import org.apache.jena.rdf.model.Model;
import sparql.ExecQuery;

public class Festival extends Event implements Queryable {
    @Override
    public Model queryModel(ExecQuery execQuery) {
        selector.add(SetTriple(Queryable.object, "dct:subject", "dbc:Festivals_in_Vietnam"));

        return execQuery.execConstruct(this, selector);
    }
}
