package com.example.cbrcurrencyservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Builder
public class Currency {
    int numCode;
    String charCode;
    int nominal;
    String name;
    BigDecimal rate;
    BigDecimal unitRate;
}
