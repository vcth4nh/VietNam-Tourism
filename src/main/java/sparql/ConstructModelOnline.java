package sparql;

import org.apache.jena.arq.querybuilder.ConstructBuilder;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.sparql.lang.sparql_11.ParseException;
import tourismobject.park.NationalPark;
import utils.GetClassInfo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ConstructModelOnline extends QueryModel {
    public Model ExecConstruct() {
        Query query;
        try {
            query = createQuery();
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return null;
        }
        Model m;
        String URL = "http://dbpedia.org/sparql";
        try (QueryExecution qef = QueryExecution.service(URL, query)) {
            m = qef.execConstruct();
        }

        return m;
    }

    public Query createQuery() throws Exception {
        String object = "?l";

        ConstructBuilder builder = new ConstructBuilder().addPrefixes(urlPrefix.getPrefixMapping())
                .addWhere(object, "rdf:type", "yago:WikicatNationalParksOfVietnam");

        ArrayList<String> queryAttr = GetClassInfo.getQueryAttr(new NationalPark());

        builder = addConstruct(builder, object, queryAttr);
        builder = addOptional(builder, object, queryAttr);
        builder = addLangFilter(builder, "?label", new ArrayList<>(Arrays.asList("en", "vi")));
        builder = addLangFilter(builder, "abstract", new ArrayList<>(Arrays.asList("en", "vi")));

        Query query = builder.build();
        System.out.println(query);
        return query;
    }

    public ConstructBuilder addConstruct(ConstructBuilder builder, String object, ArrayList<String> subjects) {
        for (String condition : subjects) {
            String predicate = getPredicate(condition);
            String subject = getSubject(condition);
            builder.addConstruct(object, predicate, subject);
        }
        return builder;
    }

    public ConstructBuilder addOptional(ConstructBuilder builder, String object, ArrayList<String> wheres) {
        for (String condition : wheres) {
            String predicate = getPredicate(condition);
            String subject = getSubject(condition);
            if (condition.charAt(0) == '_')
                builder.addOptional(subject, predicate, object);
            else
                builder.addOptional(object, predicate, subject);
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

//        try {
//            builder.addFilter(langFilter);
//        } catch (ParseException e) {
//            System.out.println("Error at filter: `" + langFilter + "`");
//            throw new RuntimeException(e);
//        }
        return builder;
    }


    public static void main(String[] args) {
        ConstructModelOnline data = new ConstructModelOnline();
        try {
            Model m = data.ExecConstruct();
            FileWriter myWriter = new FileWriter("result.txt");
            m.write(myWriter, "TURTLE");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
}
