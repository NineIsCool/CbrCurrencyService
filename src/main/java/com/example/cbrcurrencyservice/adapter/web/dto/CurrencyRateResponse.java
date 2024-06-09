package com.example.cbrcurrencyservice.adapter.web.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CurrencyRateResponse(String charCode, String name, int nominal, BigDecimal rate, LocalDate date) {
}