package com.example.cbrcurrencyservice.adapter.web;

import com.example.cbrcurrencyservice.adapter.web.dto.CurrencyRateResponse;
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
@RequestMapping("api/cbr")
@Validated
public class CurrencyController {
    CurrencyService currencyService;

    @GetMapping("/currency")
    public CurrencyRateResponse getCurrencyByCharCode(@RequestParam("charCode") @CharCodeConstraint String charCode) {
        return currencyService.getCurrencyRateByCharCode(charCode);
    }
//    @GetMapping("/info")
//    public void getInfo(){
//        currencyService.cacheInfo();
//    }
}
