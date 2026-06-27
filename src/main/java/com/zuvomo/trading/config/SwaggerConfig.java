package com.zuvomo.trading.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

public class SwaggerConfig {
    @Bean
    public OpenAPI tradingApi(){

        return new OpenAPI()
                .info(
                        new Info()
                                .title("Trading Signal Tracker API")
                                .version("1.0")
                                .description("Backend Assignment")
                );

    }

}
