package tourismobject;

import org.apache.commons.lang3.tuple.MutableTriple;
import org.apache.commons.lang3.tuple.Triple;

import org.apache.jena.rdf.model.Model;
import sparql.ExecQuery;

public abstract class TourismObject  {
    protected String label;
    protected String lat;
    protected String long_;
    protected String abstract_;

    protected Triple<String, String, String> SetTriple(String l, String m, String r) {
        MutableTriple<String, String, String> t = new MutableTriple<>();
        t.setLeft(l);
        t.setMiddle(m);
        t.setRight(r);
        return t;
    }

//    @Override
//    public Model queryModel(ExecQuery execQuery) {
//        return null;
//    }
}
