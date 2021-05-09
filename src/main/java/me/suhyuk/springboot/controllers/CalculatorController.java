package me.suhyuk.springboot.controllers;

import me.suhyuk.springboot.interfaces.ICalculatorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculator")
public class CalculatorController implements ICalculatorController {

    private int i0 = 0; // 덧셈의 항등원 (identity)
    private int i1 = 1; // 곱셈의 항등원
    private float f0 = 0.0f; // 덧셈의 항등원 (identity)
    private float f1 = 1.0f; // 곱셈의 항등원

    private int parseInt(String s, int def) {
        if (s == null) return def;
        if (s.contains("."))
            return new Float(Float.parseFloat(s)).intValue();
        return Integer.parseInt(s);
    }

    private float parseFloat(String s, float def) {
        if (s == null) return def;
        return Float.parseFloat(s);
    }

    @Override
    @GetMapping("add")
    public String add(@RequestParam String x, String y) {
        return String.valueOf(parseInt(x, i0) + parseInt(y, i0));
    }

    @Override
    @GetMapping("subtract")
    public String subtract(String x, String y) {
        return String.valueOf(parseInt(x, i0) - parseInt(y, i0));
    }

    @Override
    @GetMapping("multiply")
    public String multiply(String x, String y) {
        return String.valueOf(parseInt(x, i1) * parseInt(y, i1));
    }

    @Override
    @GetMapping("divide")
    public String divide(String x, String y) {
        return String.valueOf(parseInt(x, i1) / parseFloat(y, f1));
    }
}
