package com.dbepcepk.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalculatorTest {
    private Calculator calculator = new Calculator();

    @Test
    public void testSum() {
        Assertions.assertEquals(5, calculator.sum(2, 3));
    }
}
