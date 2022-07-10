package sparql;

import org.apache.commons.lang3.tuple.Triple;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.sparql.core.TriplePath;
import org.json.simple.parser.ParseException;
import tourismobject.Queryable;
import tourismobject.TourismObject;
import utils.ClassUtils;
import utils.ModelUtils;
import utils.Selector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class ExecQuery {

    private final String endpoint;

    public ExecQuery(String endpoint) {
        this.endpoint = endpoint;
    }

    public ExecQuery() {
        endpoint = "http://dbpedia.org/sparql";
    }

    public Model queryOnlineAll() {
        Selector selector;
//        try {
//            selector = new Selector();
//        } catch (IOException | ParseException e) {
//            throw new RuntimeException(e);
//        }

        Model db = ModelFactory.createDefaultModel();
        for (String className : Objects.requireNonNull(ClassUtils.getSubclassesName("Queryable", false))) {
            Queryable object = (Queryable) ClassUtils.strToObj(className);
            if (object == null) continue;
            Model m = object.queryModel(this);
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

    public Model execConstruct(TourismObject tObj, ArrayList<Triple<String, String, String>> selector) {
        ArrayList<String> queryAttr = ClassUtils.getQueryAttr(tObj);
        return execConstructReal(selector, queryAttr);
    }

    public Model execConstruct(ArrayList<Triple<String, String, String>> selector, ArrayList<String> queryAttr) {
        return execConstructReal(selector, queryAttr);
    }

    private Model execConstructReal(ArrayList<Triple<String, String, String>> selector, ArrayList<String> queryAttr) {
        Query query;
        try {
            query = (new CreateQuery()).create(selector, queryAttr);
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