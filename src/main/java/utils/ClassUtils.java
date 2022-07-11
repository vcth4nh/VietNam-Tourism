package utils;

import org.apache.jena.sparql.function.library.collation;
import org.reflections.Reflections;

import tourismobject.TourismObject;

import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class ClassUtils {
    public static final String[] omittedFields = {"selector", ""};

    /**
     * Get field's names of all own and inherited fields.
     *
     * @param obj Object to get all avaiable non-static fields
     * @return ArrayList of name of fields
     */
    public static ArrayList<String> getQueryAttr(Object obj) {
        ArrayList<String> queryAttr = new ArrayList<>();
        Class<?> class_ = obj.getClass();
        boolean haveLabel = false, haveAbstract = false;

        while (class_ != TourismObject.class.getSuperclass()) {
            Field[] allFields = class_.getDeclaredFields();
            for (Field field : allFields) {
                if (Modifier.isStatic(field.getModifiers()) ||
                        (Modifier.isPrivate(field.getModifiers()) && !class_.equals(obj.getClass())))
                    continue;

                String fieldName = field.getName();
                if (fieldName.equals("selector"))
                    continue;

                if (fieldName.charAt(fieldName.length() - 1) == '_')
                    fieldName = rmLastChar(fieldName);

                if (fieldName.equals("label"))
                    haveLabel = true;
                if (fieldName.equals("abstract"))
                    haveAbstract = true;

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

    /**
     * Get current class and subclasses' names
     *
     * @param className  Class's name to get current and subclasses name
     * @param onlyDirect option to get direct or all subclasses name
     * @return ArrayList of name of current and subclasses name
     */
    public static ArrayList<String> getCurNSubClassesName(String className, boolean onlyDirect) {
        ArrayList<String> classes = getSubclassesName(className, onlyDirect);
        if (classes == null)
            return new ArrayList<>(Collections.singleton(className));

        classes.add(className);
        return classes;
    }

    /**
     * Get subclasses' names
     *
     * @param className  Class's name to get subclasses name
     * @param onlyDirect option to get direct or all subclasses name
     * @return ArrayList of name of subclasses name
     */
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
            if (onlyDirect && subclass.getClass().getSuperclass().equals(superClass))
                continue;

            String subclassStr = subclass.toString();
            subclasses_.add(subclassStr.substring(subclassStr.lastIndexOf('.') + 1));
        }
        return subclasses_;
    }

    /**
     * Check existence of a Class path, exit program if not
     *
     * @param className Class's name to check existence
     */
    public static void checkExistClassPath(String className) {
        String classPath = "src/main/java/tourismobject/" + className + ".java";
        assertTrue("File " + classPath + " does not exist",
                Files.exists(Path.of(classPath)));
    }

    /**
     * Get path to a class
     *
     * @param className Class's name to get path to class
     * @return String Path to class
     */
    public static String getClassPath(String className) {
        final String tourismObjectDir = "tourismobject";
        checkExistClassPath(className);

        return tourismObjectDir + '.' + className;
    }

    /**
     * Create an object from a string
     *
     * @param className Class's name to create object
     * @return TourismObject
     */
    public static TourismObject strToObj(String className) {
        try {
            Class<?> c = Class.forName(getClassPath(className));
            return (TourismObject) c.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Remove the final character in a String
     *
     * @param s String to be processed
     * @return String
     */
    public static String rmLastChar(String s) {
        return s.substring(0, s.length() - 1);
    }

    // Get field names of Current class
    public static List<String> getCurFieldNames(String className) {
        List<String> nameList = new ArrayList<>();
        try {
            Class<?> c = Class.forName(getClassPath(className));
            Field[] fields = c.getDeclaredFields();
            for (Field field : fields) {
                if (!nameList.contains(field.getName()))
                    nameList.add(field.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nameList;
    }

    // Get field names of parents and all ancestor of the current class

    public static List<String> getUpperFieldNames(String className) {
        List<String> nameList = new ArrayList<>();
        try {
            Class<?> c = Class.forName(getClassPath(className));
            // Loop through to the very top ancestor
            while (c.getSuperclass() != Object.class) {
                c = c.getSuperclass();
                nameList.addAll(getCurFieldNames(c.getSimpleName()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nameList;
    }

    // Get field names of child and all descendent of the current class
    public static List<String> getLowerFieldNames(String className) {
        List<String> nameList = new ArrayList<>();
        try {
            List<String> subClasses = getSubclassesName(className, false);
            if (subClasses != null && !subClasses.isEmpty()) {
                for (String subClass : subClasses) {
                    getCurFieldNames(subClass);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nameList;
    }

    // Get All field names related to the current class
    public static List<String> getFieldNames(String className) {
        List<String> nameList = new ArrayList<>();
        nameList.addAll(getUpperFieldNames(className));
        nameList.addAll(getCurFieldNames(className));
        nameList.addAll(getLowerFieldNames(className));
        nameList.remove("selector");
        // Sort to make label first in the field list
        nameList.sort((o1, o2) -> {
            if (o1.equals("label"))
                return -1;
            if (o2.equals("label"))
                return 1;
            if (o1.equals("abstract_"))
                return 1;
            return o1.compareToIgnoreCase(o2);
        });
        return nameList;
    }

}
