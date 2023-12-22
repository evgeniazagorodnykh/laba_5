package Reflection;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

public class Injector {
    /**
     * Осуществляет поиск полей, помеченных
     * этой аннотацией, и инициализацию этих полей
     * экземплярами классов
     * @param obj объект класса
     * @return ссылка на obj
     */
    public static Object inject(Object obj){
        Properties properties = new Properties();
        String pathProperties = "src/main/resources/injector.properties";

        try (FileInputStream fis = new FileInputStream(pathProperties)) {
            properties.load(fis);
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException("Error reading properties" + pathProperties, e);
        }

        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                Class<?> fieldType = field.getType();
                String implementationClassName = properties.getProperty(fieldType.getName());
                try {
                    Class<?> implementationClass = Class.forName(implementationClassName);
                    Object implementationInstance = implementationClass.getDeclaredConstructor().newInstance();
                    field.setAccessible(true);
                    field.set(obj, implementationInstance);
                } catch (Exception e) {
                    throw new RuntimeException("Error inject field: " + field.getName(), e);
                }
            }
        }
        return obj;
    }
}
