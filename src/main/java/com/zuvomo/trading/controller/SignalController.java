package com.zuvomo.trading.controller;

import com.zuvomo.trading.dto.request.CreateSignalRequest;
import com.zuvomo.trading.dto.request.SignalResponse;
import com.zuvomo.trading.service.SignalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/signals")
@RequiredArgsConstructor
public class SignalController {
    private final SignalService signalService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SignalResponse createSignal(
            @Valid @RequestBody CreateSignalRequest request) {

        return signalService.createSignal(request);

    }

    @GetMapping
    public List<SignalResponse> getAllSignals() {

        return signalService.getAllSignals();

    }

    @GetMapping("/{id}")
    public SignalResponse getSignalById(@PathVariable Long id) {

        return signalService.getSignalById(id);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSignal(@PathVariable Long id) {

        signalService.deleteSignal(id);

    }


}
