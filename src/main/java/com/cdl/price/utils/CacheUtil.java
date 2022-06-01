package com.cdl.price.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class CacheUtil {

    @Autowired
    private CacheManager cacheManager;

    private static CacheManager cm;

    @PostConstruct
    public void init() {
        cm = cacheManager;
    }

    /**
     * Add cache
     *
     * @param cacheName
     * @param key
     * @param value
     */
    public static void put(String cacheName, String key, Object value) {
        Cache cache = cm.getCache(cacheName);
        cache.put(key, value);
    }

    /**
     * Get cache
     *
     * @param cacheName
     * @param key
     * @return
     */
    public static Object get(String cacheName, String key) {
        Cache cache = cm.getCache(cacheName);
        if (cache == null || cache.get(key) == null) {
            return null;
        }
        return Objects.requireNonNull(cache.get(key)).get();
    }

    /**
     * Get cache as String
     *
     * @param cacheName
     * @param key
     * @return
     */
    public static String getString(String cacheName, String key) {
        Cache cache = cm.getCache(cacheName);
        if (cache == null) {
            return null;
        }
        Cache.ValueWrapper wrapper = cache.get(key);
        if (wrapper == null) {
            return null;
        }
        return Objects.requireNonNull(wrapper.get()).toString();
    }

    /**
     * Get cache as <T>
     *
     * @param cacheName
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T get(String cacheName, String key, Class<T> clazz) {
        Cache cache = cm.getCache(cacheName);
        if (cache == null) {
            return null;
        }
        Cache.ValueWrapper wrapper = cache.get(key);
        if (wrapper == null) {
            return null;
        }
        return (T) wrapper.get();
    }

    /**
     * Evict cache
     *
     * @param cacheName
     * @param key
     */
    public static void evict(String cacheName, String key) {
        Cache cache = cm.getCache(cacheName);
        if (cache != null) {
            cache.evict(key);
        }
    }
}
