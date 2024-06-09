package com.example.cbrcurrencyservice.config;

import com.example.cbrcurrencyservice.domain.CacheCurrency;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {
    @Bean
    public Caffeine caffeineConfig() {
        return Caffeine.newBuilder().expireAfterWrite(24, TimeUnit.HOURS).maximumSize(1);
    }

    @Bean
    public CacheManager cacheManager(Caffeine caffeine) {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }

    @Bean
    public LoadingCache<Integer, CacheCurrency> cache() {
        return Caffeine.newBuilder()
                .maximumSize(3)
                .expireAfterWrite(24, TimeUnit.HOURS)
                .build(k -> new CacheCurrency());
    }
}
