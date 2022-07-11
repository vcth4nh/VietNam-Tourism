package utils;

import org.apache.jena.riot.system.PrefixMap;
import org.apache.jena.sparql.core.Prologue;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// TODO: 7/10/2022 Rename methods
public class JsonUtils {
    public static JSONObject read(String fileName) throws IOException, ParseException {
        if (!Files.exists(Path.of(fileName)))
            fileName = "src/main/resources/" + fileName;

        try (FileReader fr = new FileReader(fileName)) {
            return (JSONObject) new JSONParser().parse(fr);
        }
    }

    public static ArrayList<String> getJsonKeys(JSONObject json) throws ClassCastException {
        return new ArrayList<String>(json.keySet());
    }

    public static Map<String, String> JSONToMapStrStr(JSONObject json) throws ClassCastException {
        Map<String, String> m = new HashMap<>();

        for (String key : getJsonKeys(json)) {
            m.put(key, (String) json.get(key));
        }

        return m;
    }

    public static Map<String, String> JSONToMapStrStr(String fileName) throws
            ClassCastException, IOException, ParseException {
        return JSONToMapStrStr(read(fileName));
    }

    public static Prologue JsonToPrologue(JSONObject json) throws ClassCastException {
        Prologue p = new Prologue();

        for (String key : getJsonKeys(json)) {
            p.setPrefix(key, (String) json.get(key));
        }

        return p;
    }

    public static Prologue JsonToPrologue(String fileName) throws ClassCastException, IOException, ParseException {
        return JsonToPrologue(read(fileName));

    }

    public static String getJsonPath(String className) {
        return "src/main/resources/cache/" + className + ".json";
    }
}
