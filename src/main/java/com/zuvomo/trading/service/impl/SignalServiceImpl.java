package com.zuvomo.trading.service.impl;

import com.zuvomo.trading.client.BinanceClient;
import com.zuvomo.trading.dto.request.CreateSignalRequest;
import com.zuvomo.trading.dto.request.SignalResponse;
import com.zuvomo.trading.dto.response.BinancePriceResponse;
import com.zuvomo.trading.entity.TradingSignal;
import com.zuvomo.trading.enums.SignalStatus;
import com.zuvomo.trading.exception.ResourceNotFoundException;
import com.zuvomo.trading.mapper.SignalMapper;
import com.zuvomo.trading.repository.TradingSignalRepository;
import com.zuvomo.trading.service.SignalService;
import com.zuvomo.trading.util.RoiCalculator;
import com.zuvomo.trading.util.SignalStatusUtil;
import com.zuvomo.trading.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SignalServiceImpl implements SignalService {
    private final TradingSignalRepository repository;
    private final BinanceClient binanceClient;
    @Override
    public SignalResponse createSignal(CreateSignalRequest request) {

        ValidationUtil.validateSignal(request);

        TradingSignal signal = TradingSignal.builder()
                .symbol(request.symbol())
                .direction(request.direction())
                .entryPrice(request.entryPrice())
                .stopLoss(request.stopLoss())
                .targetPrice(request.targetPrice())
                .entryTime(request.entryTime())
                .expiryTime(request.expiryTime())
                .createdAt(LocalDateTime.now())
                .status(SignalStatus.OPEN)
                .build();

        repository.save(signal);

        return SignalMapper.toResponse(signal);

    }
    @Override
    public List<SignalResponse> getAllSignals() {

        return repository.findAll()
                .stream()
                .map(this::updateSignalStatus)
                .map(SignalMapper::toResponse)
                .toList();

    }
    @Override
    public SignalResponse getSignalById(Long id) {

        TradingSignal signal = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Signal not found"));

        updateSignalStatus(signal);

        return SignalMapper.toResponse(signal);

    }
    @Override
    public void deleteSignal(Long id) {

        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Signal not found");
        }

        repository.deleteById(id);

    }
    private TradingSignal updateSignalStatus(TradingSignal signal) {

        BinancePriceResponse response =
                binanceClient.getCurrentPrice(signal.getSymbol());

        signal.setRealizedRoi(
                RoiCalculator.calculate(
                        signal.getDirection(),
                        signal.getEntryPrice(),
                        response.price()
                )
        );

        SignalStatus status =
                SignalStatusUtil.evaluate(signal, response.price());

        signal.setStatus(status);

        repository.save(signal);

        return signal;
    }

}
