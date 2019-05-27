package com.example.demo;

import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

@Configuration
public class EmbeddedRedisConfig {
    private final RedisProperties redisProperties;

    private RedisServer redisServer;

    public EmbeddedRedisConfig(final RedisProperties redisProperties) {
        this.redisProperties = redisProperties;
    }

    @PostConstruct
    public void redisServer() throws IOException {
            redisServer = new RedisServer(redisProperties.getPort());
            redisServer.start();
    }

    @PreDestroy
    public void stopRedis() {
        if (redisServer != null) {
            redisServer.stop();
        }
    }
}