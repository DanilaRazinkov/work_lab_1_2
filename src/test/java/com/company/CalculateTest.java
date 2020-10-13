package com.company;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculateTest {

    @Test
    void sumTest() {
        assertEquals(15, Calculate.calculate("7+8"), "Сумма 7+8 равна 15");
        assertEquals(26, Calculate.calculate("(7+9)+10"), "Сумма (7+9)+10 равна 26");
        assertEquals(56.5, Calculate.calculate("((7+9)+(10+20.5))+10"), "Сумма ((7+9)+(10+20.5))+10 равна 56.5");
    }

    @Test
    void sumMinusTest() {
        assertEquals(-1, Calculate.calculate("7-8"), "Выражение 7-8 равно -1");
        assertEquals(8, Calculate.calculate("(7-9)+10"), "Выражение (7-9)+10 равно 8");
        assertEquals(-4.5, Calculate.calculate("((7+9)-(10+20.5))+10"), "Выражение ((7+9)-(10+20.5))+10 равно -4.5");
    }

    @Test
    void sumMinusMultiplicationDivisionTest() {
        assertEquals(-921.0281, Calculate.calculate("(10.5+20.24)*(20.505-30.07)-(20+0.9)*30"),
                "Выражение (10.5+20.24)*(20.505-30.07)-(20+0.9)*30 равно -921.0281");
        assertEquals(-0.006378949754158468, Calculate.calculate("(10*120*(10+(10-9)/18*7)/90+" +
                        "90*(17+1/10)/(19+19/(19+19)))/(200-2894+299*(183-288)+" +
                        "299/(183-(1/(18-2/10))/11))"),
                "Выражение (10 * 120 * ( 10 + (10 - 9) / 18 * 7 ) / 90 +" +
                        " 90* (17 + 1 /10)/(19 + 19/(19 +19)))/ (200 - 2894 +" +
                        " 299 * (183 - 288)+ 299/(183 - (1/(18 - 2/ 10))/ 11)) равно -0.006378949754158468");
    }


}
