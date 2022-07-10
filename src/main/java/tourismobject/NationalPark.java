package tourismobject;

import org.apache.commons.lang3.tuple.Triple;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.sparql.core.TriplePath;
import sparql.ExecQuery;

import java.util.ArrayList;


public class NationalPark extends Park implements Queryable {
    private String nearestCity;
    private String areaTotal;
    private String area;

    @Override
    public Model queryModel(ExecQuery execQuery) {
        selector.add(SetTriple(Queryable.object, "rdf:type", "yago:WikicatNationalParksOfVietnam"));

        return execQuery.execConstruct(this, selector);
    }
}
