package tourismobject;

import org.apache.jena.rdf.model.Model;
import sparql.ExecQuery;

public class Mosque extends ReligiousBuilding implements Queryable {

    @Override
    public Model queryModel(ExecQuery execQuery) {
        selector.add(SetTriple(Queryable.object, "rdf:type", "dbo:ArchitecturalStructure"));
        selector.add(SetTriple(Queryable.object, "dct:subject", "dbc:Mosques_in_Vietnam"));

        return execQuery.execConstruct(this, selector);
    }
}
