package com.zuvomo.trading.dto.response;

import java.math.BigDecimal;

public record BinancePriceResponse(
        String symbol,
        BigDecimal price
) {
}
