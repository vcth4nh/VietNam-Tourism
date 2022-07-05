package utils;

import tourismobject.TourismObject;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class GetClassInfo {
    public static ArrayList<String> getInheritedFieldsName(Object obj) {
        Class<?> class_ = obj.getClass();
        ArrayList<String> allFieldsName = new ArrayList<>();

        while (class_ != TourismObject.class.getSuperclass()) {
            Field[] allFields = class_.getDeclaredFields();
            for (Field field : allFields) {
                allFieldsName.add(field.getName());
            }
            class_ = class_.getSuperclass();
        }
        return allFieldsName;
    }
}
