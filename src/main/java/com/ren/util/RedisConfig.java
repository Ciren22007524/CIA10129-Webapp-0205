package com.ren.util;

import com.Entity.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.List;

@Configuration
public class RedisConfig {

    /**
     * 自定義 RedisTemplate，設定存入Redis的商品緩存資料為 (ProductNo(Primary key), )
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<String, List<Product>> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, List<Product>> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        return redisTemplate;
    }
}
