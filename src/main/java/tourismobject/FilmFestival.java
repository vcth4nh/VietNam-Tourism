package tourismobject;

import org.apache.jena.rdf.model.Model;
import sparql.ExecQuery;

public class FilmFestival extends Event implements Queryable {
    protected String date;
    protected String host;
    protected String wikiPageExternalLink;

    @Override
    public Model queryModel(ExecQuery execQuery) {
        selector.add(SetTriple(Queryable.object, "dct:subject", "dbc:Vietnam_Film_Festival"));

        return execQuery.execConstruct(this, selector);
    }
}
