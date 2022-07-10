package tourismobject;

import org.apache.jena.rdf.model.Model;
import sparql.ExecQuery;

public class ArchaeologicalSite extends HistoricBuilding implements Queryable {
    @Override
    public Model queryModel(ExecQuery execQuery) {
        selector.add(SetTriple(Queryable.object, "dct:subject", "dbc:Archaeological_sites_in_Vietnam"));

        return execQuery.execConstruct(this, selector);
    }
}
