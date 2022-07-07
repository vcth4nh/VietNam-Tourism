package utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class Selector {
    private final JSONObject json;
    private final String tourismObjectPath = "src/main/java/";
    private final String tourismObjectDir = "tourismobject";

    public Selector(String fileName) throws IOException, ParseException {
        json = (JSONObject) new JSONParser().parse(new FileReader(fileName));
    }

    public Selector() throws IOException, ParseException {
        json = (JSONObject) new JSONParser().parse(new FileReader("src/main/resources/tourism-object/selector.json"));
    }

    public ArrayList<String> getJsonKeys() {
        return new ArrayList<String>(json.keySet());
    }

    private void checkExistClassPath() {
        String dir = tourismObjectPath + tourismObjectDir;
        assertTrue("File " + dir + " does not exist", Files.exists(Path.of(dir)));
    }

    public String getClassPath(String className) {
        checkExistClassPath();

        return tourismObjectDir + '.' + className;
    }

    ;

    public ArrayList<String> tourismObjects() {
        checkExistClassPath();

        ArrayList<String> tourismObjects = getJsonKeys();
        tourismObjects.replaceAll(s -> tourismObjectDir + '.' + s);
        return tourismObjects;
    }


    public Map<String, String> objectSelector(String objectName) {
        return (Map<String, String>) json.get(objectName);
    }

}
