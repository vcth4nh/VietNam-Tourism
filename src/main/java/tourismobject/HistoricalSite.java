package tourismobject;

import org.apache.jena.rdf.model.Model;
import sparql.ExecQuery;

public class HistoricalSite extends HistoricBuilding implements Queryable{
    @Override
    public Model queryModel(ExecQuery execQuery) {
        selector.add(SetTriple(Queryable.object, "dct:subject", "dbc:Historical_sites_in_Hanoi"));

        return execQuery.execConstruct(this, selector);
    }
}
