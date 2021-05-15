package me.suhyuk.springboot.controller;

import me.suhyuk.springboot.aspect.LogExecutionTime;
import me.suhyuk.springboot.aspect.LogRunningTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/calculator/v3")
public class CalculatorControllerV3 implements ICalculatorController {

    private static Logger logger = LoggerFactory.getLogger(CalculatorControllerV3.class);
    private int maxSleepMillis = 1000;

    private int parseInt(String s) {
        if (s.contains("."))
            return new Float(Float.parseFloat(s)).intValue();
        return Integer.parseInt(s);
    }

    private float parseFloat(String s) {
        return Float.parseFloat(s);
    }

    private void randomSleep(int bound) {
        int millis = new Random().nextInt(bound);
        try { Thread.sleep(millis); } catch (InterruptedException e) {}
    }

    @Override
    @LogExecutionTime
    @GetMapping("add")
    public String add(@RequestParam(defaultValue = "0") String x,
                      @RequestParam(defaultValue = "0") String y) {
        randomSleep(maxSleepMillis);
        return String.valueOf(parseInt(x) + parseInt(y));
    }

    @Override
    @LogRunningTime
    @GetMapping("subtract")
    public String subtract(@RequestParam(defaultValue = "0") String x,
                           @RequestParam(defaultValue = "0") String y) {
        randomSleep(maxSleepMillis);
        return String.valueOf(parseInt(x) - parseInt(y));
    }

    @Override
    @LogRunningTime
    @GetMapping("multiply")
    public String multiply(@RequestParam(defaultValue = "1") String x,
                           @RequestParam(defaultValue = "1") String y) {
        randomSleep(maxSleepMillis);
        return String.valueOf(parseInt(x) * parseInt(y));
    }

    @Override
    @LogRunningTime
    @GetMapping("divide")
    public String divide(@RequestParam(defaultValue = "1") String x,
                         @RequestParam(defaultValue = "1") String y) {
        randomSleep(maxSleepMillis);
        return String.valueOf(parseInt(x) / parseFloat(y));
    }
}
