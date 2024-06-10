package com.example.cbrcurrencyservice.service;

import com.example.cbrcurrencyservice.adapter.web.client.CbrCurrencyClient;
import com.example.cbrcurrencyservice.adapter.web.dto.CurrencyRateResponse;
import com.example.cbrcurrencyservice.adapter.web.error.NotFoundException;
import com.example.cbrcurrencyservice.domain.CacheCurrency;
import com.example.cbrcurrencyservice.domain.Currency;
import com.example.cbrcurrencyservice.domain.MoneyValue;
import com.example.cbrcurrencyservice.service.mapper.CurrencyMapper;
import com.example.cbrcurrencyservice.service.parser.CbrXmlParser;
import com.github.benmanes.caffeine.cache.LoadingCache;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Log4j2
public class CurrencyService {
    CbrXmlParser cbrXmlParser;
    CbrCurrencyClient cbrCurrencyClient;
    CurrencyMapper currencyMapper;
    LoadingCache<Integer, CacheCurrency> cache;

    private Currency getCurrencyRateByCharCode(String charCode) {
        CacheCurrency currencies = (cache.get(0).getCurrenciesList() == null) ? getAllCurrency() : cache.get(0);
        return currencies.getCurrenciesList().stream()
                .filter(cur -> cur.getCharCode().equals(charCode.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Currency by char code:" + charCode));
    }

    public CurrencyRateResponse getCurrencyResponseRateByCharCode(String charCode) {
        Currency currency = getCurrencyRateByCharCode(charCode);
        return currencyMapper.CurrencyToResponse(currency, cache.get(0).getDate());
    }

    private CacheCurrency getAllCurrency() {
        cache.cleanUp();
        String xmlCurrencies = cbrCurrencyClient.getCurrencies();
        CacheCurrency currencies = cbrXmlParser.parse(xmlCurrencies);
        cache.put(0, currencies);
        return currencies;
    }

    public CurrencyRateResponse convert(String charCode, String charCodeConvert) {
        Currency currency = getCurrencyRateByCharCode(charCode);
        Currency currencyConvert = getCurrencyRateByCharCode(charCodeConvert);
        BigDecimal convertedRate = currency.getUnitRate().divide(currencyConvert.getUnitRate(), RoundingMode.HALF_UP);
        return currencyMapper.convertedCurrencyToResponse(currency, cache.get(0).getDate(), new MoneyValue(currencyConvert.getCharCode(), convertedRate));
    }
}
