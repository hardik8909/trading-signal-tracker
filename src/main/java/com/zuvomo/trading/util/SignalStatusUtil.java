package com.zuvomo.trading.util;

import com.zuvomo.trading.entity.TradingSignal;
import com.zuvomo.trading.enums.Direction;
import com.zuvomo.trading.enums.SignalStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SignalStatusUtil {
    private SignalStatusUtil(){}

    public static SignalStatus evaluate(
            TradingSignal signal,
            BigDecimal currentPrice
    ){

        if(signal.getStatus()!=SignalStatus.OPEN){
            return signal.getStatus();
        }

        if(signal.getDirection()== Direction.BUY){

            if(currentPrice.compareTo(signal.getTargetPrice())>=0){
                return SignalStatus.TARGET_HIT;
            }

            if(currentPrice.compareTo(signal.getStopLoss())<=0){
                return SignalStatus.STOPLOSS_HIT;
            }

        }else{

            if(currentPrice.compareTo(signal.getTargetPrice())<=0){
                return SignalStatus.TARGET_HIT;
            }

            if(currentPrice.compareTo(signal.getStopLoss())>=0){
                return SignalStatus.STOPLOSS_HIT;
            }

        }

        if(LocalDateTime.now().isAfter(signal.getExpiryTime())){
            return SignalStatus.EXPIRED;
        }

        return SignalStatus.OPEN;

    }

}
