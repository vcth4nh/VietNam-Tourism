package tourismobject;

import org.apache.jena.rdf.model.Model;
import sparql.ExecQuery;

public class Tomb extends HistoricBuilding implements Queryable {
    @Override
    public Model queryModel(ExecQuery execQuery) {
        selector.add(SetTriple(Queryable.object, "dct:subject", "dbc:Tombs_in_Vietnam"));

        return execQuery.execConstruct(this, selector);
    }
}
