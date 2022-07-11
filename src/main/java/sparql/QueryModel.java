package sparql;

import org.apache.jena.sparql.core.Prologue;
import utils.JsonUtils;

import java.util.Map;

public class QueryModel {
    protected final Prologue urlPrefix;
    protected final Map<String, String> abbrPrefix;

    /**
     * Initialize prefixes for sparql queries
     */
    public QueryModel() {
        try {
            urlPrefix = JsonUtils.JsonToPrologue("url-prefix.json");
            abbrPrefix = JsonUtils.JsonToMapStrStr("abbr-prefix.json");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get predicate for a category if missing
     * E.g. param = "location" -> return "$prefix:location" if the location's prefix exists in abbrPrefix
     *
     * @param s category that needed to add prefix
     * @return category with prefix
     */
    public String getPredicate(String s) {
        if (s.contains(":")) return s;

        for (Map.Entry<String, String> entry : abbrPrefix.entrySet()) {
            if (entry.getValue().contains('|' + s + '|'))
                return entry.getKey() + ':' + s;
        }
        throw new RuntimeException(s + " is not found in abbr-prefix.json");
    }

    public String getVar(String s) {
        return "?" + s;
    }
}
