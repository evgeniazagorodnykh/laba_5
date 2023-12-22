package Reflection;

import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Field;

public class InjectorTest {

    @org.junit.Test
    public void injectTest() throws NoSuchFieldException, IllegalAccessException {
        SomeBean temp = new SomeBean();
        Injector.inject(temp);

        Field field1 = SomeBean.class.getDeclaredField("field1");
        field1.setAccessible(true);
        assertSame(field1.get(temp).getClass(), MyImpl.class);
        field1.setAccessible(false);

        Field field2 = SomeBean.class.getDeclaredField("field2");
        field2.setAccessible(true);
        assertSame(field2.get(temp).getClass(), SODoer.class);
        field2.setAccessible(false);
    }
}