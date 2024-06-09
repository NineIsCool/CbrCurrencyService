package com.example.cbrcurrencyservice.service.parser;

import com.example.cbrcurrencyservice.domain.CacheCurrency;

public interface Parser {
    CacheCurrency parse(String ratesAsString);
}
