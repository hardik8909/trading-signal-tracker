package com.zuvomo.trading.mapper;

import com.zuvomo.trading.dto.request.SignalResponse;
import com.zuvomo.trading.entity.TradingSignal;

public class SignalMapper {
    private SignalMapper(){}

    public static SignalResponse toResponse(TradingSignal signal){

        return new SignalResponse(

                signal.getId(),
                signal.getSymbol(),
                signal.getDirection(),
                signal.getEntryPrice(),
                signal.getStopLoss(),
                signal.getTargetPrice(),
                signal.getEntryTime(),
                signal.getExpiryTime(),
                signal.getCreatedAt(),
                signal.getStatus(),
                signal.getRealizedRoi()

        );

    }
}
