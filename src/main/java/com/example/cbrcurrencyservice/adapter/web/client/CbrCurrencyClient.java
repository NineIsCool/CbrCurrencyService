package com.example.cbrcurrencyservice.adapter.web.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "cbrclient", url = "${cbr.currencies.url}")
public interface CbrCurrencyClient {
    @GetMapping
    String getCurrencies();
}
