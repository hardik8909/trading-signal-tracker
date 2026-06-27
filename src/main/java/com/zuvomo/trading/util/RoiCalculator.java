package com.zuvomo.trading.util;

import com.zuvomo.trading.enums.Direction;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RoiCalculator {
    private RoiCalculator(){}

    public static BigDecimal calculate(
            Direction direction,
            BigDecimal entryPrice,
            BigDecimal currentPrice
    ){

        BigDecimal roi;

        if(direction==Direction.BUY){

            roi=currentPrice.subtract(entryPrice)
                    .divide(entryPrice,8,RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(100));

        }else{

            roi=entryPrice.subtract(currentPrice)
                    .divide(entryPrice,8, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(100));

        }

        return roi.setScale(2,RoundingMode.HALF_UP);

    }

}
