package sparql;

import org.apache.commons.lang3.tuple.Triple;
import org.apache.jena.arq.querybuilder.ConstructBuilder;
import org.apache.jena.query.Query;
import org.apache.jena.sparql.core.TriplePath;
import org.apache.jena.sparql.lang.sparql_11.ParseException;
import tourismobject.Queryable;
import tourismobject.TourismObject;
import utils.ClassUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class CreateQuery extends QueryModel {
    public Query create(ArrayList<Triple<String, String, String>> selector, ArrayList<String> queryAttr) throws Exception {
        return createReal(selector, queryAttr);
    }

    public Query create(TourismObject tObj, ArrayList<Triple<String, String, String>> selector) throws Exception {
        ArrayList<String> queryAttr = ClassUtils.getQueryAttr(tObj);
        return createReal(selector, queryAttr);
    }

    private Query createReal(ArrayList<Triple<String, String, String>> selector, ArrayList<String> queryAttr) throws Exception {
        String object = Queryable.object;

        ConstructBuilder builder = new ConstructBuilder().addPrefixes(urlPrefix.getPrefixMapping());
        builder = addSelector(builder, selector);

        builder = addConstruct(builder, object, queryAttr);
        builder = addOptional(builder, object, queryAttr);
        builder = addLangFilter(builder, "label", new ArrayList<>(Arrays.asList("en", "vi")));
        builder = addLangFilter(builder, "abstract", new ArrayList<>(Arrays.asList("en", "vi")));

        return builder.build();
    }

    public ConstructBuilder addSelector(ConstructBuilder builder, ArrayList<Triple<String, String, String>> selector) {
        for (Triple<String, String, String> entry : selector) {
            builder.addWhere(entry.getLeft(), entry.getMiddle(), entry.getRight());
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
            if ((subject + predicate + object).contains("null")) continue; // TODO: 7/10/2022 Dùng tạm để test
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
