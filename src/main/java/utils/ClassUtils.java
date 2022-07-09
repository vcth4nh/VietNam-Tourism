package utils;

import org.reflections.Reflections;
import tourismobject.TourismObject;

import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.lang.reflect.Modifier;
import java.util.Set;

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

    public static ArrayList<String> getSubclassesName(String className, boolean onlyDirect) {
        Reflections reflections = new Reflections("tourismobject");

        Class<?> superClass;
        try {
            superClass = Class.forName(ClassUtils.getClassPath(className));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        Set<?> subclasses;
        subclasses = reflections.getSubTypesOf(superClass);

        ArrayList<String> subclasses_ = new ArrayList<>();
        for (Object subclass : subclasses) {
            if (onlyDirect && subclass.getClass().getSuperclass().equals(superClass)) continue;

            String subclassStr = subclass.toString();
            subclasses_.add(subclassStr.substring(subclassStr.lastIndexOf('.') + 1));
        }
        return subclasses_;
    }


    public static void checkExistClassPath(String dir) {
        assertTrue("File " + dir + " does not exist", Files.exists(Path.of(dir)));
    }

    public static String getClassPath(String className) {
        final String tourismObjectDir = "tourismobject";
        final String tourismObjectDirPath = "src/main/java/" + tourismObjectDir + "/";
        checkExistClassPath(tourismObjectDirPath + className + ".java");

        return tourismObjectDir + '.' + className;
    }

    public static TourismObject strToObj(String className) {
        try {
            Class<?> c = Class.forName(className);
            return (TourismObject) c.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String rmLastChar(String s) {
        return s.substring(0, s.length() - 1);
    }
}
