package com.andyadc.bms.cache.redis;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class RedisOperator {
    private static final Logger logger = LoggerFactory.getLogger(RedisOperator.class);

    private final RedisTemplate<String, Object> redisTemplate;
    private final RedisSerializer keySerializer;
    private final RedisSerializer valueSerializer;

    public RedisOperator(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        keySerializer = this.redisTemplate.getKeySerializer();
        valueSerializer = this.redisTemplate.getValueSerializer();
    }

    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    public void batctSet(Map<String, Object> kvs) {
        redisTemplate.executePipelined(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                kvs.forEach((k, v) -> {
                    redisConnection.set(keySerializer.serialize(k), valueSerializer.serialize(v));
                });
                return null;
            }
        });
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public List<Object> multiGet(List<String> keys) {
        List<Object> list = redisTemplate.opsForValue().multiGet(Sets.newHashSet(keys));
        List<Object> result = Lists.newArrayList();
        Optional.ofNullable(list).ifPresent(e -> list.forEach(el -> Optional.ofNullable(el).ifPresent(result::add)));
        return result;
    }
}
