package utils;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ModelUtils {
    public static Model createModel(String file) {
        Model model = ModelFactory.createDefaultModel();
        try {
            model.read(file, "TURTLE");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
        return model;
    }

    public static Model createModel(ArrayList<String> fileList) {
        Model db = ModelFactory.createDefaultModel();
        for (String file : fileList) {
            Model m = ModelUtils.createModel(file);
            if (m == null) continue;

            db.add(m);
        }
        return db;
    }

    public static ArrayList<String> scanModel(String className) {
        ArrayList<String> subclasses_ = ClassUtils.getSubclassesName(className, false);
        if (subclasses_ == null) return null;

        subclasses_.replaceAll(s -> "src/main/resources/result/" + s + ".ttl");
        return subclasses_;
    }

    public static void writeModel(Model model, String filePath, String type) throws IOException {
        FileWriter myWriter = new FileWriter(filePath);
        model.write(myWriter, type);
        myWriter.close();
    }
}
