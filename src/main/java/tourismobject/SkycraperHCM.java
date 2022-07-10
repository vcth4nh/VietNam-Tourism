package tourismobject;

import org.apache.jena.rdf.model.Model;
import sparql.ExecQuery;

public class SkycraperHCM extends Skyscraper {
    @Override
    public Model queryModel(ExecQuery execQuery) {
        selector.add(SetTriple(Queryable.object, "dct:subject", "dbc:Skyscrapers_in_Ho_Chi_Minh_City"));

        return execQuery.execConstruct(this, selector);
    }
}
