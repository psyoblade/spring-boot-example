package me.suhyuk.springboot.services;

public class MyService {

    private static final String SERVICE_NAME = "MY_SERVICE";

    public MyService(){
    }

    public void printServiceName(){
        System.out.println("My Service: " + SERVICE_NAME);
    }
}