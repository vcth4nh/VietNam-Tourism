import org.json.simple.JSONObject;
import org.apache.jena.rdf.model.Model;
import org.json.simple.parser.ParseException;
import utils.ClassUtils;
import utils.JsonUtils;
import utils.ModelUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;

public class API4GUI {
    private final String cacheDirPath = "src/main/resources/cache/";
    private final String resultDirPath = "src/main/resources/result/";

    /**
     * Convert a data object to a JSON object, write to file in cache folder and return a JSON object
     * If file exist then read file to return a JSON object
     * @param className
     * @return JSONObject contain
     */
    public JSONObject ObjectToJson(String className) {
        ArrayList<String> fileList;
        fileList = ModelUtils.scanModel(className);

        if (fileList == null) return null;

        Model db = ModelUtils.createModel(fileList);

        String cacheFileName = className + ".json";
        String cacheFilePath = cacheDirPath + cacheFileName;

        if (!Files.exists(Path.of(cacheFilePath)))
            try {
                ModelUtils.writeModel(db, cacheFilePath, "JSONLD");
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        else System.err.println(cacheFileName + " already exist, reading from file.");

        JSONObject json;
        try {
            json = JsonUtils.read(cacheFilePath);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }

        return json;
    }

    /**
     * Get name of direct subclasses
     *
     * @param superClass
     * @return ArrayList<String> of direct subclasses
     */
    public ArrayList<String> getDirectSubclassesName(String superClass) {
        return ClassUtils.getSubclassesName(superClass, true);
    }

    /**
     * Get name of all subclasses
     *
     * @param superClass
     * @return ArrayList<String> of all subclasses
     */
    public ArrayList<String> getAllSubclassesName(String superClass) {
        return ClassUtils.getSubclassesName(superClass, false);
    }


    /**
     * Clear cache folder after exit the program
     */
    public void destroyCache() {
        try {
            Files.list(Path.of(cacheDirPath)).filter(p -> p.toString().endsWith(".json")).forEach((p) -> {
                try {
                    Files.deleteIfExists(p);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}