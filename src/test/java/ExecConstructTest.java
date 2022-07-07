import org.apache.jena.rdf.model.Model;
import org.json.simple.parser.ParseException;
import sparql.Construct;
import utils.Selector;

import java.io.FileWriter;
import java.io.IOException;

public class ExecConstructTest {
    public static void main(String[] args) {
        Selector selector;
        try {
            selector = new Selector();
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);

        }

        Construct data = new Construct();
        data.queryOnlineAll();
        try {
            Model m = data.execConstruct(selector.getClassPath("NationalPark"), selector.objectSelector("NationalPark"));
//            Model m = ModelUtils.createModel("result.txt");
            FileWriter myWriter = new FileWriter("result3.txt");
            m.write(myWriter, "TURTLE");
            m.write(System.out, "TURTLE");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
}
