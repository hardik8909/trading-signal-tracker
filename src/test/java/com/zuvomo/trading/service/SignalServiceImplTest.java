package com.zuvomo.trading.service;

import com.zuvomo.trading.client.BinanceClient;
import com.zuvomo.trading.dto.request.CreateSignalRequest;
import com.zuvomo.trading.dto.request.SignalResponse;
import com.zuvomo.trading.entity.TradingSignal;
import com.zuvomo.trading.enums.Direction;
import com.zuvomo.trading.enums.SignalStatus;
import com.zuvomo.trading.exception.ResourceNotFoundException;
import com.zuvomo.trading.repository.TradingSignalRepository;
import com.zuvomo.trading.service.impl.SignalServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SignalServiceImplTest {
    @Mock
    private TradingSignalRepository repository;

    @Mock
    private BinanceClient binanceClient;

    @InjectMocks
    private SignalServiceImpl service;

    @Test
    void shouldCreateSignal() {

        CreateSignalRequest request = new CreateSignalRequest(
                "BTCUSDT",
                Direction.BUY,
                BigDecimal.valueOf(100),
                BigDecimal.valueOf(90),
                BigDecimal.valueOf(110),
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(2)
        );

        when(repository.save(any(TradingSignal.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        SignalResponse response = service.createSignal(request);

        assertEquals("BTCUSDT", response.symbol());
        assertEquals(SignalStatus.OPEN, response.status());

        verify(repository, times(1))
                .save(any(TradingSignal.class));
    }

    @Test
    void shouldThrowExceptionWhenSignalNotFound() {

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> service.getSignalById(1L)
        );
    }

    @Test
    void shouldDeleteSignal() {

        when(repository.existsById(1L))
                .thenReturn(true);

        service.deleteSignal(1L);

        verify(repository).deleteById(1L);
    }

}
