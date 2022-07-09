package tourismobject;

import org.apache.jena.rdf.model.Model;
import sparql.Construct;

public interface Queryable {
    public Model queryModel(Construct construct);
}
