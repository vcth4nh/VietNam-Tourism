package tourismobject;

import org.apache.jena.arq.querybuilder.WhereBuilder;
import org.apache.jena.rdf.model.Model;
import sparql.Construct;

import java.util.Map;

public class NationalPark extends Park implements Queryable {
    Map<String, String> classSelector;

    @Override
    public Model queryModel(Construct construct) {
        WhereBuilder selector = new WhereBuilder();
        return construct.execConstruct(this, );
        ;
    }
}
