package org.example;

import Reflection.Injector;
import Reflection.SomeBean;

public class Main {
    public static void main(String[] args) {
        ((SomeBean) (Injector.inject(new SomeBean()))).foo();
    }
}