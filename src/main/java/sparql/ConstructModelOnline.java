package sparql;

import org.apache.jena.arq.querybuilder.ConstructBuilder;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.sparql.lang.sparql_11.ParseException;

import java.io.FileWriter;
import java.io.IOException;

public class ConstructModelOnline extends QueryModel {

    public Model ExecConstruct(String data) {
        ConstructBuilder sb;
        try {
            sb = new ConstructBuilder()
                    .addPrefixes(urlPrefix.getPrefixMapping())

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
        String URL = "http://dbpedia.org/sparql";
        try (QueryExecution qef = QueryExecution.service(URL, query)) {
            m = qef.execConstruct();
        }
        return m;
    }

    public static void main(String[] args) {
        ConstructModelOnline data = new ConstructModelOnline();
        try {
            Model m = data.ExecConstruct("country");
            FileWriter myWriter = new FileWriter("result.txt");
            m.write(myWriter, "TURTLE");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
}
