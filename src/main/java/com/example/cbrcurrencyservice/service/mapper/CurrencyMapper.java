package com.example.cbrcurrencyservice.service.mapper;

import com.example.cbrcurrencyservice.adapter.web.dto.CurrencyRateRequest;
import com.example.cbrcurrencyservice.domain.Currency;
import com.example.cbrcurrencyservice.domain.MoneyValue;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CurrencyMapper {
    public CurrencyRateRequest CurrencyToResponse(Currency currency, LocalDate date) {
        return new CurrencyRateRequest(
                currency.getCharCode(),
                currency.getName(),
                currency.getNominal(),
                currency.getRate(),
                date);
    }

    public CurrencyRateRequest convertedCurrencyToResponse(Currency currency, LocalDate date, MoneyValue convertedRate) {
        return new CurrencyRateRequest(
                currency.getCharCode(),
                currency.getName(),
                1,
                convertedRate,
                date);
    }
}
