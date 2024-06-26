package com.example.cbrcurrencyservice.adapter.web;

import com.example.cbrcurrencyservice.adapter.web.dto.CurrencyRateRequest;
import com.example.cbrcurrencyservice.adapter.web.validation.CharCodeConstraint;
import com.example.cbrcurrencyservice.service.CurrencyService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("api/cbr/currency")
@Validated
public class CurrencyController {
    CurrencyService currencyService;

    @GetMapping()
    public CurrencyRateRequest getCurrencyByCharCode(@RequestParam("charCode") @CharCodeConstraint String charCode) {
        return currencyService.getCurrencyResponseRateByCharCode(charCode);
    }

    @GetMapping("/convert")
    public CurrencyRateRequest convertCurrency(@RequestParam("charCode") @CharCodeConstraint String charCode,
                                               @RequestParam("convertCharCode") @CharCodeConstraint String convertCharCode) {
        return currencyService.convert(charCode, convertCharCode);
    }
}
