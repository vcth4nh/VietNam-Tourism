package old;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import utils.ClassUtils;
import utils.JsonUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Handle selector creation
 */
public class Selector {
    private final JSONObject json;
    private final String tourismObjectDir = "tourismobject";
    private final String tourismObjectDirPath = "src/main/java/" + tourismObjectDir;

    public Selector(String fileName) throws IOException, ParseException {
        json = JsonUtils.read(fileName);
    }

    public Selector() throws IOException, ParseException {
        json = JsonUtils.read("src/main/resources/tourism-object/selector.json");
    }

    public ArrayList<String> getClassName() throws ClassCastException {
        return JsonUtils.getJsonKeys(json);
    }


    public ArrayList<String> tourismObjects() {
        ClassUtils.checkExistClassPath(tourismObjectDirPath);

        ArrayList<String> tourismObjects = getClassName();
        tourismObjects.replaceAll(s -> tourismObjectDir + '.' + s);
        return tourismObjects;
    }


    public Map<String, String> objectSelector(String objectName) throws ClassCastException {
        return (Map<String, String>) json.get(objectName);
    }

}
