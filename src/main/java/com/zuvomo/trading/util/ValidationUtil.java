package com.zuvomo.trading.util;

import com.zuvomo.trading.dto.request.CreateSignalRequest;
import com.zuvomo.trading.enums.Direction;

import java.time.LocalDateTime;

public class ValidationUtil {
    private ValidationUtil(){}

    public static void validateSignal(CreateSignalRequest request){

        if(request.direction()== Direction.BUY){

            if(request.stopLoss().compareTo(request.entryPrice())>=0){
                throw new IllegalArgumentException(
                        "BUY signal requires Stop Loss < Entry Price");
            }

            if(request.targetPrice().compareTo(request.entryPrice())<=0){
                throw new IllegalArgumentException(
                        "BUY signal requires Target Price > Entry Price");
            }

        }

        if(request.direction()== Direction.SELL){

            if(request.stopLoss().compareTo(request.entryPrice())<=0){
                throw new IllegalArgumentException(
                        "SELL signal requires Stop Loss > Entry Price");
            }

            if(request.targetPrice().compareTo(request.entryPrice())>=0){
                throw new IllegalArgumentException(
                        "SELL signal requires Target Price < Entry Price");
            }

        }

        if(request.expiryTime().isBefore(request.entryTime())){
            throw new IllegalArgumentException(
                    "Expiry time must be after Entry time");
        }

        if(request.entryTime().isBefore(LocalDateTime.now().minusHours(24))){
            throw new IllegalArgumentException(
                    "Entry time cannot be older than 24 hours");
        }

    }

}
