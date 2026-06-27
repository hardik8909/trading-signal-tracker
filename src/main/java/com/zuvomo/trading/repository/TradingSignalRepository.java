package com.zuvomo.trading.repository;

import com.zuvomo.trading.entity.TradingSignal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradingSignalRepository extends JpaRepository<TradingSignal,Long> {
}
