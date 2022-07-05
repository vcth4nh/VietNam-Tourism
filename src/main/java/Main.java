import org.apache.jena.rdf.model.Model;
import sparql.ConstructModelOnline;

public class Main {
    public static void main(String[] args) {
        ConstructModelOnline cmo = new ConstructModelOnline();
        Model m = cmo.ExecConstruct("country");

    }
}
