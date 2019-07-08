package com.cay.sbt.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
//标示此类为配置类
@Configuration
//使该配置类在内置的配置类之后配置，保证自定义配置类生效，并不会被覆盖
@AutoConfigureAfter(RedisAutoConfiguration.class)
//开启缓存支持
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Autowired
    LettuceConnectionFactory lettuceConnectionFactory;

    /**
     * 自定义RedisTemplate配置
     * */
    @Bean("customRedisTemplate")
    public RedisTemplate<String,Object> redisTemplate(LettuceConnectionFactory redisConnectionFactory){
        //配置redisTemplate
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        //key序列化
        redisTemplate.setKeySerializer(keySerializer());
        //hash key序列化
        redisTemplate.setHashKeySerializer(keySerializer());
        //value序列化
        redisTemplate.setValueSerializer(valueSerializer());
        //hash value序列化
        redisTemplate.setHashValueSerializer(valueSerializer());

        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.afterPropertiesSet();
        log.info("自定义RedisTemplate完成！");
        return redisTemplate;
    }

    @Bean
    public RedisCacheManager cacheManager(LettuceConnectionFactory lettuceConnectionFactory) {
//        Map<String, RedisCacheConfiguration> initialCacheConfigurations = new HashMap<>();
//        //获取默认配置
//        RedisCacheConfiguration defaultCacheConfiguration = getDefaultRedisCacheConfiguration(Duration.ZERO);
//        initialCacheConfigurations.put("10min",getDefaultRedisCacheConfiguration(Duration.ofMinutes(10L)));
//
//        RedisCacheManager redisCacheManager = RedisCacheManager
//                .builder(lettuceConnectionFactory)
//                .cacheDefaults(defaultCacheConfiguration)
//                .withInitialCacheConfigurations(initialCacheConfigurations)
//                .transactionAware()
//                .build();
//        return redisCacheManager;

        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(5L))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer()))
                .disableCachingNullValues();

        RedisCacheManager redisCacheManager = RedisCacheManager.builder(lettuceConnectionFactory)
                .cacheDefaults(configuration)
                .transactionAware()
                .build();
        log.info("自定义RedisCacheManager完成！");
        return redisCacheManager;

    }

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        /**
         * 设置自动key的生成规则，配置spring boot的注解，进行方法级别的缓存
         * */
        return (target,method,params) -> {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(target.getClass().getName());
            stringBuilder.append(":");
            stringBuilder.append(method.getName());
            for (Object obj:params) {
                stringBuilder.append(":" + obj);
            }
            String key = String.valueOf(stringBuilder);
            log.info("自动生成redis key,key:={}",key);
            return key;
        };
    }

    @Override
    public CacheErrorHandler errorHandler() {
        /**
         *异常处理，当redis发生异常时，打印异常，但是程序正常走
         * */
        log.info("初始化[{}]","CacheErrorHandler");
        CacheErrorHandler cacheErrorHandler = new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
                log.error("redis occur handleCacheGetError,key:={}",key,e);
            }

            @Override
            public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value) {
                log.error("redis occur handleCachePutError,key:={},value:={}",key,value,e);
            }

            @Override
            public void handleCacheEvictError(RuntimeException e, Cache cache, Object key) {
                log.error("redis occur handleCacheEvictError,key:={}",key);
            }

            @Override
            public void handleCacheClearError(RuntimeException e, Cache cache) {
                log.error("redis occur handleCacheClearError");
            }
        };
        return cacheErrorHandler;
    }

    //key 序列化
    private RedisSerializer<String> keySerializer(){
        return new StringRedisSerializer();
    }
    //value 序列化
    private RedisSerializer<Object> valueSerializer(){
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
//        return jackson2JsonRedisSerializer;
        return new GenericJackson2JsonRedisSerializer();
    }
//    //获取默认缓存配置
//    private RedisCacheConfiguration getDefaultRedisCacheConfiguration(Duration duration){
//        RedisCacheConfiguration defaultCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
//        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
//        return defaultCacheConfiguration
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(genericJackson2JsonRedisSerializer))
//                .entryTtl(duration);
//    }
}
