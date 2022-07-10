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

    public TourismObject(String label, String abstract_) {
        this.label = label;
        this.abstract_ = abstract_;
    }

    public TourismObject() {
    }

    protected Triple<String, String, String> SetTriple(String l, String m, String r) {
        MutableTriple<String, String, String> t = new MutableTriple<>();
        t.setLeft(l);
        t.setMiddle(m);
        t.setRight(r);
        return t;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getAbstract_() {
        return abstract_;
    }

    public void setAbstract_(String abstract_) {
        this.abstract_ = abstract_;
    }

}
