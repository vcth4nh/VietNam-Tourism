package sparql;

import org.apache.commons.lang3.tuple.Triple;
import org.apache.jena.arq.querybuilder.ConstructBuilder;
import org.apache.jena.sparql.lang.sparql_11.ParseException;
import tourismobject.Queryable;
import tourismobject.TourismObject;
import utils.ClassUtils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Create query to gather data
 */
public class CreateQuery extends Query {
    /**
     * Create query
     *
     * @param selector  ArrayList of Triple to filter result
     * @param queryAttr ArrayList of Attributes to query
     * @return Query object to start query for triples
     */
    public org.apache.jena.query.Query create(ArrayList<Triple<String, String, String>> selector, ArrayList<String> queryAttr) throws Exception {
        return createReal(selector, queryAttr);
    }

    /**
     * Create query
     *
     * @param tObj     A TourismObject object that needed to be queried
     * @param selector ArrayList of Triple to filter result
     * @return Query object to start query for triples
     */
    public org.apache.jena.query.Query create(TourismObject tObj, ArrayList<Triple<String, String, String>> selector) throws Exception {
        ArrayList<String> queryAttr = ClassUtils.getQueryAttr(tObj);
        return createReal(selector, queryAttr);
    }

    /**
     * Add selector clauses for query
     *
     * @param builder  current Builder object
     * @param selector ArrayList of Triple to filter result
     * @return builder object with added selector clauses
     */
    public ConstructBuilder addSelector(ConstructBuilder builder, ArrayList<Triple<String, String, String>> selector) {
        for (Triple<String, String, String> entry : selector) {
            builder.addWhere(entry.getLeft(), entry.getMiddle(), entry.getRight());
        }
        return builder;
    }

    /**
     * Add construct clauses for query
     *
     * @param builder  current Builder object
     * @param object   object, for example: "?a"
     * @param subjects subject to query, for example: "dbp:length" or "length"
     * @return builder object with added construct clauses
     */
    public ConstructBuilder addConstruct(ConstructBuilder builder, String object, ArrayList<String> subjects) {
        return addOptCon(builder, object, subjects, false);
    }

    /**
     * Add optional clauses for query
     *
     * @param builder current Builder object
     * @param object  object, for example: "?a"
     * @param wheres  optional attribute to query, for example: "dbp:length" or "length"
     * @return builder object with added optional clauses
     */
    public ConstructBuilder addOptional(ConstructBuilder builder, String object, ArrayList<String> wheres) {
        return addOptCon(builder, object, wheres, true);
    }


    /**
     * Add language filter
     *
     * @param builder current Builder object
     * @param var     variable that needed to add filter
     * @param langs   ArrayList of String of language that are allowed
     * @return builder object with added language filter clauses
     * @throws ParseException will be thrown if parser cannot parse filter string
     */
    public ConstructBuilder addLangFilter(ConstructBuilder builder, String var, ArrayList<String> langs) throws ParseException {
        ArrayList<String> langFilterList = new ArrayList<>();
        for (String lang : langs) {
            langFilterList.add("lang(" + getVar(var) + ") = '" + lang + "'");
        }

        String langFilter = String.join(" || ", langFilterList);
        builder.addFilter(langFilter);

        return builder;
    }

    private ConstructBuilder addOptCon(ConstructBuilder builder, String object, ArrayList<String> list, boolean isOptional) {
        for (String item : list) {
            String predicate = getPredicate(item);
            String subject = getVar(item);
            if ((subject + predicate + object).contains("null")) continue; // TODO: 7/10/2022 Dùng tạm để test
            if (isOptional)
                builder.addOptional(object, predicate, subject);
            else builder.addConstruct(object, predicate, subject);
        }
        return builder;
    }

    private org.apache.jena.query.Query createReal(ArrayList<Triple<String, String, String>> selector, ArrayList<String> queryAttr) throws Exception {
        String object = Queryable.object;

        ConstructBuilder builder = new ConstructBuilder().addPrefixes(urlPrefix.getPrefixMapping());
        builder = addSelector(builder, selector);

        builder = addConstruct(builder, object, queryAttr);
        builder = addOptional(builder, object, queryAttr);
        builder = addLangFilter(builder, "label", new ArrayList<>(Arrays.asList("en", "vi")));
        builder = addLangFilter(builder, "abstract", new ArrayList<>(Arrays.asList("en", "vi")));

        return builder.build();
    }
}
