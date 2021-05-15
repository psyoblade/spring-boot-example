package me.suhyuk.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/calculator/v2")
public class CalculatorControllerV2 implements ICalculatorController {

    private static Logger logger = LoggerFactory.getLogger(CalculatorControllerV2.class);
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
    @GetMapping("add")
    public String add(@RequestParam(defaultValue = "0") String x,
                      @RequestParam(defaultValue = "0") String y) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        String ret = String.valueOf(parseInt(x) + parseInt(y));

        randomSleep(maxSleepMillis);
        stopWatch.stop();
        logger.info("\n[덧셈] : " + stopWatch.prettyPrint());
        return ret;
    }

    @Override
    @GetMapping("subtract")
    public String subtract(@RequestParam(defaultValue = "0") String x,
                           @RequestParam(defaultValue = "0") String y) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        String ret = String.valueOf(parseInt(x) - parseInt(y));

        randomSleep(maxSleepMillis);
        stopWatch.stop();
        logger.info("\n[덧셈] : " + stopWatch.prettyPrint());
        return ret;
    }

    @Override
    @GetMapping("multiply")
    public String multiply(@RequestParam(defaultValue = "1") String x,
                           @RequestParam(defaultValue = "1") String y) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        String ret = String.valueOf(parseInt(x) * parseInt(y));

        randomSleep(maxSleepMillis);
        stopWatch.stop();
        logger.info("\n[덧셈] : " + stopWatch.prettyPrint());
        return ret;
    }

    @Override
    @GetMapping("divide")
    public String divide(@RequestParam(defaultValue = "1") String x,
                         @RequestParam(defaultValue = "1") String y) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        String ret = String.valueOf(parseInt(x) / parseFloat(y));

        randomSleep(maxSleepMillis);
        stopWatch.stop();
        logger.info("\n[덧셈] : " + stopWatch.prettyPrint());
        return ret;
    }
}
