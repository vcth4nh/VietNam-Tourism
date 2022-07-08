package utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class JsonUtils {
    public static JSONObject read(String fileName) throws IOException, ParseException {
        return (JSONObject) new JSONParser().parse(new FileReader(fileName));
    }

    public static ArrayList<String> getJsonKeys(JSONObject json) throws ClassCastException {
        return new ArrayList<String>(json.keySet());
    }

    public static String getJsonPath(String className) {
        return "src/main/resources/cache/" + className + ".json";
    }
}
