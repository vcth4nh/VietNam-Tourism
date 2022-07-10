package tourismobject;

import org.apache.jena.rdf.model.Model;
import sparql.ExecQuery;

public class HotelHCM extends Hotel implements Queryable{
    @Override
    public Model queryModel(ExecQuery execQuery) {
        selector.add(SetTriple(Queryable.object, "dct:subject", "dbc:Hotels_in_Ho_Chi_Minh_City"));

        return execQuery.execConstruct(this, selector);
    }
}
