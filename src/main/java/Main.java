import org.apache.jena.rdf.model.Model;
import sparql.ConstructModelOnline;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class Main {
    public static void main(String[] args) {
        ConstructModelOnline cmo = new ConstructModelOnline();
        Model m = cmo.ExecConstruct();
        assertNotNull("Failed to query data", m);
    }
}
