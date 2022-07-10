import static org.junit.Assert.assertNotEquals;

import org.apache.jena.rdf.model.Model;
import org.json.simple.JSONObject;

import sparql.ExecQuery;
import utils.API4GUI;

import java.io.IOException;
import java.util.Scanner;

import static org.junit.Assert.*;

public class Main {
    public static void main(String[] args) {
        ExecQuery queryOnline = new ExecQuery();
        // Model db = queryOnline.queryOnlineAll();
        // assertNotEquals("Empty database", 0, db.size());
//
       API4GUI api4GUI = new API4GUI();
//
//        JSONObject object = API4GUI.ObjectToJson("Park");
////        Test API
//        Scanner sc = new Scanner(System.in);
//        System.out.println("TourismObject: ");
//        System.out.println(api4GUI.getDirectSubclassesName("TourismObject"));
//        System.out.println(API4GUI.ObjectToJson("TourismObject"));
//        System.out.println("Press enter");
//        sc.nextLine();
//
//
//        System.out.println("Beach: ");
//        System.out.println(api4GUI.getDirectSubclassesName("Beach"));
//        System.out.println(API4GUI.ObjectToJson("Beach"));
//        System.out.println("Press enter");
//        sc.nextLine();
//
//        System.out.println("Delete cache file");
       api4GUI.destroyCache();
    }

    // ----------------------------------
    // GUI code goes here
    // ----------------------------------

}
