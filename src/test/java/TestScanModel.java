import org.json.simple.parser.ParseException;
import tourismobject.Building;
import utils.ModelUtils;
import utils.Selector;

import java.io.IOException;

public class TestScanModel {
    public static void main(String[] args) {
        try {
            ModelUtils.scanModel((new Selector()).getClassPath("Building"));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
