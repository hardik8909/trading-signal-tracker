package com.zuvomo.trading.dto.request;

import com.zuvomo.trading.enums.Direction;
import com.zuvomo.trading.enums.SignalStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SignalResponse(
        Long id,

        String symbol,

        Direction direction,

        BigDecimal entryPrice,

        BigDecimal stopLoss,

        BigDecimal targetPrice,

        LocalDateTime entryTime,

        LocalDateTime expiryTime,

        LocalDateTime createdAt,

        SignalStatus status,

        BigDecimal realizedRoi

) {
}

