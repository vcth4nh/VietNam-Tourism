package tourismobject;

import org.apache.jena.rdf.model.Model;
import sparql.ExecQuery;

public class Sport extends Event implements Queryable {
    protected String stadium;

    @Override
    public Model queryModel(ExecQuery execQuery) {
        selector.add(SetTriple(Queryable.object, "dct:subject", "dbc:Forts_in_Vietnam"));

        return execQuery.execConstruct(this, selector);
    }
}
