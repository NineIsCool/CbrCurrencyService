package com.example.cbrcurrencyservice.adapter.web.dto;

import java.math.BigDecimal;

public record CurrencyRateResponse(String charCode,String name, int nominal,BigDecimal rate) {
}