package tourismobject;

import org.apache.commons.lang3.tuple.MutableTriple;
import org.apache.commons.lang3.tuple.Triple;

import org.apache.jena.rdf.model.Model;
import sparql.ExecQuery;

import java.util.ArrayList;

public abstract class TourismObject {
    protected ArrayList<Triple<String, String, String>> selector = new ArrayList<>();
    protected String label;
    protected String abstract_;
    protected String nativeName;

    protected Triple<String, String, String> SetTriple(String l, String m, String r) {
        MutableTriple<String, String, String> t = new MutableTriple<>();
        t.setLeft(l);
        t.setMiddle(m);
        t.setRight(r);
        return t;
    }
}
