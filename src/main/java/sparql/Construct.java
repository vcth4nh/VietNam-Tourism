package sparql;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.json.simple.parser.ParseException;
import utils.ClassUtils;
import utils.ModelUtils;
import utils.Selector;

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

    public Model queryOnlineAll() {
        Selector selector;
        try {
            selector = new Selector();
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        Model db = ModelFactory.createDefaultModel();
        for (String className : selector.getClassName()) {
            Map<String, String> classSelector = selector.objectSelector(className);
            System.out.println(className);
            Model m = execConstruct(ClassUtils.getClassPath(className), classSelector);
            if (m == null) continue;

            db.add(m);

            String fileName = className + ".ttl";
            String filePath = "src/main/resources/result/" + fileName;
            try {
                ModelUtils.writeModel(m, filePath, "TURTLE");
                System.out.println("Successfully wrote to " + fileName);
            } catch (IOException e) {
                System.err.println("Cannot write to " + fileName);
            }
        }
        return db;
    }

    public Model execConstruct(String className, Map<String, String> classSelector) {
        Query query;
        try {
            query = (new ConstructQuery()).create(className, classSelector);
            System.out.println(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (endpoint.startsWith("http://") || endpoint.startsWith("https://"))
            return execConstructHTTP(query, endpoint);
        else
            return execConstructFile(query, endpoint);
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
}