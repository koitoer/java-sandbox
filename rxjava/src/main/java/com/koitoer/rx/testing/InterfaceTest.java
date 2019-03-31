package com.koitoer.rx.testing;

/**
 * Created by mmena on 6/25/18.
 */
public class InterfaceTest extends Inter implements In {

    public static void main(String[] args) {
        InterfaceTest interfaceTest = new InterfaceTest();
        interfaceTest.show();
    }
}

interface In {

    default void show() {
        System.out.println("Show from interface");
    }
}

class Inter {

    public void show() {
        System.out.println("Show from parent");
    }
}
