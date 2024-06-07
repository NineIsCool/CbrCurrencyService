package com.example.cbrcurrencyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CbrCurrencyServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CbrCurrencyServiceApplication.class, args);
    }

}
