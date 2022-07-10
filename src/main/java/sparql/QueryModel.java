package sparql;

import org.apache.jena.sparql.core.Prologue;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import utils.JsonUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class QueryModel {
    protected final Prologue urlPrefix;
    protected final Map<String, String> abbrPrefix;

    public QueryModel() {
        try {
            urlPrefix = JsonUtils.JsonToPrologue("url-prefix.json");
            // TODO: 7/7/2022 Lặp key?
            abbrPrefix = JsonUtils.JSONToMapStrStr("abbr-prefix.json");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected String getPredicate(String s) {
        for (Map.Entry<String, String> entry : abbrPrefix.entrySet()) {
            if (entry.getValue().contains('|' + s + '|')) return entry.getKey() + ':' + s;
        }
        throw new RuntimeException(s + " is not found in abbr-prefix.json");
    }

    protected String getSubject(String s) {
        return "?" + s;
    }
}
