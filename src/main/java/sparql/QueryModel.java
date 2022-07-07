package sparql;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.sparql.core.Prologue;

import java.util.HashMap;

public class QueryModel {
    protected final Prologue urlPrefix = new Prologue();
    protected final HashMap<String, String> abbrPrefix = new HashMap<>();

    public QueryModel() {
        urlPrefix.setPrefix("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
        urlPrefix.setPrefix("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
        urlPrefix.setPrefix("dbr", "http://dbpedia.org/resource/");
        urlPrefix.setPrefix("dbo", "http://dbpedia.org/ontology/");
        urlPrefix.setPrefix("dbp", "http://dbpedia.org/property/");
        urlPrefix.setPrefix("geo", "http://www.w3.org/2003/01/geo/wgs84_pos#");
        urlPrefix.setPrefix("gold", "http://purl.org/linguistics/gold/");
        urlPrefix.setPrefix("yago", "http://dbpedia.org/class/yago/");

        abbrPrefix.put("label", "rdfs");
        abbrPrefix.put("abstract", "dbo");
        abbrPrefix.put("type", "rdf");
        abbrPrefix.put("lat", "geo");
        abbrPrefix.put("long", "geo");
        abbrPrefix.put("hypernym", "gold");
        abbrPrefix.put("testttttt", "gold");

    }

    protected String getPredicate(String s) {
        return abbrPrefix.get(s) + ':' + s;
    }

    protected String getSubject(String s) {
        return "?" + s;
    }
}
