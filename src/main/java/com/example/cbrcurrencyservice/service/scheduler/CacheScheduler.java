package com.example.cbrcurrencyservice.service.scheduler;

import com.example.cbrcurrencyservice.domain.CacheCurrency;
import com.github.benmanes.caffeine.cache.LoadingCache;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CacheScheduler {
    LoadingCache<Integer, CacheCurrency> cache;
    @Scheduled(cron = "${cache.clear.cron}", zone = "Europe/Moscow")
    public void cleanCache(){
        cache.cleanUp();
        log.info("cache cleared");
    }
}
