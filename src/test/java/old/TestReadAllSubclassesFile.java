package old;

import org.apache.jena.rdf.model.Model;
import org.json.simple.parser.ParseException;
import utils.ClassUtils;
import utils.ModelUtils;
import utils.Selector;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;


public class TestReadAllSubclassesFile {
    public static void main(String[] args) {
        ArrayList<String> fileList;
            fileList = ModelUtils.scanModel(ClassUtils.getClassPath("Building"));
        assertNotNull("Empty file list", fileList);
        Model db = ModelUtils.createModel(fileList);
        db.write(System.out, "JSONLD");
    }
}
