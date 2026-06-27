package com.zuvomo.trading.util;

import com.zuvomo.trading.dto.request.CreateSignalRequest;
import com.zuvomo.trading.enums.Direction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidationUtilTest {
    @Test
    void shouldAcceptValidBuySignal() {

        CreateSignalRequest request = new CreateSignalRequest(
                "BTCUSDT",
                Direction.BUY,
                BigDecimal.valueOf(100),
                BigDecimal.valueOf(90),
                BigDecimal.valueOf(110),
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(2)
        );

        assertDoesNotThrow(() ->
                ValidationUtil.validateSignal(request));
    }

    @Test
    void shouldRejectInvalidBuyStopLoss() {

        CreateSignalRequest request = new CreateSignalRequest(
                "BTCUSDT",
                Direction.BUY,
                BigDecimal.valueOf(100),
                BigDecimal.valueOf(120),
                BigDecimal.valueOf(110),
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(2)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> ValidationUtil.validateSignal(request)
        );
    }

    @Test
    void shouldRejectInvalidSellTargetPrice() {

        CreateSignalRequest request = new CreateSignalRequest(
                "BTCUSDT",
                Direction.SELL,
                BigDecimal.valueOf(100),
                BigDecimal.valueOf(120),
                BigDecimal.valueOf(110),
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(2)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> ValidationUtil.validateSignal(request)
        );
    }

    @Test
    void shouldRejectExpiredEntryTime() {

        CreateSignalRequest request = new CreateSignalRequest(
                "BTCUSDT",
                Direction.BUY,
                BigDecimal.valueOf(100),
                BigDecimal.valueOf(90),
                BigDecimal.valueOf(110),
                LocalDateTime.now().minusDays(2),
                LocalDateTime.now().plusHours(2)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> ValidationUtil.validateSignal(request)
        );
    }

    @Test
    void shouldRejectExpiryBeforeEntryTime() {

        CreateSignalRequest request = new CreateSignalRequest(
                "BTCUSDT",
                Direction.BUY,
                BigDecimal.valueOf(100),
                BigDecimal.valueOf(90),
                BigDecimal.valueOf(110),
                LocalDateTime.now(),
                LocalDateTime.now().minusHours(1)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> ValidationUtil.validateSignal(request)
        );
    }

}
