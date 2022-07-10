package tourismobject;

import org.apache.jena.rdf.model.Model;
import sparql.ExecQuery;


public interface Queryable {
    String object = "?o";
    Model queryModel(ExecQuery execQuery);
}
