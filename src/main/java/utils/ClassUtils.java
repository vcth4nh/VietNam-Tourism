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
    /**
     * Return an ArrayList of fields' name
     */
    public static final String[] omittedFields = { "selector", "" };

    public static ArrayList<String> getQueryAttr(Object obj) {
        ArrayList<String> queryAttr = new ArrayList<>();
        Class<?> class_ = obj.getClass();
        boolean haveLabel = false, haveAbstract = false;

        while (class_ != TourismObject.class.getSuperclass()) {
            Field[] allFields = class_.getDeclaredFields();
            for (Field field : allFields) {
                if (Modifier.isStatic(field.getModifiers()))
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

    public static ArrayList<String> getCurNSubClassesName(String className, boolean onlyDirect) {
        ArrayList<String> classes = getSubclassesName(className, onlyDirect);
        if (classes == null)
            return new ArrayList<String>(Collections.singleton(className));

        classes.add(className);
        return classes;
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
            if (onlyDirect && subclass.getClass().getSuperclass().equals(superClass))
                continue;

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
            Class<?> c = Class.forName(getClassPath(className));
            return (TourismObject) c.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String rmLastChar(String s) {
        return s.substring(0, s.length() - 1);
    }

    public static List<String> getCurFieldNames(String className) {
        List<String> nameList = new ArrayList<>();
        try {
            Class<?> c = Class.forName(getClassPath(className));
            Field[] fields = c.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                if (!nameList.contains(fields[i].getName()))
                    nameList.add(fields[i].getName());
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return nameList;
    }

    public static List<String> getUpperFieldNames(String className) {
        List<String> nameList = new ArrayList<>();
        try {
            Class<?> c = Class.forName(getClassPath(className));
            while (c.getSuperclass() != Object.class) {
                c = c.getSuperclass();
                nameList.addAll(getCurFieldNames(c.getSimpleName()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nameList;
    }

    public static List<String> getLowerFieldNames(String className) {
        List<String> nameList = new ArrayList<>();
        try {
            List<String> subClasses = getSubclassesName(className, false);
            if (subClasses != null && !subClasses.isEmpty()) {
                for (String subClass : subClasses) {
                    Class<?> sc = Class.forName(getClassPath(subClass));

                    Field[] sFields = sc.getDeclaredFields();
                    for (int i = 0; i < sFields.length; i++) {
                        if (!nameList.contains(sFields[i].getName()))
                            nameList.add(sFields[i].getName());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nameList;
    }

    public static List<String> getFieldNames(String className) {
        List<String> nameList = new ArrayList<>();
        nameList.addAll(getUpperFieldNames(className));
        nameList.addAll(getCurFieldNames(className));
        nameList.addAll(getLowerFieldNames(className));
        nameList.remove("selector");
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
