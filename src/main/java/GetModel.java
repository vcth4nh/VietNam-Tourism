import org.apache.jena.arq.querybuilder.ConstructBuilder;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.sparql.core.Prologue;
import org.apache.jena.sparql.lang.sparql_11.ParseException;

import java.io.FileWriter;
import java.io.IOException;

public class GetModel {
    private final String URL;
    private final String type;
    private String outputFile;

    public GetModel(String URL, String type, String outputFile) {
        this.URL = URL;
        this.type = type;
        this.outputFile = outputFile;
    }

    public GetModel(String URL, String type) {
        this.URL = URL;
        this.type = type;
    }

    public Model ExecQuery(String data) {
        Prologue prefix = new Prologue();
        prefix.setPrefix("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
        prefix.setPrefix("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
        prefix.setPrefix("dbr", "http://dbpedia.org/resource/");
        prefix.setPrefix("dbo", "http://dbpedia.org/ontology/");
        prefix.setPrefix("dbp", "http://dbpedia.org/property/");
        prefix.setPrefix("geo", "http://www.w3.org/2003/01/geo/wgs84_pos#");
        prefix.setPrefix("gold", "http://purl.org/linguistics/gold/");
        prefix.setPrefix("yago", "http://dbpedia.org/class/yago/");

        ConstructBuilder sb;
        try {
            sb = new ConstructBuilder()
                    .addPrefixes(prefix.getPrefixMapping())
                    .addConstruct("?l", "rdfs:label", "?name").addConstruct("?l", "geo:lat", "?lat")
                    .addConstruct("?l", "geo:long", "?long").addConstruct("?l", "gold:hypernym", "?hypernym")
                    .addConstruct("?l", "dbo:abstract", "?abstract")

                    .addWhere("?l", "dbp:" + data, "dbr:Vietnam")
                    .addWhere("?l", "rdf:type", "geo:SpatialThing")
                    .addWhere("?l", " rdf:type", "yago:Building102913152")

                    .addFilter("LANG (?abstract) = 'en'")
                    .addFilter("LANG (?name) = 'en'")

                    .addWhere("?l", "rdfs:label", "?name")
                    .addWhere("?l", "dbo:abstract", "?abstract")
                    .addWhere("?l", "geo:lat", "?lat")
                    .addWhere("?l", "geo:long", "?long")
                    .addWhere("?l", "gold:hypernym", "?hypernym")
            ;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Query query = sb.build();
        System.out.println(query);

        Model m;
        try (QueryExecution qef = QueryExecution.service(URL, query)) {
            m = qef.execConstruct();
            System.out.println((m));
            m.write(System.out, "TURTLE");
        }
        return m;
    }

    public static void main(String[] args) throws Exception {
        GetModel data = new GetModel("http://dbpedia.org/sparql", "country");
        try {
            Model m = data.ExecQuery("country");
            FileWriter myWriter = new FileWriter("result.txt");
            m.write(myWriter, "TURTLE");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
}
