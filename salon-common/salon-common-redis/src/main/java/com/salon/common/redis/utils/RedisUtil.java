package com.salon.common.redis.utils;

import cn.hutool.core.util.ObjectUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.*;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.core.utils
 * @Project：salon
 * @name：RedisUtil
 * @Date：2024/4/3 13:53
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RedisUtil {

    private final RedisTemplate<String, Object> redisTemplate;

    private final RedissonClient redissonClient;

    /**
     * 24小时过期，单位：秒.
     */
    public final static long DEFAULT_EXPIRE = 60 * 60 * 24;

    /**
     * 1小时过期，单位：秒.
     */
    public final static long HOUR_ONE_EXPIRE = 60 * 60;

    /**
     * 6小时过期，单位：秒.
     */
    public final static long HOUR_SIX_EXPIRE = 60 * 60 * 6;

    /**
     * 5分钟过期，单位：秒.
     */
    public final static long MINUTE_FIVE_EXPIRE = 5 * 60;

    /**
     * 永不过期.
     */
    public final static long NOT_EXPIRE = -1L;

    public RLock getLock(String key) {
        return redissonClient.getLock(key);
    }

//    public RLock getFencedLock(String key) {
//        return redissonClient.getFencedLock(key);
//    }

    public RLock getFairLock(String key) {
        return redissonClient.getFairLock(key);
    }

    public RLock getReadLock(String key) {
        return redissonClient.getReadWriteLock(key).readLock();
    }

    public RLock getWriteLock(String key) {
        return redissonClient.getReadWriteLock(key).writeLock();
    }

    public boolean tryLock(RLock lock, long expire, long timeout) throws InterruptedException {
        return lock.tryLock(timeout, expire, TimeUnit.MILLISECONDS);
    }

//    public boolean rateLimiter(String key, RateType mode, long replenishRate, long rateInterval,
//                               RateIntervalUnit rateIntervalUnit) {
//        RRateLimiter rateLimiter = redissonClient.getRateLimiter(key);
//        rateLimiter.trySetRate(mode, replenishRate, rateInterval, rateIntervalUnit);
//        return rateLimiter.tryAcquire();
//    }

    public boolean tryLock(String key, long expire, long timeout) throws InterruptedException {
        return tryLock(getLock(key), expire, timeout);
    }

    public void unlock(String key) {
        unlock(getLock(key));
    }

    public void unlock(RLock lock) {
        lock.unlock();
    }

    public void lock(String key) {
        lock(getLock(key));
    }

    public void lock(RLock lock) {
        lock.lock();
    }

    public boolean isLocked(String key) {
        return isLocked(getLock(key));
    }

    public boolean isLocked(RLock lock) {
        return lock.isLocked();
    }

    public boolean isHeldByCurrentThread(String key) {
        return isHeldByCurrentThread(getLock(key));
    }

    public boolean isHeldByCurrentThread(RLock lock) {
        return lock.isHeldByCurrentThread();
    }

    public void set(String key, Object value) {
        set(key, value, DEFAULT_EXPIRE);
    }

    public void set(String key, Object value, long expire) {
        if (expire == NOT_EXPIRE) {
            redissonClient.getBucket(key).set(value);
        }
        else {
            redissonClient.getBucket(key).set(value, expire,TimeUnit.SECONDS);
        }
    }

    public void lSet(String key, List<Object> objList, long expire) {
        RList<Object> rList = redissonClient.getList(key);
        rList.expireIfNotSet(Duration.ofSeconds(expire));
        rList.addAll(objList);
    }

    public void lSet(String key, Object obj, long expire) {
        RList<Object> rList = redissonClient.getList(key);
        rList.expireIfNotSet(Duration.ofSeconds(expire));
        rList.add(obj);
    }

    public int lSize(String key) {
        return redissonClient.getList(key).size();
    }

    public void lTrim(String key, int start, int end) {
        redissonClient.getList(key).trim(start, end);
    }

    public List<Object> lGetAll(String key) {
        return redissonClient.getList(key).readAll();
    }

    public Object lGet(String key, int index) {
        return redissonClient.getList(key).get(index);
    }

    public Long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    public void setIfAbsent(String key, Object value) {
        setIfAbsent(key, value, DEFAULT_EXPIRE);
    }

    public void setIfAbsent(String key, Object value, long expire) {
        redissonClient.getBucket(key).setIfAbsent(value, Duration.ofSeconds(expire));
    }

    public Object get(String key) {
        return redissonClient.getBucket(key).get();
    }

    public void hDel(String key) {
        redissonClient.getMap(key).delete();
    }

    public boolean delete(String... key) {
        return redissonClient.getKeys().delete(key) > 0;
    }

    public void hDel(String key, String field) {
        redissonClient.getMap(key).remove(field);
    }

    public void hDel(String key, String... field) {
        redissonClient.getMap(key).fastRemove(field);
    }

    public boolean hasKey(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    public boolean hasHashKey(String key, String field) {
        return redissonClient.getMap(key).containsKey(field);
    }

    public long incrementAndGet(String key) {
        RAtomicLong atomicLong = redissonClient.getAtomicLong(key);
        atomicLong.expireIfNotSet(Duration.ofSeconds(HOUR_ONE_EXPIRE));
        return atomicLong.incrementAndGet();
    }

    public long decrementAndGet(String key) {
        RAtomicLong atomicLong = redissonClient.getAtomicLong(key);
        atomicLong.expireIfNotSet(Duration.ofSeconds(HOUR_ONE_EXPIRE));
        return atomicLong.decrementAndGet();
    }

    public long addAndGet(String key, long value) {
        RAtomicLong atomicLong = redissonClient.getAtomicLong(key);
        atomicLong.expireIfNotSet(Duration.ofSeconds(HOUR_ONE_EXPIRE));
        return atomicLong.addAndGet(value);
    }

    public Set<String> keys(String pattern) {
        return redissonClient.getKeys().getKeysStreamByPattern(pattern).collect(Collectors.toSet());
    }

    public Set<String> keys() {
        return redissonClient.getKeys().getKeysStream().collect(Collectors.toSet());
    }

    public long getAtomicValue(String key) {
        return redissonClient.getAtomicLong(key).get();
    }

    public void hSet(String key, String field, Object value, long expire) {
        RMap<String, Object> map = redissonClient.getMap(key);
        map.expire(Duration.ofSeconds(expire));
        map.put(field, value);
    }

    public void hSet(String key, Map<String, Object> map, long expire) {
        RMap<String, Object> rMap = redissonClient.getMap(key);
        rMap.expire(Duration.ofSeconds(expire));
        rMap.putAll(map);
    }

    public void hSet(String key, String field, Object value) {
        hSet(key, field, value, NOT_EXPIRE);
    }

    public Object hGet(String key, String field) {
        return redissonClient.getMap(key).get(field);
    }

    public long getKeysSize() {
        final Object obj = redisTemplate.execute(RedisServerCommands::dbSize);
        return ObjectUtil.isNull(obj) ? 0 : Long.parseLong(obj.toString());
    }

    public List<Map<String, String>> getCommandStatus() {
        Properties commandStats = (Properties) redisTemplate
                .execute((RedisCallback<Object>) connection -> connection.serverCommands().info("commandstats"));
        Assert.isTrue(ObjectUtil.isNotNull(commandStats), "command states is null");
        Set<String> set = commandStats.stringPropertyNames();
        List<Map<String, String>> pieList = new ArrayList<>(set.size());
        set.forEach(key -> {
            Map<String, String> data = new HashMap<>(2);
            String property = commandStats.getProperty(key);
            data.put("name", StringUtils.removeStart(key, "cmdstat_"));
            data.put("value", StringUtils.substringBetween(property, "calls=", ",usec"));
            pieList.add(data);
        });
        return pieList;
    }

    public Map<String, String> getInfo() {
        final Properties properties = redisTemplate.execute(RedisServerCommands::info,
                redisTemplate.isExposeConnection());
        Assert.isTrue(ObjectUtil.isNotNull(properties), "properties is null");
        final Set<String> set = properties.stringPropertyNames();
        Map<String, String> dataMap = new HashMap<>(set.size());
        set.forEach(key -> dataMap.put(key, properties.getProperty(key)));
        return dataMap;
    }

    public <T> T execute(RedisScript<T> script, List<String> keys, Object... args) {
        return redisTemplate.execute(script, keys, args);
    }

}
