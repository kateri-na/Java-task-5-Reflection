import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException,
            InstantiationException, IllegalAccessException {
        SomeBean sb = (new Injector<SomeBean>("src/main/resources/realization.properties").inject(new SomeBean()));
        sb.foo();
    }
}
