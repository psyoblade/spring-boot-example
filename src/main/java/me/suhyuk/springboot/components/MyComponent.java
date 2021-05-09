package me.suhyuk.springboot.components;

import org.springframework.stereotype.Component;

@Component
public class MyComponent {
    private static final String COMPONENT_NAME = "MY_COMPONENT";

    public MyComponent() {

    }

    public void printComponentName() {
        System.out.println("My Component: " + COMPONENT_NAME);
    }
}
