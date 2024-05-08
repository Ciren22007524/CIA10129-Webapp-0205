package com.ren.util;

import com.ren.administrator.dto.LoginState;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
public class RedisConfig {

    /**
     * 變更RedisTemplate默認配置
     * 序列化器默認為JdkSerializationRedisSerializer，序列化後會回傳byte[]
     * 1.將key的序列化器設置為String序列化器
     * 2.將value的序列化器設置為Json序列化器
     *
     * @param connectionFactory 配置RedisConnectionFactory，與資料庫建立連線
     * @return 返回redisTemplate
     */
    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate<>();
        // 設置連線
        redisTemplate.setConnectionFactory(connectionFactory);
        // 設置Serializer
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        return redisTemplate;
    }
}
