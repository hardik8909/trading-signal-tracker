package com.zuvomo.trading.util;

import com.zuvomo.trading.entity.TradingSignal;
import com.zuvomo.trading.enums.Direction;
import com.zuvomo.trading.enums.SignalStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignalStatusUtilTest {
    private TradingSignal createBuySignal() {

        TradingSignal signal = new TradingSignal();

        signal.setDirection(Direction.BUY);
        signal.setEntryPrice(BigDecimal.valueOf(100));
        signal.setStopLoss(BigDecimal.valueOf(90));
        signal.setTargetPrice(BigDecimal.valueOf(110));
        signal.setExpiryTime(LocalDateTime.now().plusHours(2));
        signal.setStatus(SignalStatus.OPEN);

        return signal;
    }

    private TradingSignal createSellSignal() {

        TradingSignal signal = new TradingSignal();

        signal.setDirection(Direction.SELL);
        signal.setEntryPrice(BigDecimal.valueOf(100));
        signal.setStopLoss(BigDecimal.valueOf(110));
        signal.setTargetPrice(BigDecimal.valueOf(90));
        signal.setExpiryTime(LocalDateTime.now().plusHours(2));
        signal.setStatus(SignalStatus.OPEN);

        return signal;
    }

    @Test
    void shouldReturnTargetHitForBuySignal() {

        TradingSignal signal = createBuySignal();

        SignalStatus status = SignalStatusUtil.evaluate(
                signal,
                BigDecimal.valueOf(115)
        );

        assertEquals(SignalStatus.TARGET_HIT, status);
    }

    @Test
    void shouldReturnStopLossHitForBuySignal() {

        TradingSignal signal = createBuySignal();

        SignalStatus status = SignalStatusUtil.evaluate(
                signal,
                BigDecimal.valueOf(85)
        );

        assertEquals(SignalStatus.STOPLOSS_HIT, status);
    }

    @Test
    void shouldReturnTargetHitForSellSignal() {

        TradingSignal signal = createSellSignal();

        SignalStatus status = SignalStatusUtil.evaluate(
                signal,
                BigDecimal.valueOf(85)
        );

        assertEquals(SignalStatus.TARGET_HIT, status);
    }

    @Test
    void shouldReturnExpiredWhenSignalIsExpired() {

        TradingSignal signal = createBuySignal();

        signal.setExpiryTime(LocalDateTime.now().minusHours(1));

        SignalStatus status = SignalStatusUtil.evaluate(
                signal,
                BigDecimal.valueOf(100)
        );

        assertEquals(SignalStatus.EXPIRED, status);
    }

}
