package tourismobject;

import org.apache.commons.lang3.tuple.Triple;
import org.apache.jena.rdf.model.Model;
import sparql.ExecQuery;

import java.util.ArrayList;

public class MuseumHanoi extends Museum {
    @Override
    public Model queryModel(ExecQuery execQuery) {
        selector.add(SetTriple(Queryable.object, "dct:subject", "dbc:Museums_in_Hanoi"));

        return execQuery.execConstruct(this, selector);
    }
}
