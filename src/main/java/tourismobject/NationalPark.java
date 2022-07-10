package tourismobject;

//import org.apache.jena.graph.Triple;

import org.apache.commons.lang3.tuple.Triple;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.sparql.core.TriplePath;
import sparql.ExecQuery;

import java.util.ArrayList;

public class NationalPark extends Park implements Queryable {
    @Override
    public Model queryModel(ExecQuery execQuery) {
        ArrayList<Triple<String, String, String>> selector = new ArrayList<>();
        selector.add(SetTriple(Queryable.object, "rdf:type", "yago:WikicatNationalParksOfVietnam"));

        return execQuery.execConstruct(this, selector);
    }
}
