package com.example.cbrcurrencyservice.adapter.web.dto;

import com.example.cbrcurrencyservice.domain.MoneyValue;

import java.time.LocalDate;

public record CurrencyRateRequest(String charCode, String name, int nominal, MoneyValue rate, LocalDate date) {
}