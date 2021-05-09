package me.suhyuk.springboot;

import org.junit.Test;

public class ParsingNumerics {

    private int parseInt(String str) {
        if (str.contains("."))
            return new Float(Float.parseFloat(str)).intValue();
        return Integer.parseInt(str);
    }

    private float parseFloat(String str) {
        return Float.parseFloat(str);
    }

    @Test
    public void testParseString() {
        String strInt = "1";
        String strFloat = "1.1";
        System.out.println(new Float(strFloat).intValue());
    }

    @Test
    public void testDivideInt() {
        String x = "1";
        String y = "2";
        System.out.println(parseInt(x) / parseFloat(y));
    }
}
