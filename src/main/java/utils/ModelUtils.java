package utils;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

public class ModelUtils {
    public static Model createModel(String file) {
        Model model = ModelFactory.createDefaultModel();
        model.read(file, "TURTLE");
        return model;
    }
}
