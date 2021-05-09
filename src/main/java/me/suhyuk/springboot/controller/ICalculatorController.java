package me.suhyuk.springboot.controller;

public interface ICalculatorController {
    public String add(String x, String y) throws InterruptedException;
    public String subtract(String x, String y);
    public String multiply(String x, String y);
    public String divide(String x, String y);
}
