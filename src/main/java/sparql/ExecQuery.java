package sparql;

import org.apache.commons.lang3.tuple.Triple;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import tourismobject.Queryable;
import tourismobject.TourismObject;
import utils.ClassUtils;
import utils.ModelUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/*
* Execute sparql query
 */

public class ExecQuery {

    private final String endpoint;

    // initialize database source
    public ExecQuery(String endpoint) {
        this.endpoint = endpoint;
    }

    // initialize default database source
    public ExecQuery() {
        this("https://live.dbpedia.org/sparql/");
    }

    /*
    Query all  tourism object
     */
    public Model queryOnlineAll() {
        // initialize Jena Model for queries
        Model db = ModelFactory.createDefaultModel();
        for (String className : Objects.requireNonNull(ClassUtils.getSubclassesName("Queryable", false))) {
            Queryable object = (Queryable) ClassUtils.strToObj(className);
            if (object == null) continue;

            Model m = object.queryModel(this);
            if (m == null) continue;

            String fileName = className + ".ttl";
            String filePath = "src/main/resources/result/" + fileName;
            if (m.size() > 0)
                try {
                    ModelUtils.writeModel(m, filePath, "TURTLE");
                    System.out.println("Successfully wrote to " + fileName);
                } catch (IOException e) {
                    System.err.println("Cannot write to " + fileName);
                }

            db.add(m);
        }
        return db;
    }

    // Execute query with tourism object and selector as parameters
    // @param tourismObject, selector
    // @return query's result
    public Model execConstruct(TourismObject tObj, ArrayList<Triple<String, String, String>> selector) {
        ArrayList<String> queryAttr = ClassUtils.getQueryAttr(tObj);
        return execConstructReal(selector, queryAttr);
    }

    // Execute query with selector and query's attributes as parameters
    // @param selector, queryAttributes
    // @return query's result
    public Model execConstruct(ArrayList<Triple<String, String, String>> selector, ArrayList<String> queryAttr) {
        return execConstructReal(selector, queryAttr);
    }

    // Execute Query
    private Model execConstructReal(ArrayList<Triple<String, String, String>> selector, ArrayList<String> queryAttr) {
        Query query;
        try {
            query = (new CreateQuery()).create(selector, queryAttr);
            System.out.println(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // if endpoint(data source) is a http(s) url, execute with execConstructHTTP method
        // otherwise execute it with execConstructFile method
        if (endpoint.startsWith("http://") || endpoint.startsWith("https://"))
            return execConstructHTTP(query, endpoint);
        else
            return execConstructFile(query, endpoint);
    }

    /* Execute query with url
    @param query, url
    @return
    */
    private Model execConstructHTTP(Query query, String URL) {
        Model m;
        try (QueryExecution qef = QueryExecution.service(URL, query)) {
            m = qef.execConstruct();
        }
        return m;
    }

    /*
    Execute query with file
     */
    private Model execConstructFile(Query query, String file) {
        Model db = ModelUtils.createModel(file);
        if (db == null) return null;

        return execConstructFileReal(query, db);
    }

    private Model execConstructFileReal(Query query, Model db) {
        Model m;

        try (QueryExecution qef = QueryExecution.create(query, db)) {
            m = qef.execConstruct();
        }

        return m;
    }
}