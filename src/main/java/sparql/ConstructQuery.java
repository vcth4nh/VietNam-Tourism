package sparql;

import org.apache.jena.arq.querybuilder.ConstructBuilder;
import org.apache.jena.query.Query;
import org.apache.jena.sparql.lang.sparql_11.ParseException;
import utils.ClassUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class ConstructQuery extends QueryModel {
    public Query create(String className, Map<String, String> classSelector) throws Exception {
        String object = "?l";

        ConstructBuilder builder = new ConstructBuilder().addPrefixes(urlPrefix.getPrefixMapping());

        Class<?> c = Class.forName(className);
        Object obj = c.getDeclaredConstructor().newInstance();

        builder = addSelector(builder, object, classSelector);
        ArrayList<String> queryAttr = ClassUtils.getQueryAttr(obj);

        builder = addConstruct(builder, object, queryAttr);
        builder = addOptional(builder, object, queryAttr);
        builder = addLangFilter(builder, "label", new ArrayList<>(Arrays.asList("en", "vi")));
        builder = addLangFilter(builder, "abstract", new ArrayList<>(Arrays.asList("en", "vi")));

        return builder.build();
    }

    public ConstructBuilder addSelector(ConstructBuilder builder, String object, Map<String, String> selector) {
        for (Map.Entry<String, String> entry : selector.entrySet()) {
            String predicate = getPredicate(entry.getKey());
            String subject = entry.getValue();
            if (predicate.charAt(0) == '_')
                builder.addWhere(subject, predicate, object);
            else
                builder.addWhere(object, predicate, subject);
        }
        return builder;
    }


    public ConstructBuilder addConstruct(ConstructBuilder builder, String object, ArrayList<String> subjects) {
        return addOptCon(builder, object, subjects, false);
    }

    public ConstructBuilder addOptional(ConstructBuilder builder, String object, ArrayList<String> wheres) {
        return addOptCon(builder, object, wheres, true);
    }

    private ConstructBuilder addOptCon(ConstructBuilder builder, String object, ArrayList<String> list, boolean isOptional) {
        for (String item : list) {
            String predicate = getPredicate(item);
            String subject = getSubject(item);

            if (isOptional)
                builder.addOptional(object, predicate, subject);
            else builder.addConstruct(object, predicate, subject);
        }
        return builder;
    }

    public ConstructBuilder addLangFilter(ConstructBuilder builder, String var, ArrayList<String> langs) throws ParseException {
        ArrayList<String> langFilterList = new ArrayList<>();
        for (String lang : langs) {
            langFilterList.add("lang(" + getSubject(var) + ") = '" + lang + "'");
        }

        String langFilter = String.join(" || ", langFilterList);
        builder.addFilter(langFilter);

        return builder;
    }
}
