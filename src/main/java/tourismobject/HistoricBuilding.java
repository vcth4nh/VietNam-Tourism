package tourismobject;

import org.apache.jena.rdf.model.Model;
import sparql.ExecQuery;

public class HistoricBuilding extends Building implements Queryable {

    @Override
    public Model queryModel(ExecQuery execQuery) {
        selector.add(SetTriple(Queryable.object, "rdf:type", "dbo:HistoricBuilding"));
        selector.add(SetTriple(Queryable.object, "dbp:country", "dbr:Vietnam"));

        return execQuery.execConstruct(this, selector);
    }
}
