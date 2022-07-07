package utils;

import tourismobject.TourismObject;
import tourismobject.NationalPark;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertTrue;

public class ClassUtils {

    /**
     * Return an ArrayList of fields' name
     */
    public static ArrayList<String> getQueryAttr(Object obj) {
        ArrayList<String> queryAttr = new ArrayList<>();
        Class<?> class_ = obj.getClass();
        boolean haveLabel = false, haveAbstract = false;

        while (class_ != TourismObject.class.getSuperclass()) {
            Field[] allFields = class_.getDeclaredFields();
            for (Field field : allFields) {
                if (Modifier.isStatic(field.getModifiers())) continue;

                String fieldName = field.getName();

                if (fieldName.charAt(fieldName.length() - 1) == '_')
                    fieldName = rmLastChar(fieldName);

                if (fieldName.equals("label")) haveLabel = true;
                if (fieldName.equals("abstract")) haveAbstract = true;

                queryAttr.add(fieldName);
            }

            class_ = class_.getSuperclass();
        }
        if (!haveLabel) {
            throw new RuntimeException(class_ + " doesn't have label field");
        }

        if (!haveAbstract) {
            throw new RuntimeException(class_ + " doesn't have abstract field");
        }

        return queryAttr;
    }

    public static String rmLastChar(String s) {
        return s.substring(0, s.length() - 1);
    }

    public static void main(String[] args) {
        ArrayList<String> queryAttr = ClassUtils.getQueryAttr(new NationalPark());
        System.out.println(queryAttr);
    }
}
