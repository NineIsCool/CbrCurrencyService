package com.example.cbrcurrencyservice.service;

import com.example.cbrcurrencyservice.adapter.web.client.CbrCurrencyClient;
import com.example.cbrcurrencyservice.adapter.web.dto.CurrencyRateResponse;
import com.example.cbrcurrencyservice.adapter.web.error.NotFoundException;
import com.example.cbrcurrencyservice.domain.Currency;
import com.example.cbrcurrencyservice.service.mapper.CurrencyMapper;
import com.example.cbrcurrencyservice.service.parser.CbrXmlParser;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CurrencyService {
    CbrXmlParser cbrXmlParser;
    CbrCurrencyClient cbrCurrencyClient;
    CurrencyMapper currencyMapper;

    public CurrencyRateResponse getCurrencyRateByCharCode(String charCode) {
        Currency currency = getAllCurrency().stream()
                .filter(cur -> cur.getCharCode().equals(charCode.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Currency by char code:" + charCode));
        return currencyMapper.CurrencyToResponse(currency);
    }

    private List<Currency> getAllCurrency() {
        String xmlCurrencies = cbrCurrencyClient.getCurrencies();
        return cbrXmlParser.parse(xmlCurrencies);
    }
}
