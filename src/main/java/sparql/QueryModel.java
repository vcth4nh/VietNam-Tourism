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
    // url prefix
    protected final Prologue urlPrefix;

    // prefix for attributes
    protected final Map<String, String> abbrPrefix;

    // initialize prefix for sparql queries
    public QueryModel() {
        try {
            urlPrefix = JsonUtils.JsonToPrologue("url-prefix.json");
            abbrPrefix = JsonUtils.JSONToMapStrStr("abbr-prefix.json");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // get predicate for a category
    // e.g. param = "location" -> return "$prefix:location" if the location's prefix exists in abbrPrefix
    protected String getPredicate(String s) {
        for (Map.Entry<String, String> entry : abbrPrefix.entrySet()) {
            if (entry.getValue().contains('|' + s + '|'))
                return entry.getKey() + ':' + s;
        }
        throw new RuntimeException(s + " is not found in abbr-prefix.json");
    }

    // get subject
    protected String getSubject(String s) {
        return "?" + s;
    }
}
