package Reflection;

public class SomeBean{
    @AutoInjectable
    private MyInterface field1;
    @AutoInjectable
    private MyOtherInterface field2;
    public void foo(){
        field1.doSomething();
        field2.doSomething();
    }
}
