package com.example.cbrcurrencyservice.service.mapper;

import com.example.cbrcurrencyservice.adapter.web.dto.CurrencyRateResponse;
import com.example.cbrcurrencyservice.domain.Currency;
import com.example.cbrcurrencyservice.domain.MoneyValue;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CurrencyMapper {
    public CurrencyRateResponse CurrencyToResponse(Currency currency, LocalDate date) {
        return new CurrencyRateResponse(
                currency.getCharCode(),
                currency.getName(),
                currency.getNominal(),
                currency.getRate(),
                date);
    }

    public CurrencyRateResponse convertedCurrencyToResponse(Currency currency, LocalDate date, MoneyValue convertedRate) {
        return new CurrencyRateResponse(
                currency.getCharCode(),
                currency.getName(),
                1,
                convertedRate,
                date);
    }
}
