package com.zuvomo.trading.client;

import com.zuvomo.trading.dto.response.BinancePriceResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class BinanceClient {
    private final RestClient restClient;

    public BinanceClient(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("https://api.binance.com")
                .build();
    }

    public BinancePriceResponse getCurrentPrice(String symbol) {

        return restClient.get()
                .uri("/api/v3/ticker/price?symbol={symbol}", symbol)
                .retrieve()
                .body(BinancePriceResponse.class);
    }


}
