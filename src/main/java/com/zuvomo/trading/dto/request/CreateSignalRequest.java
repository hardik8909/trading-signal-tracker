package com.zuvomo.trading.dto.request;

import com.zuvomo.trading.enums.Direction;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateSignalRequest(

        @NotBlank(message = "Symbol is required")
        String symbol,

        @NotNull(message = "Direction is required")
        Direction direction,

        @NotNull(message = "Entry price is required")
        BigDecimal entryPrice,

        @NotNull(message = "Stop loss is required")
        BigDecimal stopLoss,

        @NotNull(message = "Target price is required")
        BigDecimal targetPrice,

        @NotNull(message = "Entry time is required")
        LocalDateTime entryTime,

        @NotNull(message = "Expiry time is required")
        LocalDateTime expiryTime
) {
}

