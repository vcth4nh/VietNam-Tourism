package sparql;

import org.apache.jena.arq.querybuilder.ConstructBuilder;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.rdf.model.Model;
import utils.ModelUtils;
import utils.Selector;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class Construct {

    private final String endpoint;

    public Construct(String endpoint) {
        this.endpoint = endpoint;
    }

    public Construct() {
        endpoint = "http://dbpedia.org/sparql";
    }

    public void queryOnlineAll() {
        Selector selector;
        try {
            selector = new Selector();
        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
            return;
        }

        for (String className : selector.getJsonKeys()) {
            try {
                Map<String, String> classSelector = selector.objectSelector(className);
                Model m = execConstruct(selector.getClassPath(className), classSelector);
                FileWriter myWriter = new FileWriter("result.txt");
                m.write(myWriter, "TURTLE");
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
            }
        }

    }

    public Model execConstruct(String className, Map<String, String> classSelector) {
        Query query;
        try {
            query = (new ConstructQuery()).create(className, classSelector);
            System.out.println(query);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        if (endpoint.startsWith("http://") || endpoint.startsWith("https://"))
            return execConstructHTTP(query, endpoint);
        else return execConstructFile(query, endpoint);
    }


    private Model execConstructHTTP(Query query, String URL) {
        Model m;
        try (QueryExecution qef = QueryExecution.service(URL, query)) {
            m = qef.execConstruct();
        }
        return m;
    }

    private Model execConstructFile(Query query, String file) {
        Model db = ModelUtils.createModel(file);
        if (db == null) return null;

        return execConstructFileReal(query, db);

    }

    private Model execConstructFile(Query query, Model m) {
        return execConstructFileReal(query, m);
    }

    private Model execConstructFileReal(Query query, Model db) {
        Model m;

        try (QueryExecution qef = QueryExecution.create(query, db)) {
            m = qef.execConstruct();
        }

        return m;
    }


    public static void main(String[] args) {
    }
}
