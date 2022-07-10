package tourismobject;

import org.apache.commons.lang3.tuple.Triple;
import org.apache.jena.rdf.model.Model;
import sparql.ExecQuery;

import java.util.ArrayList;

public class BuddhistTemple extends ReligiousBuilding implements Queryable{
    @Override
    public Model queryModel(ExecQuery execQuery) {
        selector.add(SetTriple(Queryable.object, "dct:subject", "dbc:Buddhist_temples_in_Vietnam"));

        return execQuery.execConstruct(this, selector);
    }
}
