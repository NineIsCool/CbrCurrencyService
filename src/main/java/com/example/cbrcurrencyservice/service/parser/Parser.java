package com.example.cbrcurrencyservice.service.parser;

import com.example.cbrcurrencyservice.domain.Currency;

import java.util.List;

public interface Parser {
    List<Currency> parse(String ratesAsString);
}
