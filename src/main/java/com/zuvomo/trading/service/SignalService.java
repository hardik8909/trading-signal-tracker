package com.zuvomo.trading.service;

import com.zuvomo.trading.dto.request.CreateSignalRequest;
import com.zuvomo.trading.dto.request.SignalResponse;

import java.util.List;

public interface SignalService {
    SignalResponse createSignal(CreateSignalRequest request);

    List<SignalResponse> getAllSignals();

    SignalResponse getSignalById(Long id);

    void deleteSignal(Long id);

}
