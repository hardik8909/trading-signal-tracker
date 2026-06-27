package com.zuvomo.trading.util;

import com.zuvomo.trading.enums.Direction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoiCalculatorTest {
    @Test
    void shouldCalculateBuyRoi() {

        BigDecimal roi = RoiCalculator.calculate(
                Direction.BUY,
                BigDecimal.valueOf(100),
                BigDecimal.valueOf(110)
        );

        assertEquals(
                BigDecimal.valueOf(10.00).setScale(2),
                roi
        );
    }

    @Test
    void shouldCalculateSellRoi() {

        BigDecimal roi = RoiCalculator.calculate(
                Direction.SELL,
                BigDecimal.valueOf(100),
                BigDecimal.valueOf(90)
        );

        assertEquals(
                BigDecimal.valueOf(10.00).setScale(2),
                roi
        );
    }
}
