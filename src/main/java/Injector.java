import java.io.*;
import java.lang.reflect.Field;
import java.text.Annotation;
import java.util.Properties;

public class Injector<T> {
    private Properties properties;
    Injector(String filePath) throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream(new File(filePath)));
    }

    T inject(T object) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class dependency;
        Class gettenClass = object.getClass();
        Field[] fields = gettenClass.getDeclaredFields();
        for(Field field: fields){
            if(field.isAnnotationPresent(AutoInjectable.class)){
                Class fieldType = field.getType();
                String interfaceName = fieldType.getName();
                String implementationClassName = properties.getProperty(interfaceName);
                if (implementationClassName != null) {
                    dependency = Class.forName(implementationClassName);
                    field.setAccessible(true);
                    field.set(object, dependency.newInstance());
                }
            }
        }
        return object;
    }
}
