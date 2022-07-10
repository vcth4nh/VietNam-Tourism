package tourismobject;

import org.apache.jena.rdf.model.Model;
import sparql.ExecQuery;

public class WorldHeritageSite extends HistoricBuilding implements Queryable {
    protected String year;
    protected String id;

    @Override
    public Model queryModel(ExecQuery execQuery) {
        /// TODO: 7/11/2022 Xem lại query
        selector.add(SetTriple("dbr:List_of_World_Heritage_Sites_in_Vietnam", "dbo:wikiPageWikiLink", Queryable.object));
        selector.add(SetTriple(Queryable.object, "rdf:type", "dbo:WorldHeritageSite"));

        return execQuery.execConstruct(this, selector);
    }
}
