import org.apache.jena.rdf.model.Model;
import sparql.ExecQuery;

import java.io.IOException;

import static org.junit.Assert.*;

public class Main {
    public static void main(String[] args) {
        ExecQuery queryOnline = new ExecQuery();
        Model db = queryOnline.queryOnlineAll();
        assertNotEquals("Empty database", 0, db.size());

        API4GUI api4GUI = new API4GUI();


//      Test API
        System.out.println("TourismObject: ");
        System.out.println(api4GUI.getDirectSubclassesName("TourismObject"));
        System.out.println(api4GUI.ObjectToJson("TourismObject"));
        try {
            System.out.println("Press enter");
            System.in.read();
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Building: ");
        System.out.println(api4GUI.getDirectSubclassesName("Building"));
        System.out.println(api4GUI.ObjectToJson("Building"));
        try {
            System.out.println("Press enter");
            System.in.read();
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Park: ");
        System.out.println(api4GUI.getDirectSubclassesName("Park"));
        System.out.println(api4GUI.ObjectToJson("Park"));

        api4GUI.destroyCache();
    }


//        ----------------------------------
//        GUI code goes here
//        ----------------------------------

}
