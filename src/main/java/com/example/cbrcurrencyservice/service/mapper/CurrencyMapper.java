package com.example.cbrcurrencyservice.service.mapper;

import com.example.cbrcurrencyservice.adapter.web.dto.CurrencyRateResponse;
import com.example.cbrcurrencyservice.domain.Currency;
import org.springframework.stereotype.Component;

@Component
public class CurrencyMapper {
    public CurrencyRateResponse CurrencyToResponse(Currency currency){
        return new CurrencyRateResponse(
                currency.getCharCode(),
                currency.getName(),
                currency.getNominal(),
                currency.getRate());
    }
}
