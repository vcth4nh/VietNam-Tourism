package utils;

import org.apache.jena.sparql.core.Prologue;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// TODO: 7/10/2022 Rename methods
public class JsonUtils {
    /**
     * Read a JSON file and return JSONObject object
     *
     * @param fileName name of the JSON file to be read
     * @return JSONObject object of the file
     */
    public static JSONObject read(String fileName) throws IOException, ParseException {
        if (!Files.exists(Path.of(fileName)))
            fileName = "src/main/resources/" + fileName;

        try (FileReader fr = new FileReader(fileName)) {
            return (JSONObject) new JSONParser().parse(fr);
        }
    }

    /**
     * Get a set of keys of a JSONObject
     *
     * @param json a JSONObject object to get key set
     * @return ArrayList of keys
     */
    public static ArrayList<String> getJsonKeys(JSONObject json) throws ClassCastException {
        return new ArrayList<String>(json.keySet());
    }

    /**
     * Map key and value pair (String:String) of a file to a Map object
     *
     * @param fileName file needed to convert to a Map object
     * @return Map object
     */
    public static Map<String, String> JsonToMapStrStr(String fileName) throws
            ClassCastException, IOException, ParseException {
        return JsonToMapStrStr(read(fileName));
    }

    /**
     * Map key and value pair (String:String) of a JSONObject objet to a Map object
     *
     * @param json JSONObject object needed to convert to a Map object
     * @return Map object
     */
    public static Map<String, String> JsonToMapStrStr(JSONObject json) throws ClassCastException {
        Map<String, String> m = new HashMap<>();

        for (String key : getJsonKeys(json)) {
            m.put(key, (String) json.get(key));
        }

        return m;
    }

    /**
     * Read Jena's Prologue (in this case only prefixes) object from a JSON file
     *
     * @param fileName file contain Jena's Prologue
     * @return a Prologue object
     */
    public static Prologue JsonToPrologue(String fileName) throws ClassCastException, IOException, ParseException {
        return JsonToPrologue(read(fileName));
    }

    /**
     * Read Jena's Prologue (in this case only prefixes) object from a JSONObject object
     *
     * @param json JSONObject object contain Jena's Prologue
     * @return a Prologue object
     */
    public static Prologue JsonToPrologue(JSONObject json) throws ClassCastException {
        Prologue p = new Prologue();

        for (String key : getJsonKeys(json)) {
            p.setPrefix(key, (String) json.get(key));
        }

        return p;
    }

    /**
     * Get path to json file of a queried object
     *
     * @return String path to json file
     */
    public static String getJsonPath(String className) {
        return "src/main/resources/cache/" + className + ".json";
    }
}
